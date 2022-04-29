package structures;

import exceptions.EmptyQueueException;
import utilities.*;

/**
 * This is the implementation class for the QueueADT interface. This
 * implementation contains all standard Queue operations for data structure
 * management.
 * 
 * @author Alex Fleury
 * @version 10/11/2021
 *
 * @param <E> Generic placeholder for abstract data handling
 */
@SuppressWarnings("serial")
public class MyQueue<E> implements QueueADT<E> {

	private MyDLL<E> myData;
	private int queueSize, capacity;

	/**
	 * Default constructor that initializes a MyDLL node list and size.
	 */
	
	public MyQueue() {
		myData = new MyDLL<E>();
		queueSize = myData.size;
	}

	/**
	 * Secondary constructor that allows the user to specify the maximum size of the
	 * queue being instantiated.
	 * 
	 * @param setSize set the initial size for the queue
	 */
	public MyQueue(int setSize) {
		myData = new MyDLL<E>();
		queueSize = myData.size;
		capacity = setSize;
	}

	/**
	 * Will place the specified object at the last position in the queue. This
	 * method will not allow null values. Calls the method add(E toAdd) in the MyDLL
	 * class to complete task.
	 * 
	 * @param toAdd user specified object to be added.
	 * @throws NullPointerException raised when a null object is passed into the
	 *                              method.
	 */
	@Override
	public void enqueue(E toAdd) throws NullPointerException {
		myData.add(toAdd);
		queueSize = myData.size;
	}

	/**
	 * Will remove the first object that was placed into the Queue. Utilizes the
	 * MyDLL class method remove(int index);
	 * 
	 * @return The object that was removed.
	 */
	@Override
	public E dequeue() throws EmptyQueueException {
		if (queueSize == 0) {
			throw new EmptyQueueException();
		}
		queueSize--;
		return myData.remove(0);
	}

	/**
	 * Will provide a reference to the first object in the queue, but will not
	 * remove the object from the queue. Utilizes the MyDLL class get(int index)
	 * method.
	 * 
	 * @return Reference to the first object in queue.
	 */
	@Override
	public E peek() throws EmptyQueueException {
		if (myData.first == null && myData.last == null) {
			throw new EmptyQueueException("Queue is empty");
		} else {
			return (E) myData.get(0);
		}
	}

	/**
	 * Will remove every object in the queue, resulting in an empty queue. Utilizes
	 * the MyDLL clear() method.
	 */
	@Override
	public void dequeueAll() {
		myData.clear();
		queueSize = myData.size;
	}

	/**
	 * Will provide a boolean true value when the queue contains no objects.
	 * Utilizes the MyDLL isEmpty() method.
	 * 
	 * @return true when the queue length is zero.
	 */
	@Override
	public boolean isEmpty() {
		return (myData.isEmpty());
	}

	/**
	 * Returns an iterator over the objects in this queue in sequential order from
	 * first to last. Implements the MyDLL iterator() method.
	 * 
	 * @return iterator over the queue objects.
	 */
	@Override
	public Iterator<E> iterator() {
		return myData.iterator();
	}

	/**
	 * Will compare two MyQueue ADT's to determine whether they contain equal
	 * objects that appear in the same order. Utilizes the MyDLL toArray() and
	 * size() methods.
	 * 
	 * @return true if queues are equal, or, false if they are not equal.
	 */
	@Override
	public boolean equals(QueueADT<E> that) {

		Object[] array1 = myData.toArray();
		Object[] array2 = that.toArray();
		for (int i = 0; i < myData.size(); i++) {
			if (array1[i] != array2[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns an array that contains all of the elements in the queue in proper
	 * sequence. Obeys the general contact of the Collection.toArray method.
	 * Utilizes the MyDLL toArray() method.
	 * 
	 * @return array containing all queue elements in the queue sequence.
	 */
	@Override
	public Object[] toArray() {
		return myData.toArray();
	}

	/**
	 * Returns an array containing all the elements in the queue in proper sequence.
	 * The array that is returned is the array object that was passed into this
	 * method. Utilizes the MyDLL toArray(E toHold) method.
	 * 
	 * @param holder the array the method will insert elements from the queue. If
	 *               the specified array is not large enough, a new array of the
	 *               same runtime type is generated for this purpose.
	 * @return an array containing all queue elements in the proper queue sequence.
	 * @throws NullPointerException if the specified array is null.
	 */
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {

		return myData.toArray(holder);
	}

	/**
	 * Will check if the queue contains the equal number of elements compared to the
	 * specified user queue capacity. This method is only implemented when a fixed
	 * length queue is constructed. Utilizes the MyDLL iterator() method.
	 * 
	 * @return true if the queue size is equal to the queue capacity.
	 */
	@Override
	public boolean isFull() {
		int position = 0;
		while (this.iterator().hasNext()) {
			this.iterator().next();
			position++;
		}
		return (capacity == position);
	}

	/**
	 * Will return the length of the current queue as an integer value.
	 * 
	 * @return integer representation of the current queue size.
	 */
	@Override
	public int size() {
		return (queueSize);
	}
}
