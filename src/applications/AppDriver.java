package applications;

import java.io.*;
import java.util.Scanner;


import exceptions.*;
import structures.*;

/**
 * Application driver, which starts the XML Parser
 * 
 * @author Kin Shing Chong, Chirstian Lay, Alex Fleury, Brandon Donkersloot
 * @version 13/11/2021
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * It read the file path from command line argument, if the file is xml format, it start parsing and
	 * check for any syntax error found in the file. It then print out the errors found to the terminal.
	 * No error message will be printed if there are no errors
	 * @param args for the main method
	 * @throws EmptyQueueException if any of the queue structure is found empty.
	 * @throws EmptyStackException if any of the stack structure is found empty.
	 */
	public static void main(String[] args) throws EmptyQueueException,EmptyStackException {
		//declaring variables
		MyQueue<String> welcomeQ = new MyQueue<String>();
		MyStack<String> stack = new MyStack<String>();
		MyQueue<String> errorQ = new MyQueue<String>();
		MyQueue<String> extrasQ = new MyQueue<String>();
		String startTag="";
		String endTag="";
		int lineCount=0;
		
		String filename="";
		Scanner input=new Scanner(System.in);
		if(args.length!=1) {
			System.out.print("Incorrent number of argument! Please only input one argument! e.g. c:/cprg311/sample2.xmk");
		}else {
			filename = args[0];
		}
		
		input.close();

		Scanner sc;

		try {
			if (filename.endsWith(".xml")) {
				File file = new File(filename);
				sc = new Scanner(file);
				String inputLine="";
				//scan file data
				while (sc.hasNextLine()) {
					
					lineCount++;
					String printOut=sc.nextLine();
					inputLine=printOut.trim();
					System.out.println("  Line "+lineCount+": "+printOut);
					//find potential tag string, and store them individually to a quene to improve the flow of the program.
					while(inputLine.contains("<")&&inputLine.contains(">")) {
						int indexOfOpenBracket = inputLine.indexOf("<");
						int indexOfCloseBracket = inputLine.indexOf(">");
						if (inputLine.indexOf("<")!=0) {
							String content =  inputLine.substring(0, indexOfOpenBracket);
							welcomeQ.enqueue(content);
						}
						String tag = inputLine.substring(indexOfOpenBracket, indexOfCloseBracket+1);
						welcomeQ.enqueue(tag);
						inputLine=inputLine.substring(indexOfCloseBracket+1);
					}

					//Parsing the tag one by one and do different things according to type of tag(Self-closing,Start and End)
					while(welcomeQ.iterator().hasNext()) {
						String element = welcomeQ.dequeue();
						if (element.startsWith("<") && element.endsWith(">")){
							//check if it is a self-closing tage, if yes, do nothing
							if (element.endsWith("/>") || (element.startsWith("<?"))) {
								continue;
							}else if (!element.startsWith("</")) {
								//check if it is a start tag, if yes push to stack
								if (element.contains(" ")) {
									startTag=element.substring(1,element.indexOf(" "));
								}else {
									startTag=element.substring(1,element.indexOf(">"));
								}
								stack.push(startTag);
							}else {
								//trim the end tag to get rid of name="value" information
								if (element.contains(" ")) {
									endTag=element.substring(1,element.indexOf(" "));
								}else {
									endTag=element.substring(2,element.indexOf(">"));
								}

								//check if the close tag finds its corrisponding start tag 
								if (stack.isEmpty()) {
									errorQ.enqueue(endTag);
								}else if(stack.peek().equals(endTag)) {
									stack.pop();
								}else {
									if (errorQ.size()>0) {
										if(errorQ.peek().equals(endTag)) {
											errorQ.dequeue();
										}
									}	

									int matchIndex=stack.search(endTag);
									//tag pair encounter error, report errors
									if (matchIndex!=-1) {
										System.out.println("ERROR in line "+lineCount+": The tag "+endTag+" contains unfinished tag");
										for (int i=1;i<matchIndex;i++) {
											System.out.println("ERROR in line "+lineCount+": The tag "+stack.peek()+" is not finished because it is inside other complete tag!("+endTag+")");
											errorQ.enqueue(stack.pop());							
										}stack.pop();
									}
									else {
										extrasQ.enqueue(endTag);		 					
									}
								}
							}
						}

					}
				}
				
				//dealing with remaining error tags, report errors
				while (!stack.isEmpty()) {
					errorQ.enqueue(stack.pop());
				}

				while (!(errorQ.isEmpty()&&!extrasQ.isEmpty())||(!errorQ.isEmpty()&&extrasQ.isEmpty())) {
					while (!errorQ.isEmpty()) {
						System.out.println("ERROR: "+errorQ.dequeue()+" tag is not ended properly!");
					}
					while (!extrasQ.isEmpty()) {
						System.out.println("ERROR: "+extrasQ.dequeue()+" tag is not started properly!");
					}
				}
				while(!(errorQ.isEmpty()&& !extrasQ.isEmpty())) {

					if (errorQ.peek().equals(extrasQ.peek())) {
						errorQ.dequeue();
						extrasQ.dequeue();
					}else {
						System.out.println("ERROR: "+errorQ.dequeue()+" tag is not ended properly!");
					}


				}
			}else {
				System.out.println("Not an xml file!");
			}
			

		} catch (FileNotFoundException e) {
			System.out.println("File not found :" + filename);

		}


	}

}
