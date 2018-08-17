//Allows the user to play a trivia game with the use of a console interface
//Note: This assumes there is a text file (game.txt) that exists and
//is in proper format to generate a Trivia Game


package application;

import java.io.*;
import java.util.Scanner;
import support.*;

public class TriviaConsole 
{
	
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		
		TriviaGame game;		//The trivia game
		
		int questNum;			//Current question number
		TriviaQuestion tq;		//Current question
		String answer;			//Answer provided by user
		
		//Initialize game
		game = GetTriviaGame.useTextFile("game.txt");
		
		//Greet the User
		System.out.println("Welcome to the " + game.getQuizName() + " trivia quiz.");
		System.out.println("You will have " + game.getNumChances() + " chances to answer " + game.getCurrNumQuestions() + " questions. \n");
		
		questNum = 0;
		
		while(!game.isOver()) 
		{
			//Get the number of next unanswered questions
			do 
			{
				if(questNum == game.getCurrNumQuestions())
					questNum = 1;
				else
					questNum++;
			} while(game.isAnswered(questNum));
			
			//Ask question to user and handle response
			tq = game.getTriviaQuestion(questNum);
			System.out.println("Question " + questNum + "\n" + tq);
			answer = scan.nextLine();
			if(tq.tryAnswer(answer)) 
			{
				System.out.println("\nCorrect!");
				game.correctAnswer(questNum);
			}
			else 
			{
				System.out.println("\nIncorrect");
				game.incorrectAnswer(questNum);
			}
			
			System.out.println(game.getRemainingChances() + " chances remaining \n");
			
		}
		
		
		System.out.println("Game Over");
		System.out.println("Results ... \n");
		
		System.out.println("Chances Used: " + (game.getNumChances() - game.getRemainingChances()));
		System.out.println("Number Correct: " + game.getNumCorrect());
		
		System.out.println("\nGood Job!\n");
		
		
	}

}












