package triviaTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import support.TriviaGame;
import support.TriviaQuestion;
import exceptions.*;

public class TriviaGameTest 
{
	TriviaGame tg;
	TriviaQuestion tq1;
	TriviaQuestion tq2;
	
	@Before
	public void Before() 
	{
		tg = new TriviaGame("Quiz 1", 2, 3);
		tq1 = new TriviaQuestion("Sports", "Name NY Baseball Teams", 2);
		tq1.storeAnswer("Mets"); tq1.storeAnswer("Yankees");
		tq2 = new TriviaQuestion("Geography", "Name capital of NY", 1);
		tq2.storeAnswer("Albany");
	}
	
	@Test
	public void testGetQuizName() 
	{
		assertEquals("Quiz 1", tg.getQuizName());
	}
	
	@Test
	public void testGetNumberChances() 
	{
		assertEquals(3, tg.getNumChances());
	}
	
	@Test
	public void testGetCurrNumQuestions() 
	{
		tg.insertQuestion(tq1);
		tg.insertQuestion(tq2);
		assertEquals(2, tg.getCurrNumQuestions());
	}
	
	@Test
	public void testInsertQuestion() 
	{
		tg.insertQuestion(tq1);
		assertEquals(1, tg.getCurrNumQuestions());
		tg.insertQuestion(tq2);
		assertEquals(2, tg.getCurrNumQuestions());
	}
	
	@Test (timeout = 100, expected = TriviaGameOverflowException.class)
	public void testTriviaGameOverflowException() 
	{
		TriviaQuestion tq3 = new TriviaQuestion("Sports", "Name an NFL team", 32);
		tg.insertQuestion(tq1);
		tg.insertQuestion(tq2);
		tg.insertQuestion(tq3);
	}
	
	@Test
	public void testGetTriviaQuestion() 
	{
		tg.insertQuestion(tq1);
		tg.insertQuestion(tq2);
		assertEquals(tq1, tg.getTriviaQuestion(1));
		assertEquals(tq2, tg.getTriviaQuestion(2));
	}
	
	@Test (timeout = 100, expected = InvalidInputException.class)
	public void testInvalidInputException() 
	{
		tg.getTriviaQuestion(1);
	}
	
	@Test
	public void testGameTrial() 
	{
		tg.insertQuestion(tq1);
		tg.insertQuestion(tq2);
		tg.correctAnswer(1);
		tg.incorrectAnswer(2);
		assertEquals(1, tg.getNumCorrect());
		assertEquals(1, tg.getNumIncorrect());
		assertEquals(1, tg.getRemainingChances());
		assertTrue(tg.isAnswered(1));
		assertFalse(tg.isOver());
		tg.correctAnswer(2);
		assertTrue(tg.isOver());
		assertEquals(2, tg.getNumCorrect());
	}
	
	
}
