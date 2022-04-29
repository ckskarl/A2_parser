package tests;
import static org.junit.jupiter.api.Assertions.*;

import utilities.*;
import structures.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MyArrayListTests {
	
	MyArrayList<Object> testArrayList;
	MyArrayList<Object> testArrayList2; 


	@BeforeEach
	void setUp() throws Exception {
		testArrayList = new MyArrayList<>();
		testArrayList2 = new MyArrayList<>();
	}

	@AfterEach
	void tearDown() throws Exception {
		testArrayList = null;
		testArrayList2 = null;
	}

	@Test
	void testAdd() {
		assertTrue(testArrayList.add("test"));
	}
	
	@Test
	void testAddIndex() {
		assertTrue(testArrayList.add(3, "test"));
	}
	
	@Test
	void testAddAll() {
		testArrayList.add("fat");
		testArrayList.add("cat");
		testArrayList2.add("small");
		testArrayList2.add("dog");
		testArrayList.addAll(testArrayList2);
		assertEquals(testArrayList.size(), 4);
	}
	
	@Test
	void testRemoveIndex() {
		testArrayList.add("test");
		testArrayList.add("fish");
		testArrayList.add("tuna");
		testArrayList.add("salmon");
		
		assertEquals(testArrayList.remove(1), "fish");
		
	}
	
	@Test
	void testRemoveElement() {
		testArrayList.add("test");
		testArrayList.add("fish");
		testArrayList.remove("test");
		assertFalse(testArrayList.contains("test"));
		
	}
	
	@Test
	void testGet() {
		testArrayList.add(10);
		testArrayList.add(5);
		assertEquals(testArrayList.get(0), 10);
		
	}
	
	@Test
	void testSize() {
		testArrayList.add(10);
		testArrayList.add(5);
		assertEquals(testArrayList.size(), 2);
		
	}
	
	@Test
	void testClear() {
		testArrayList.add(10);
		testArrayList.add(5);
		testArrayList.add(5);
		testArrayList.add(5);
		testArrayList.add(5);
		testArrayList.clear();
		assertEquals(testArrayList.size(), 0);
		
	}
	
	@Test
	void testSet() {
		testArrayList.add(10);
		testArrayList.add(5);
		testArrayList.add(5);
		testArrayList.add(5);
		testArrayList.add(5);
		testArrayList.set(0, 100);
		assertEquals(testArrayList.get(0), 100);
		
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(testArrayList.isEmpty());
		
	}
	
	@Test
	void testContains() {
		testArrayList.add("Fish");
		testArrayList.add("tree");
		assertTrue(testArrayList.contains("Fish"));
		
	}
	
	@Test
	void testToArray() {
		testArrayList.add("Fish");
		testArrayList.add("tree");
		Object[] myArray = testArrayList.toArray();
		assertEquals(myArray[1], "tree");
		
	}
	
	@Test
	void testStoreToArray() {
		testArrayList.add(1);
		testArrayList.add(2);
		testArrayList.add(3);
		Object[] myArray = new Object[] {"testing"};
		myArray = testArrayList.toArray(myArray);
		assertEquals(myArray[0], 1);
		
	}
	
	@Test
	void testIterator() {
		testArrayList.add("Fish");
		testArrayList.add("tree");
		Iterator<Object> it = testArrayList.iterator();
		
		assertEquals(it.next(), "Fish");
		assertEquals(it.next(), "tree");
		
	}

}
