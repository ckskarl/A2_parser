package structures;
import java.util.NoSuchElementException;
import utilities.*;

/**
 *An ArrayList data structure called MyArrayList is a class that is used to create an ArrayList, add elements to the ArrayList, remove elements from 
 * an ArrayList, return the size of the ArrayList, see if the ArrayList contains an element, and convert
 * the ArrayList into an Array, and an iterator.
 * 
 * @author Christian Lay
 * @version 12/11/2021
 * 
 *
 * @param <E> The type of elements this list holds
 */

@SuppressWarnings("serial")
public class MyArrayList<E> implements ListADT<E>  {
	private Object[] MyArrayList = {};
	private Object[] tempArrayList = {};
	private int initialSize = 10;
	private int currentSize = 0;




	/*
	 * Constructor used to create MyArrayList with size initially
	 * set to 10 elements
	 */
	public MyArrayList() {
		MyArrayList = new Object[initialSize];
	}



	/**
	 * The size method will return the current element count contained 
	 * in the list.
	 * 
	 * @return The currentSize of the ArrayList
	 */

	@Override
	public int size() {
		currentSize=0;
		for(int i =0; i < MyArrayList.length; i++) {
			if (MyArrayList[i] != null){
				currentSize++;
			}
		}

		return currentSize;
	}

	/**
	 * Removes all of the elements from this list. This list will be empty after
	 * this call returns.
	 */
	@Override
	public void clear() {
		for(int i = 0; i < MyArrayList.length; i++) {
			MyArrayList[i]= null; 
		}

	}

	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) and any subsequent
	 * elements to the right (adds one to their indices). If current ArrayList is
	 * full then it creates a new ArrayList double the size and copies everything 
	 * over to the tempArraylist
	 * 
	 * @param index The index at which the specified element is to be inserted
	 * @param toAdd The element to be inserted into the ArrayList
	 * 
	 * @return true if the element is added successfully
	 * @throws NullPointerException If the specified element is null
	 * @throws IndexOutOfBoundsException if the index is out of range of the ArrayList
	 */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if(toAdd ==null) {
			throw new NullPointerException("Value cannot be null");
		}
		else if(index < 0 || index >= MyArrayList.length) {
			throw new IndexOutOfBoundsException("Invalid index");
		}

		if(MyArrayList[MyArrayList.length-1] !=null) {
			tempArrayList = new Object[MyArrayList.length*2];
			for(int i =0; i < MyArrayList.length; i++) {
				tempArrayList[i]= MyArrayList[i]; 
			}
			MyArrayList = tempArrayList;
		}

		for(int i = MyArrayList.length-1; i>index; i--) {
			MyArrayList[i] = MyArrayList[i-1];
		}
		MyArrayList[index] = toAdd;
		return true;
	}

	/**
	 * Appends the specified element to the end of the ArrayList, if the current ArrayList is full
	 * it creates a tempArrayList double the size and copies the elements over.
	 * 
	 * @param toAdd Specified element to be added to the end of the ArrayList
	 * @return true if element is added successfully
	 * @throws NullPointerException if the specified element is null and ArrayList 
	 * cannot have null elements
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if(toAdd == null) {
			throw new NullPointerException("Value cannot be null");
		}

		if(MyArrayList[MyArrayList.length-1] !=null) {
			tempArrayList = new Object[MyArrayList.length*2];
			for(int i =0; i < MyArrayList.length; i++) {
				tempArrayList[i]= MyArrayList[i]; 
			}
			MyArrayList = tempArrayList;
		}

		for(int i =0; i < MyArrayList.length; i++) {
			if(MyArrayList[i] ==null) {
				MyArrayList[i] = toAdd;
				return true;
			}
		}

		return false;
	}

	/**
	 * Appends all of the elements in specified ArrayList to the end of
	 * this ArrayList in the same order
	 * 
	 * @param toAdd ArrayList of generic type to be added
	 * @return true if the addition of the ArrayList is successful
	 * @throws NullPointerException If the specified ArrayList is null.
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if(toAdd==null) {
			throw new NullPointerException("Value cannot be null");
		}

		Iterator<? extends E> iterator = toAdd.iterator();
		while(iterator.hasNext())
			if(!add(iterator.next())) {
				return false;
			}
		return true;
	}

	/**
	 * Returns the element of the specified position in the ArrayList
	 * @param index Index of the element to return
	 * @return The element at the specified position of the list.
	 * @throws IndexOutOfBoundsException if the index is out of range of the ArrayList
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= MyArrayList.length) {
			throw new IndexOutOfBoundsException("Invalid Index");
		}
		else {
			return (E) MyArrayList[index];
		}

	}

	/**
	 * Removes the element at the specified position in the ArrayList. Shifts
	 * all elements in the ArrayList to the left from where it was removed
	 * 
	 * @param index The index of the element to be removed
	 * @return The removed element
	 * @throws IndexOutOfBoundsException If the index is out of range of the ArrayList
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index <0 || index >= MyArrayList.length) {
			throw new IndexOutOfBoundsException("Invalid Index to remove");
		}
		E temp=(E) MyArrayList[index];
		for(int i = index; i < MyArrayList.length; i++) {
			//System.out.println(MyArrayList[i]);
			if(MyArrayList[i+1] == null) {
				MyArrayList[i] = null;
				break;
			}
			else {
				MyArrayList[i] = MyArrayList[i+1];
			}
		}

		return temp;
	}

	/**
	 * Removes the first occurrence in the list of the specified element.
	 * If the list does not contain the element nothing happens. Shifts all elements to
	 * the left once element is removed
	 * 
	 * @param toRemove the element to be removed from the list
	 * @return the element which is being removed, or null if the list does not contain the element
	 * @throws NullPointerException if the specified element is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(E toRemove) throws NullPointerException {
		if(toRemove==null) {
			throw new NullPointerException("Value cannot be null");
		}

		int indexRemove = 0;
		boolean elementFound = false;
		for(int i = 0; i < MyArrayList.length; i++) {
			if(MyArrayList[i] == toRemove) {
				indexRemove = i;
				elementFound = true;

			}
		}
		if(elementFound) {
			for(int i = indexRemove; i < MyArrayList.length; i++) {
				if(MyArrayList[i+1] == null) {
					MyArrayList[i] = null;
					break;
				}
				else {
					MyArrayList[i] = MyArrayList[i+1];
				}
			}
		}


		return (E) MyArrayList[indexRemove];
	}

	/**
	 * Replaces the element at the specified position in the ArrayList with the specified element
	 * 
	 * @param index the index of the element to be replaced
	 * @param toChange the element to be stored at the specified position
	 * @return The element previously at the specified position
	 * @throws NullPointerException if the specified element is null
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if(toChange == null) {
			throw new NullPointerException("Value cannot be null");
		}
		else if(index<0 || index>=MyArrayList.length) {
			throw new IndexOutOfBoundsException("Invalid Index");
		}
		E temp=(E) MyArrayList[index];
		MyArrayList[index] = toChange;
		return temp;
	}

	/**
	 * Returns true if the list contains no elements
	 * 
	 * @return true if the list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		for(int i = 0; i < MyArrayList.length; i++) {
			if(MyArrayList[i]!=null) {
				return false;

			}
		}
		return true;
	}

	/**
	 * Returns true if the list contains the specified element
	 * 
	 * @param toFind The element that is being searched for in the ArrayList
	 * @return True if the element is found in the ArrayList
	 * @throws NullPointerException if the element is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if(toFind == null) {
			throw new NullPointerException("Value cannot be null");
		}

		for(int i = 0; i< MyArrayList.length; i++) {
			if(MyArrayList[i] == toFind) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns an array containing all of the elements in the ArrayList in the same
	 * sequence
	 * 
	 * @param toHold The array which the elements of this ArrayList are to be stored
	 * if the Array is not large enough a new array is created
	 * @return An array contain the elements of this ArrayList
	 * @throws NullPointerException
	 * If the specified array is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if(toHold == null) {
			throw new NullPointerException("Value cannt be null");
		}

		if(toHold.length < MyArrayList.length) {
			tempArrayList = new Object[MyArrayList.length];
			toHold = (E[]) tempArrayList;

		}
		for(int i =0; i<MyArrayList.length;i++) {
			toHold[i]= (E) MyArrayList[i]; 
		}
		return toHold;

	}

	/**
	 * Returns an array containing all of the elements in this ArrayList in proper sequence
	 * @return An Array containing all the elements listed in proper sequence
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray() {
		Object[] MyArray = new Object[MyArrayList.length];
		for(int i =0; i<MyArrayList.length;i++) {
			MyArray[i]= MyArrayList[i]; 
		}
		return (E[]) MyArray;
	}


	/**
	 * Returns an iterator over the elements in this list, in proper sequence
	 * @return an iterator over the elements in this list in proper sequence
	 */
	@Override 
	public Iterator<E> iterator() {
		Iterator<E> it = new Iterator<E>() {
			private int index = 0; // Current index

			@Override public boolean hasNext() {
				return index < size();
			}
			@SuppressWarnings("unchecked")
			@Override public E next() throws NoSuchElementException {
				if(!hasNext()) throw new NoSuchElementException("No more elements");
				return (E)MyArrayList[index++];
			}
		};
		return it;
	}

}