//This class provides methods that obtain information about a trivia game,
//create and return TriviaGame objects

//Note: currently one option is provided that obtains TriviaGame information from a text file
//Text file is organized as follows ...
/*
 * Line 1: quiz name
 * Line 2: number of questions
 * Line 3: number of chances allowed
 * For each question:
 *    Line a: category
 *    Line b: the question
 *    Line c: number of acceptable answers
 *    For each acceptable answer:
 *       Line 1: the answer
 *       
 */

package support;

import java.util.*;
import java.io.*;

public class GetTriviaGame 
{
	public static TriviaGame useTextFile(String textFile) throws IOException
	//Precondition: The textFile exists and contains a correctly formatted game
	//
	//
	{
		TriviaGame game;
		
		String quizName;
		int numQuestions;
		int numChances;
		
		//for a specific trivia question
		TriviaQuestion tq;
		String category;
		String question;
		String answer;
		int numAnswers;
		
		FileReader fin = new FileReader(textFile);
		Scanner scan = new Scanner(fin);
		String skip; 		//skip end of line after reading integer
		
		//Scan in basic trivia quiz information and set variables
		quizName = scan.nextLine();
		numQuestions = scan.nextInt();
		skip = scan.nextLine();
		numChances = scan.nextInt();
		skip = scan.nextLine();
		
		//Instantiate the TriviaGame
		game = new TriviaGame(quizName, numQuestions, numChances);
		
		//Scan in and set up each question
		for(int i = 1; i <= numQuestions; i++) 
		{
			category = scan.nextLine();
			question = scan.nextLine();
			numAnswers = scan.nextInt();
			skip = scan.nextLine();
			tq = new TriviaQuestion(category, question, numAnswers);
			for(int j = 1; j <= numAnswers; j++) 
			{
				answer = scan.nextLine();
				tq.storeAnswer(answer);
			}
			game.insertQuestion(tq);
		}
		scan.close();
		return game;
	}
}
