package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import structures.MyDLL;
import utilities.Iterator;



class MyDLLTest {

	private MyDLL<Integer> testDLL= new MyDLL<Integer>();
	private MyDLL<Integer> secondTestDLL= new MyDLL<Integer>();
	
	

	@BeforeEach
	void setUp() throws Exception {
		testDLL.add(0);
		testDLL.add(1);
		testDLL.add(2);
		testDLL.add(3);
		secondTestDLL.add(10);
		secondTestDLL.add(20);
		secondTestDLL.add(30);
	}

	@AfterEach
	void tearDown() throws Exception {
		testDLL=null;
		secondTestDLL=null;
	}

	@Test
	void testSize(){
		assertSame(4,testDLL.size());
	}
	
	
	@Test
	void testClear() {
		assertTrue(testDLL.size()==4);
		testDLL.clear();
		assertTrue(testDLL.size()==0);
	}
	
	@Test
	void testAdd() {
		assertTrue(testDLL.size()==4);
		assertSame(2,testDLL.get(2));
		assertSame(4,testDLL.size());
		testDLL.add(2,100);
		assertTrue(testDLL.size()==5);
		assertSame(100,testDLL.get(2));
	}
	
	@Test
	void testAdd2(){
		Integer test1 = 0;
		assertSame(4,testDLL.size());
		assertTrue(testDLL.add(test1));
		assertSame(5,testDLL.size());
	}
	
	
	@Test
	void testAddAll() {
		assertTrue(testDLL.size()==4);
		testDLL.addAll(secondTestDLL);
		assertTrue(testDLL.size()==7);
		assertSame(0,testDLL.get(0));
		assertSame(1,testDLL.get(1));
		assertSame(2,testDLL.get(2));
		assertSame(3,testDLL.get(3));
		assertSame(10,testDLL.get(4));
		assertSame(20,testDLL.get(5));
		assertSame(30,testDLL.get(6));
	}
	
	
	@Test
	void testGet() {
		assertSame(0,testDLL.get(0));
		assertSame(1,testDLL.get(1));
		assertSame(2,testDLL.get(2));
		assertSame(3,testDLL.get(3));
	}
	
	
	@Test
	void testRemove() {
		assertTrue(testDLL.size()==4);
		assertSame(0,testDLL.get(0));
		assertSame(1,testDLL.get(1));
		assertSame(2,testDLL.get(2));
		assertSame(3,testDLL.get(3));
		assertSame(4,testDLL.size());
		testDLL.remove(0);
		assertSame(1,testDLL.get(0));
		testDLL.remove(1);
		assertSame(3,testDLL.get(1));
		assertSame(2,testDLL.size());
	}
	
	@Test
	void testRemove2() {
		assertTrue(testDLL.size()==4);
		assertSame(0,testDLL.get(0));
		assertSame(1,testDLL.get(1));
		assertSame(2,testDLL.get(2));
		assertSame(3,testDLL.get(3));
		assertSame(4,testDLL.size());
		Integer zero = 0;
		testDLL.remove(zero);
		assertSame(3,testDLL.size());
		assertSame(1,testDLL.get(0));

	}

	
	@Test
	void testSet() {
		assertTrue(testDLL.size()==4);
		assertSame(0,testDLL.get(0));
		assertSame(1,testDLL.get(1));
		assertSame(2,testDLL.get(2));
		assertSame(3,testDLL.get(3));
		assertSame(4,testDLL.size());
		Integer ten = 10;
		testDLL.set(2,ten);
		assertSame(0,testDLL.get(0));
		assertSame(1,testDLL.get(1));
		assertSame(10,testDLL.get(2));
		assertSame(3,testDLL.get(3));
		assertSame(4,testDLL.size());
	}
	
	@Test
	void testIsEmpty() {
		assertFalse(testDLL.isEmpty());
		testDLL.clear();
		assertTrue(testDLL.isEmpty());
	}
	
	@Test
	void testContains() {
		assertTrue(testDLL.contains(0));
		assertTrue(testDLL.contains(1));
		assertTrue(testDLL.contains(2));
		assertTrue(testDLL.contains(3));
		assertFalse(testDLL.contains(4));
		assertFalse(testDLL.contains(5));
		assertFalse(testDLL.contains(-1));
	}

	@Test
	void testToArray() {
		Integer[] toHold = new Integer[] {10,9,8,7,6};
		assertSame(10,toHold[0]);
		assertSame(9,toHold[1]);
		assertSame(8,toHold[2]);
		assertSame(7,toHold[3]);
		assertSame(6,toHold[4]);
		testDLL.toArray(toHold);
		assertSame(0,toHold[0]);
		assertSame(1,toHold[1]);
		assertSame(2,toHold[2]);
		assertSame(3,toHold[3]);
		assertSame(null,toHold[4]);
	}
	
	@Test
	void testToArray2() {
		Integer[] toHold2 = new Integer[] {10,9};
		assertSame(10,toHold2[0]);
		assertSame(9,toHold2[1]);
		testDLL.toArray(toHold2);
		assertSame(0,(testDLL.toArray(toHold2))[0]);
		assertSame(1,(testDLL.toArray(toHold2))[1]);
		assertSame(2,(testDLL.toArray(toHold2))[2]);
		assertSame(3,(testDLL.toArray(toHold2))[3]);
	}


	
	@Test
	void exceptionTesting() {
		NullPointerException thrown = assertThrows(
				NullPointerException.class,() -> testDLL.add(2,null),
	           "Expected adding null to throw, but it didn't"
	    );
	    assertTrue(thrown.getMessage().contains("The specific Element is Null."));
	    IndexOutOfBoundsException thrown2 = assertThrows(
	    		IndexOutOfBoundsException.class,() -> testDLL.add(5,100),
		           "Expected index out of bound to throw, but it didn't"
		    );
	    //System.out.println(thrown2.getMessage());
	    assertTrue(thrown2.getMessage().contains("Index is out of range."));
	    thrown2= assertThrows(
	    		IndexOutOfBoundsException.class,() -> testDLL.add(-1,100),
		           "Expected index out of bound to throw, but it didn't"
		    );

	}
	
	void testIterator() {
		Iterator<Integer> it = testDLL.iterator();
		assertEquals(it.next(), 0);
		assertEquals(it.next(), 1);
	}
}
