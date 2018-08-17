package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import structures.Stack;

public class StackTest 
{
	Stack<Integer> stack;
	
	@Before
	public void before() 
	{
		stack = new Stack<Integer>();
		stack.push(5);
		stack.push(4);
		stack.push(3);
	}
	
	@Test
	public void testIsEmpty() 
	{
		assertFalse(stack.isEmpty());
		stack.pop();
		stack.pop();
		stack.pop();
		assertTrue(stack.isEmpty());
	}
	
	@Test
	public void testSize() 
	{
		assertEquals(3, stack.size());
		stack.pop();
		stack.pop();
		assertEquals(1, stack.size());
		stack.pop();
		assertEquals(0, stack.size());
	}
	
	@Test (timeout = 100, expected = StackUnderflowException.class)
	public void testStackUnderflowException() 
	{
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
	}
	
	@Test
	public void testTop() 
	{
		assertEquals(new Integer(3), stack.top());
		stack.pop();
		assertEquals(new Integer(4), stack.top());
		stack.pop();
		assertEquals(new Integer(5), stack.top());
		stack.pop();
		assertEquals(null, stack.top());
		stack.push(9);
		assertEquals(new Integer(9), stack.top());
	}
}
