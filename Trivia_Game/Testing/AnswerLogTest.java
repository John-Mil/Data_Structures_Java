package triviaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.AnswerLogOverflowException;
import support.AnswerLog;

public class AnswerLogTest 
{
	
	AnswerLog ans;
	
	@Before
	public void before() 
	{
		ans = new AnswerLog(3);
		ans.insert("Bills");
		ans.insert("Giants");
		ans.insert("Jets");
	}
	
	@Test (timeout = 100, expected = AnswerLogOverflowException.class)
	public void testAnswerLogOverflowException() 
	{
		ans.insert("Patriots");
	}
	
	@Test
	public void testContains() 
	{
		assertTrue(ans.contains("Bills"));
		assertTrue(ans.contains("Giants"));
		assertTrue(ans.contains("Jets"));
		assertFalse(ans.contains("Patriots"));
	}
	
	@Test
	public void testFull() 
	{
		assertTrue(ans.isFull());
	}
	
	@Test
	public void testSize() 
	{
		assertEquals(3, ans.size());
	}
	
}
