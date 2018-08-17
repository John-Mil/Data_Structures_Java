// Class that represents the underlying structure of trivia questions
// The possible correct answers of a TriviaQuestion are stored in an AnswerLog object

package support;

import exceptions.AnswerLogOverflowException;

public class AnswerLog 
{
	protected String[] log;			//String array holding the answers
	protected int lastIndex;
	
	public AnswerLog(int size) 
	//Create an AnswerLog object
	{
		log = new String[size];
		lastIndex = -1;
	}
	
	public void insert(String elem) 
	//Insert a String into the ArrayLog
	{
		if(isFull())
			throw new AnswerLogOverflowException("Answer not added: Maximum number of answers reached");
		lastIndex++;
		log[lastIndex] = elem;
	}
	
	public boolean contains(String elem) 
	//O(N) linear search in array
	//Returns true if the ArrayLog contains an element e such that e.equals(elem)
	//Otherwise, returns false
	{
		int index = 0;
		boolean found = false;
		while((index <= lastIndex) && (!found))
			if(elem.equalsIgnoreCase(log[index])) 
				found = true;
			else
				index++;
		return found;
	}
	
	public boolean isFull() 
	{
		return (lastIndex == (log.length - 1));
	}
	
	public int size() 
	{
		return (lastIndex + 1);
	}
	
}
