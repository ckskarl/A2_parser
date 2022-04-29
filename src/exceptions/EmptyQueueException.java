package exceptions;

/**
 * Exception handler whenever the input queue is empty.
 * 
 * @author Alex Fleury
 * @version 08/11/2021
 */

@SuppressWarnings("serial")
public class EmptyQueueException extends Exception
{
	/**
	 * Exception handler whenever the input queue is empty.
	 */
	public EmptyQueueException()
	{
		super();
	}

	/**
	 * Constructor which allows a specific message to be passed through as a argument.
	 * 
	 * @param message error message specific to cause of error.
	 */
	public EmptyQueueException(String message)
	{
		super(message);
	}
}

