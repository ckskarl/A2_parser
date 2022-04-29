package structures;



import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;



/**
 * This is a Doubly Linked List class in which stores collection of data using nodes,
 * It consists of a sequence of nodes, connected by links in both directions
 * Each node contains a single element, plus links to the node’s successor 
 * and predecessor (or null links if head/tail)The DLL header contains links to
 * the DLL’s first and last nodes (or null links if the DLL is empty)

 * @author Kin Shing Chong
 *
 * @param <E> the type of elements held in this collections
 */
@SuppressWarnings("serial")
public class MyDLL<E> implements ListADT<E> {


	transient int size = 0;

	/**
	 * Pointer to first node.
	 */
	/**
	 * 
	 */
	transient MyDLLNode<E> first;


	/**
	 * Pointer to last node.
	 */
	/**
	 * 
	 */
	transient MyDLLNode<E> last;


	// Constructs empty list.
	/**
	 * 
	 */
	public MyDLL() {
		this.first =this.last =null;
	}




	/**
	 * Node structure with node constructor,Each node contains a single element,
	 *  plus links to the node’s successor  and predecessor 
	 *  (or null links if head/tail)The DLL header contains links to
	 *  the DLL’s first and last nodes (or null links if the DLL is empty)
	 */
	static class MyDLLNode<E> {
		private E item;
		private MyDLLNode<E> next, prev;


		MyDLLNode(MyDLLNode<E> prev, E element, MyDLLNode<E> next) {
			this.prev = prev;
			this.item = element;
			this.next = next;
		}


	}

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
	@Override
	public int size() {
		return size;
	}

    /**
     * Removes all of the elements from this list.
     * The list will become empty after this call returns.
     */
	@Override
	public void clear() {
		for (MyDLLNode<E> x = first; x != null; ) {
			MyDLLNode<E> next = x.next;
			x.item = null;
			x.next = null;
			x.prev = null;
			x = next;
		}
		first = last = null;
		size = 0;		
	}

	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) and any subsequent
	 * elements to the right (adds one to their indices).
	 * 
	 * @param index
	 * 			The index at which the specified element is to be inserted.
	 * 			The element is inserted before the existing element at [index],
	 * 			or at the end if index is equal to the size
	 * @param toAdd
	 * 			The element to be inserted.
	 * @return trueif the element is added successfully.
	 * @throws NullPointerException
	 * 			If the specified element is null.
	 * @throws IndexOutOfBoundsException
	 * 			If the index is out of range: 
	 * 			i.e. (index>size || index<0).
	 */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (toAdd==null) {
			throw new NullPointerException("The specific Element is Null.");
		}
		if (index>size || index<0) {
			throw new IndexOutOfBoundsException("Index is out of range.");
		}else if (index == size) {
			add(toAdd);
			size++;
			return true;
		}else if (index>=0 && index<size) {
			MyDLLNode<E> current = first;
			int position=0;
			while (this.iterator().hasNext() && position<index) {
				current=current.next;
				position++;
			}
			MyDLLNode<E> pred = current.prev;
			MyDLLNode<E> newNode = new MyDLLNode<E>(pred, toAdd, current);
			current.prev = newNode;
			if (pred == null)
				first = newNode;
			else
				pred.next = newNode;
			size++;
			return true;
		}else
			return false;
	}

    /**
     * Appends the specified element to the end of this list.
     * return true if the element is successfully added
     * 
     * @param toAdd element to be appended to this list
     * @return true if the element is successfully added
     * @throws NullPointerException when the element is null
     */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd==null) {
			throw new NullPointerException("The specific Element is Null.");
		}
		MyDLLNode<E> l = last;
		MyDLLNode<E> newNode = new MyDLLNode<E>(l, toAdd, null);
		last = newNode;
		if (l == null)
			first = newNode;
		else
			l.next = newNode;
		size++;
		return true;
	}

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the iterator.  
     * return true if appended successfully
     *
     * @param toAdd collection containing elements to be added to this list
     * @return true if appended successfully
     * @throws NullPointerException if the specified collection is null
     */
	@Override
	public boolean addAll( ListADT<? extends E> toAdd ) throws NullPointerException {
		if (toAdd==null) {
			throw new NullPointerException("The specific Element is Null.");
		}
		Iterator<? extends E> iterator = toAdd.iterator();
		while(iterator.hasNext())
			if(!add(iterator.next())) {
				return false;
			}
		return true;
	}

	/**
	 * Returns the element at the specified index(position) in this list.
	 * 
	 * @param index
	 * 			Index of element to return. zero-based
	 * @return The element at the specified position in this list.
	 * @throws IndexOutOfBoundsException
	 * 			If the index is out of range: 
	 * 			i.e. (index>size || index<0).
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index>size || index<0) {
			throw new IndexOutOfBoundsException("Index is out of range.");
		}
		MyDLLNode<E> current = first;
		if (index>=0 && index<size) {
			int position=0;
			while (this.iterator().hasNext() && position<index) {
				current=current.next;
				position++;
			}
		} 
		return current.item;
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the lower position (subtracts one from their indices).
	 * Returns the element that was removed from the list.
	 * 
	 * @param index
	 * 			The index of the element to remove. zero-based
	 * @return The removed element.
	 * @throws IndexOutOfBoundsException
	 * 			If the index is out of range: 
	 * 			i.e. (index>size || index<0).
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index>size || index<0) {
			throw new IndexOutOfBoundsException("Index is out of range.");
		}
		MyDLLNode<E> current = first;
		if (index>=size) throw new IndexOutOfBoundsException();
		else if (index>0 && index<size) {
			int position=0;
			while (this.iterator().hasNext() && position<index) {
				current=current.next;
				position++;
			}
			current.prev.next=current.next;
			current.next.prev=current.prev;
		} else if (index==0) {
			if (size!=1) {
				first.next.prev=null;
				first=first.next;
			}else {
				first=null;
				last=null;
			}
		}

		size--;
		return current.item;
	}

	/**
	 * Removes the first occurrence in this list of the specified element. If
	 * this list does not contain the element, it is unchanged. Different approach
	 * is used for different posiotn of the element
	 * 
	 * @param toRemove
	 * 			The element to be removed from this list.
	 * @return The element which is being removed, or null if the list does
	 * 			not contain the element.
	 * @throws NullPointerException
	 * 			If the specified element is null.
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (toRemove==null) {
			throw new NullPointerException("The specific Element is Null.");
		}

		MyDLLNode<E> current = first;
		int position=0;
		while (current.item!=toRemove) {
			if (this.iterator().hasNext()) {
			current=current.next;
			position++;
			}else return null;
		}
		if (position==0) {
			first.next.prev = null;
			first = first.next;
			size--;
			return current.item;
		}else if (current.next!=null) {   	
			current.prev.next=current.next;
			current.next.prev=current.prev;
			size--;
			return current.item;		
		}else {
			last.prev.next=null;
			last=last.prev;
			size--;
			return toRemove;
		}

	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 * 
	 * @param index
	 * 			The index of the element to replace.zero-based
	 * @param toChange
	 * 			Element to be stored at the specified position.
	 * @return The element previously at the specified position.
	 * @throws NullPointerException
	 * 			If the specified element is null
	 * @throws IndexOutOfBoundsException
	 * 			If the index is out of range: 
	 * 			i.e. (index>size || index<0).
	 */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (toChange==null) {
			throw new NullPointerException("The specific Element is Null.");
		}
		if (index>size || index<0) {
			throw new IndexOutOfBoundsException("Index is out of range.");
		}
		MyDLLNode<E> current = first;
		if (index>=0 && index<size) {
			int position=0;
			while (this.iterator().hasNext() && position<index) {
				current=current.next;
				position++;
			}
		} 
		current.item=toChange;
		return current.item;
	}

	/**
	 * Returns true if this list contains no elements.
	 * 
	 * @return true if this list contains no elements.
	 */
	@Override
	public boolean isEmpty() {
		
		if (this.first==null && this.last==null && this.size()==0) {
			return true;
		}else
			return false;
	}

	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one 
	 * element toFind.
	 * 
	 * @param toFind
	 * 			The element whose presence in this list is to be tested.
	 * @return true if this list contains the specified element.
	 * @throws NullPointerException
	 * 			If the specified element is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {

		if (toFind==null) {
			throw new NullPointerException("The specific Element is Null.");
		}else {
			for (MyDLLNode<E> x = first; x != null; x = x.next) {
				if (toFind.equals(x.item))
					return true;
				//index++;
			}
		}
		return false;

	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence; the runtime type of the returned array is that of the specified
	 * array. 
	 * 
	 * @param toHold
	 *			The array into which the elements of this list are to be
	 * 			stored, if it is big enough; otherwise, a new array of the
	 * 			same runtime type is allocated for this purpose.
	 * @return An array containing the elements of this list.
	 * @throws NullPointerException
	 * 			If the specified array is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if (toHold.length < size) {
			toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
		}
		int i = 0;
		Object[] result = toHold;
		for (MyDLLNode<E> x = first; x != null; x = x.next)
			result[i++] = x.item;

		if (toHold.length > size)
			toHold[size] = null;

		return toHold;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence. 
	 * 
	 * @return An array containing all of the elements in this list in proper
	 * 			sequence.
	 */
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		int i = 0;
		for (MyDLLNode<E> x = first; x != null; x = x.next)
			result[i++] = x.item;
		return result;
	}

	/**
	 * Returns an iterator over the elements in this list, in proper sequence.
	 * 
	 * @return An iterator over the elements in this list, in proper sequence.
	 * 			NB: The return is of type Utilities.Iterator notjava.util.Iterator.
	 */
	@Override
	public Iterator<E> iterator() {
		//int nextIndex;        
		Iterator<E> it = new Iterator<E>() {
			MyDLLNode<E> current=first;


			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public E next() throws NoSuchElementException {
				if (!hasNext()) throw new NoSuchElementException();
				E temp=current.item;
				current = current.next;
				return temp;
			}

		};
		return it;
	}


}
