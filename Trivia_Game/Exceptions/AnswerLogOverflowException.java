package exceptions;

public class AnswerLogOverflowException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AnswerLogOverflowException() 
	{
		super();
	}
	
	public AnswerLogOverflowException(String message) 
	{
		super(message);
	}
}
