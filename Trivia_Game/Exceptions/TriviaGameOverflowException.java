package exceptions;

public class TriviaGameOverflowException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TriviaGameOverflowException() 
	{
		super();
	}
	
	public TriviaGameOverflowException(String message) 
	{
		super(message);
	}
}
