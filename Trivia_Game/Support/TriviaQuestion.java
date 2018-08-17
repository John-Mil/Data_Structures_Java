// Objects of this class represent individual trivia questions

package support;

import exceptions.InvalidInputException;

public class TriviaQuestion 
{
	
	private String category;		//Category of this question
	private String question;		//The question being asked
	private AnswerLog answers;		//Acceptable answers for this question
	
	public TriviaQuestion(String category, String question, int maxNumAnswers)
	{
		if(maxNumAnswers <= 0)
			throw new InvalidInputException("Number of acceptable answers for TriviaQuestion must be greater than 0");
		this.category = category;
		this.question = question;
		answers = new AnswerLog(maxNumAnswers);
	}
	
	public String getCategory() {return category;}
	
	public String getQuestion() {return question;}
	
	public void storeAnswer(String answer) 
	//Allows the client to store the correct answer(s) for this TriviaQuestion
	//Inserts answer into the AnswerLog for this TriviaQuestion
	{
		answers.insert(answer);
	}
	
	public boolean tryAnswer(String attempt) 
	//Identifies whether attempt is a valid answer to this TriviaQuestion
	//Returns true if attempt is included in the AnswerLog
	{
		return answers.contains(attempt);
	}
	
	public String toString() 
	{
		return "Category: " + category + "\nQuestion: " + question;
	}
	
}
