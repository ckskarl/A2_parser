/**
 * 
 */
package tests;

import static org.junit.Assert.*;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.EmptyStackException;
import utilities.*;
import structures.*;



public class MyStackTest {
	
	private MyStack<Object> one;
	private MyStack<Object> two;
	


	@Before
	public void setUp() throws Exception {
		one = new MyStack<>();
		two = new MyStack<>();
	}


	@After
	public void tearDown() throws Exception {
		one = null;
		two = null;
	}




	@Test
	public void testPush() {
		Object expected = 2;
		one.push(1);
		one.push(2);
		Object actual = null;
		try {
			actual = one.peek();
		} catch (EmptyStackException e) {
			fail("push throws EmptyStackException incorrectly.");
		}
		
		assertEquals("push method did not add an element to the stack.", expected, actual);
	}

	@Test
	public void testPop() {

		one.push(1);
		one.push(2);
		one.push(3);
		System.out.println(one.peek());
		assertSame(3,one.pop());
		
	}


	@Test
	public void testPeek() {
		Object expected = 1;
		one.push(2);
		one.push(3);
		one.push(1);
		Object actual = null;
		try {
			actual = one.peek();
		} catch (EmptyStackException e) {
			fail("peek throws EmptyStackException incorrectly.");
		}
		assertEquals("peek method did not return the correct element in the stack.", expected, actual);
	}


	@Test
	public void testClear() {
		int expected = 0;
		one.push(3);
		one.push(3);
		one.push(3);
		one.push(3);
		one.clear();
		int actual = one.size();
		assertEquals("clear method did not clear the stack.", expected, actual);
	}


	@Test
	public void testIsEmpty() {
		one.push(3);
		one.push(3);
		one.push(3);
		one.push(3);
		one.clear();
		assertTrue("the isEmpty method did not evaluate the stack correctly", one.isEmpty());
	}

	@Test
	public void testIsNotEmpty() {
		boolean expected = false;
		one.push(3);
		one.push(3);
		one.push(3);
		one.push(3);
		boolean actual = one.isEmpty();
		assertEquals("the isEmpty method did not evaluate the stack correctly", expected, actual);
	}


	@Test
	public void testToArray() {
		
		one.push(1);
		one.push(2);
		one.push(3);
		Object[] array = one.toArray();
		for (int i = 0; i <= array.length -1; i++) {
			
			
			if ((int)array[i] != i+1) {
				fail("Not the proper elements in array");
			}
		
		}
		
		assertTrue("The toArray method did not return an array", array.getClass().isArray());
	}


	@Test
	public void testToArrayObjectArray() {
		one.push(1);
		one.push(2);
		one.push(3);
		Object[] arrayPassed = one.toArray();
		Object[] array = one.toArray(arrayPassed);
		for (int i = 0; i <= array.length -1; i++) {
			
			
			if ((int)array[i] != i+1) {
				fail("Not the proper elements in array");
			}
		
		}
		assertTrue("The toArray method did not return an array", array.getClass().isArray());
	}


	@Test
	public void testContains() {
		one.push(2);
		one.push(3);
		one.push(1);
		assertTrue("contains method did not preoperly assess the stack", one.contains(2));
	}
	

	@Test
	public void testNotContains() {
		boolean expected = false;
		one.push(2);
		one.push(3);
		one.push(1);
		boolean actual = one.contains(6);
		assertEquals("contains method did not preoperly assess the stack", expected, actual);
	}


	@Test
	public void testSearchTopOfStack() {
		int expected = 1;
		one.push(2);
		one.push(3);
		one.push(1);
		one.push(4);
		int actual = one.search(4);
		assertEquals("search method did not return the correct result.", expected, actual);
	}
	
	@Test
	public void testSearchMiddleOfStack() {
		int expected = 2;
		one.push(2);
		one.push(3);
		one.push(1);
		one.push(4);
		int actual = one.search(1);
		assertEquals("search method did not return the correct result.", expected, actual);
	}

	@Test
	public void testIterator() {
		int[] numbers = {4, 3, 2, 1};
		one.push(1);
		one.push(2);
		one.push(3);
		one.push(4);
		Iterator<Object> it = (Iterator<Object>) one.iterator();
		
		int actual = 4;
		int expected = 0;
		int i = 0;
		while(it.hasNext()) {

			if (!(it.next().equals(numbers[i])) ) {
				
				fail("Elements in iterator out of order");
			}
			
			actual--;
			i++;
			
		}
		assertEquals("iterator method did not return the correct result.", expected, actual);
		
	}


	@Test
	public void testEqualsStackADT() {
		
		one.push(2);
		one.push(3);
		one.push(1);
		
		two.push(2);
		two.push(3);
		two.push(1);
		
		assertTrue("the equals method did not evaluate the two stacks correctly ", one.equals(two));
	}


	@Test
	public void testNotEqualsStackADT() {
		boolean expected = false;
		one.push(2);
		one.push(3);
		one.push(1);
		
		two.push(3);
		two.push(3);
		two.push(3);
		boolean actual = one.equals(two);
		assertEquals("equals method did not evaluate the two stacks correctly ", expected, actual);
	}
	

	@Test
	public void testSize() {
		int expected = 3;
		one.push(2);
		one.push(3);
		one.push(1);
		int actual = one.size();
		
		assertEquals("size method did not return the correct size of the stack.", expected, actual);
	}

}
