package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import structures.Queue;
import exceptions.QueueUnderflowException;

public class QueueTest 
{
	Queue<Integer> queue;
	
	@Before
	public void before() 
	{
		queue = new Queue<Integer>();
	}
	
	@Test (timeout = 100, expected = QueueUnderflowException.class)
	public void testQueueUnderflowException() 
	{
		queue.dequeue();
	}
	
	@Test
	public void testEnqueueDequeue() 
	{
		queue.enqueue(5);
		Integer int1 = queue.dequeue();
		assertEquals(new Integer(5), int1);
		assertTrue(queue.isEmpty());
	}
	
	@Test
	public void testEnqueueDequeue2() 
	{
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		Integer int1 = queue.dequeue();
		assertEquals(new Integer(5), int1);
		Integer int2 = queue.dequeue();
		assertEquals(new Integer(6), int2);
		assertFalse(queue.isEmpty());
		Integer int3 = queue.dequeue();
		assertEquals(new Integer(7), int3);
		assertTrue(queue.isEmpty());
	}
	
}
