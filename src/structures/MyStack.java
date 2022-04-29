package structures;

import exceptions.EmptyStackException;
import java.util.NoSuchElementException;
import utilities.*;

/**
 * MyStack class for creating a stack structure for storing/retrieving elements. Has methods for
 * creating a new stack object, adding an element to a stack, removing from the stack, 
 * peeking the top element, clearing the stack, checking if it is empty, 
 * check to see if it contains an element, changing the stack to an array with and without an argument, 
 * returning an iterator and returning the size of the stack. 
 * 
 * @author Kin Shing Chong, Chirstian Lay, Alex Fleury, Brandon Donkersloot
 * @version 11/12/2021
 */
@SuppressWarnings("serial")
public class MyStack<E> implements StackADT<E> {


	private MyArrayList<E> stack;

	/**
	 * Creates a new instance of MyStack object. 
	 */
	public MyStack() {
		this.stack = new MyArrayList<E>();
	}

	/**
	 * Pushes an element to the top of the stack. 
	 * 
	 * @param toAdd the element to add to the top of the stack
	 * @throws NullPointerException when the element is null 
	 */
	@Override
	public void push(E toAdd) throws NullPointerException {
		if(toAdd == null) {
			throw new NullPointerException("Value cannot be null");
		}

		stack.add(toAdd);

	}

	/**
	 * Removes the top element from the stack and returns it
	 * 
	 * @return the element at the top of the stack
	 * @throws EmptyStackException when the stack is empty
	 */
	@Override
	public E pop() throws EmptyStackException {
		if (stack.size() == 0) {
			throw new EmptyStackException("Stack is empty");
		}
		return stack.remove(stack.size()-1);

	}

	/**
	 * Looks at the object at the top of this stack without removing it from the
	 * stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws EmptyStackException when the stack is empty
	 */
	@Override
	public E peek() throws EmptyStackException {
		if (stack.size() == 0) {
			throw new EmptyStackException("Stack is empty");
		}
		return stack.get(stack.size() -1 );
	}

	/**
	 * Clears the stack of all elements inside it
	 */
	@Override
	public void clear() {
		stack.clear();
	}

	/**
	 * Returns a boolean value weather or not the stack is empty
	 * 
	 * @return True is the stack is empty, else false
	 */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence
	 * 
	 * @return an array containing the elements of this stack in proper order
	 */
	@Override
	public Object[] toArray()  {

		Object[] MyArray = new Object[stack.size()];
		for(int i =0; i< stack.size() ;i++) {
			MyArray[i]= stack.get(i); 
		}
		return MyArray;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence, which is the supplied array if it is big enough
	 * 
	 * @param holder the array to hold all the elements in the stack if it
	 * is big enough. If not the array size is increased
	 * @return an array containing the elements of this stack.
	 * @throws NullPointerException if the specified array is null.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		if(holder == null) {
			throw new NullPointerException("Value is null");
		}
		if (holder.length < stack.size()) {
			holder = (E[]) new Object[stack.size()];
		}

		for(int i =0; i< stack.size() ;i++) {
			holder[i]= stack.get(i); 
		}

		return holder;
	}

	/**
	 * Returns a boolean value if the stack contains the element supplied to the method
	 * 
	 * @param toFind the element to find in the stack
	 * @return true if the element is found in the stack, else false
	 * @throws NullPointerException when the element supplied is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if(toFind == null) {
			throw new NullPointerException("Value is null");
		}
		return stack.contains(toFind);
	}

	/**
	 * Returns the position of the searched element in the stack, if found, starting from the top of the stack, 
	 * the top element being 1, and -1 if not found. 
	 * 
	 * @param toFind the element to find in the stack
	 * @return an integer above 1 if the element is found, else -1
	 */
	@Override
	public int search(E toFind) {

		for (int i = stack.size()-1; i >= 0; i--) {
			if (stack.get(i).equals(toFind)) {
				return ((stack.size()-1) - i) + 1;
			}

		}
		return -1;
	}

	/**
	 * Returns an iterator to iterate over the elements in the stack in proper order
	 * from the top of the stack downwards
	 * 
	 * @return an iterator to iterate over the stack
	 */
	@Override
	public Iterator<E> iterator() {
		Iterator<E> it = new Iterator<E>() {
			private int index = stack.size()-1;

			@Override
			public boolean hasNext() {

				return index >= 0;
			}


			@Override
			public E next() throws NoSuchElementException {
				if(!hasNext()) throw new NoSuchElementException();
				return (E)stack.get(index--);
			}
		};
		return it;

	}

	/**
	 * Checks a stack the the current stack to see if they are equal by comparing each element in
	 * proper sequence. Will return a boolean value weather or not the stacks are equal
	 * 
	 * @param that a stack to be compared
	 * @return true is the two stacks are equal, else false
	 */
	@Override
	public boolean equals(StackADT<E> that) {
		Object[] array1 = stack.toArray();
		Object[] array2 = that.toArray();
		for (int i = 0; i < stack.size(); i++) {
			if (array1[i] != array2[i]) {
				return false;
			}

		}
		return true;
	}

	/**
	 * Returns the size of the stack; number of elements in the stack as an integer
	 * 
	 * @return the number of elements in the stack (size) as an integer
	 */
	@Override
	public int size() {

		return stack.size() ;
	}






}
