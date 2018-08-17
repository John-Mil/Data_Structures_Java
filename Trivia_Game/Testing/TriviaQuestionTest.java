package triviaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidInputException;
import support.TriviaQuestion;
import exceptions.AnswerLogOverflowException;

public class TriviaQuestionTest 
{
	
	TriviaQuestion q1;
	TriviaQuestion q2;
	
	@Before
	public void before() 
	{
		q1 = new TriviaQuestion("Sports", "Name football teams from NY", 3);
		q1.storeAnswer("Giants");
		q1.storeAnswer("Jets");
		q1.storeAnswer("Bills");
	}
	
	@Test (timeout = 100, expected = InvalidInputException.class)
	public void testInvalidIndexException()
	{
		q2 = new TriviaQuestion("Geography", "What is the capital city of NY?", 0);
	}
	
	@Test (timeout = 100, expected = AnswerLogOverflowException.class)
	public void storeAnswerTest() 
	{
		q1.storeAnswer("Mets");
	}
	
	@Test
	public void testGetCategory()
	{
		assertEquals("Sports", q1.getCategory());
	}
	
	@Test
	public void testGetQuestion() 
	{
		assertEquals("Name football teams from NY?", q1.getQuestion());
	}
	
	@Test
	public void testTryAnswer()
	{
		assertTrue(q1.tryAnswer("Giants"));
		assertTrue(q1.tryAnswer("Jets"));
		assertTrue(q1.tryAnswer("Bills"));
		assertFalse(q1.tryAnswer("Patriots"));
	}
}
