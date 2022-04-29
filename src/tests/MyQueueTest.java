package tests;

import exceptions.*;
import structures.*;
import utilities.Iterator;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyQueueTest {
	
	//test objects
	MyQueue<Object> one;
	MyQueue<Object> two;
	static MyQueue<?> zero;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		zero = new MyQueue<Object>();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		zero = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		one = new MyQueue<Object>();
		two = new MyQueue<Object>(5);
	}

	@AfterEach
	void tearDown() throws Exception {
		one = null;
		two = null;
	}

	@Test
	void testEnqueue() {
		one.enqueue(1);
		int expected = 1;
		int actual = 0;
		try {
			actual = (int) one.peek();
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
		assertEquals("The Enqueue method did not add object to the queue.", expected, actual);
	}

	@Test
	void testDequeue() {
		one.enqueue(2);
		one.enqueue(1);
		int expected = 2;
		int actual = 0;
		try {
			actual = (int) one.dequeue();
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
		assertEquals("The Enqueue method did not add object to the queue.", expected, actual);
	}

	@Test
	void testPeek() throws EmptyQueueException {
		one.enqueue("TEST");
		System.out.println(one.peek().toString());
		assertSame("TEST",(String) one.peek());
	}

	@Test
	void testDequeueAll() {
		one.enqueue(one);
		one.enqueue(one);
		one.enqueue(one);
		one.dequeueAll();
		Object expected = null;
		Object actual = null;
		assertEquals("The dequeueAll method did not clear the queue.", expected, actual);
	}

	@Test
	void testIsEmpty() {
		boolean expected = true;
		boolean actual = one.isEmpty();
		assertEquals("The isEmpty method did not implement properly.", expected, actual);
	}

	@Test
	void testIterator() {
		one.enqueue(0);
		one.enqueue(1);
		Iterator<Object> it = one.iterator();
		assertEquals(it.next(), 0);
		assertEquals(it.next(), 1);
	}

	@Test
	void testEqualsQueueADTOfE() {
		one.enqueue(1);
		two.enqueue(1);
		boolean expected = true;
		boolean actual = false;
		actual = one.equals(two);
		assertEquals("The equals method did not compare properly", expected, actual);
	}

	@Test
	void testToArray() {
		one.enqueue(1);
		one.enqueue(2);
		one.enqueue(3);
		Object[] expected = new Object[] {1,2,3};
		Object[] actual = one.toArray();
		
		assertArrayEquals("The toArray method is not working.", expected, actual);
	}

	@Test
	void testToArrayEArray() {
		Object[] existing = new Object[] {"TEST", 999};
		assertSame("TEST",existing[0]);
		one.enqueue(1);
		one.toArray(existing);
		assertSame(1,existing[0]);
	}



	@Test
	void testSize() {
		one.enqueue(2);
		one.enqueue(2);
		one.enqueue(2);
		int expected = 3;
		int actual = one.size();
		assertEquals("The Size method did not implement properly.", expected, actual);
	}


	
}
