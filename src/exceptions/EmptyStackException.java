
package exceptions;

/**
 * Exception handler whenever the input stack is empty.
 */
@SuppressWarnings("serial")
public class EmptyStackException extends RuntimeException
{
	public EmptyStackException()
	{
		super();
	}

	/**
	 * @param message error message specific to cause of error.
	 */
	public EmptyStackException(String message)
	{
		super(message);
	}
}
