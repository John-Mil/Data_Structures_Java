// Objects of this class represent trivia games

package support;

import exceptions.*;

public class TriviaGame 
{
	private String quizName;			//Name of this trivia game
	private int maxNumQuestions, numChances, remainingChances, numCorrect, numIncorrect;
	private TriviaQuestion[] questions; //The set of questions for this trivia game
	private boolean[] correct;			//Tracks if the corresponding question has been answered correctly
	
	private int currNumQuestions;		//Current number of questions that have been added to this Trivia Game
	
	public TriviaGame(String quizName, int maxNumQuestions, int numChances)  
	//Creates a new TriviaGame object
	{
		if(maxNumQuestions <= 0)
			throw new InvalidInputException("Number of questions for TriviaGame must be greater than 0");
		if(numChances <= 0)
			throw new InvalidInputException("Number of chances for Trivia Game must be greater than 0");
		this.quizName = quizName;
		this.maxNumQuestions = maxNumQuestions;
		this.numChances = remainingChances = numChances;
		questions = new TriviaQuestion[maxNumQuestions];
		correct = new boolean[maxNumQuestions];
		currNumQuestions = numCorrect = numIncorrect = 0;
	}

	public String getQuizName() {return quizName;}
	
	public int getNumChances() {return numChances;} 
	
	public int getRemainingChances() {return remainingChances;}
	
	public int getNumCorrect() {return numCorrect;}
	
	public int getNumIncorrect() {return numIncorrect;}
	
	public int getCurrNumQuestions() {return currNumQuestions;}
	
	public TriviaQuestion getTriviaQuestion(int questionNumber) 
	//Returns Trivia Question number questionNumber
	{
		if((questionNumber < 0) || (questionNumber > currNumQuestions))
			throw new InvalidInputException("Question Number not in range of the number of questions in Trivia Game");
		return questions[questionNumber - 1];
	}
	
	public void insertQuestion(TriviaQuestion question) 
	//Adds a TriviaQuestion to this TriviaGame
	{
		if(question == null)
			throw new NullPointerException("Must insert a valid Trivia Question");
		if(currNumQuestions >= questions.length)
			throw new TriviaGameOverflowException("Questions not added: Trivia Game max questions reached");
		questions[currNumQuestions] = question;
		correct[currNumQuestions] = false;
		currNumQuestions++;
	}
	
	public void correctAnswer(int questionNumber) 
	//Updates game status to indicate that question number questionNumber
	//was answered correctly
	{
		if((questionNumber < 0) || (questionNumber > currNumQuestions))
			throw new InvalidInputException("Question Number not in range of the number of questions in Trivia Game");
		correct[questionNumber - 1] = true;
		numCorrect++;
		remainingChances--;
	}
	
	public void incorrectAnswer(int questionNumber) 
	//Updates game status to indicate that question number questionNumber
	//was answered incorrectly
	{
		if((questionNumber < 0) || (questionNumber > currNumQuestions))
			throw new InvalidInputException("Question Number not in range of the number of questions in Trivia Game");
		numIncorrect++;
		remainingChances--;
	}
	
	public boolean isAnswered(int questionNumber) 
	//Returns true if Trivia Question number questionNumber has been answered
	//correctly, otherwise returns false
	{
		if((questionNumber < 0) || (questionNumber > currNumQuestions))
			throw new InvalidInputException("Question Number not in range of the number of questions in Trivia Game");
		return correct[questionNumber - 1];
	}
	
	public boolean isOver() 
	//Returns true if this Trivia Game is complete
	//otherwise returns false
	{
		return ((remainingChances <= 0) || (numCorrect == currNumQuestions));
	}
	
}
