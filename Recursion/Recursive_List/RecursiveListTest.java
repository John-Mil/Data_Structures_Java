package testing;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import structures.*;

public class RecursiveListTest 
{
	RecursiveList<Integer> list;
	
	@Before
	public void before() 
	{
		list = new RecursiveList<Integer>();
	}
	
	@Test
	public void testInsertFirst() 
	{
		list.insertFirst(3);
		assertEquals(new Integer(3), list.getFirst());
		assertEquals(1, list.size());
		list.insertFirst(9);
		assertEquals(new Integer(9), list.getFirst());
		assertEquals(2, list.size());
	}
	
	@Test
	public void testInsertLast() 
	{
		list.insertLast(3);
		assertEquals(new Integer(3), list.getLast());
		assertEquals(1, list.size());
		list.insertLast(9);
		assertEquals(new Integer(9), list.getLast());
		assertEquals(2, list.size());
	}
	
	@Test
	public void testInsertFirstAndLast() 
	{
		list.insertFirst(5);
		list.insertLast(9);
		list.insertLast(3);
		list.insertFirst(11);
		assertEquals(new Integer(3), list.getLast());
		assertEquals(new Integer(11), list.getFirst());
	}
	
	@Test
	public void testInsertAt() 
	{
		list.insertAt(0, 3);
		list.insertAt(1, 5);
		list.insertAt(2, 7);
		assertEquals(new Integer(3), list.get(0));
		assertEquals(new Integer(5), list.get(1));
		assertEquals(new Integer(7), list.get(2));
		
		assertEquals(new Integer(3), list.removeAt(0));
		assertEquals(new Integer(5), list.removeAt(0));
		assertEquals(new Integer(7), list.removeAt(0));
	}
	
	@Test
	public void testInsertAt2() 
	{
		list.insertAt(0, 9); //9
		list.insertAt(0, 12); //12 9
		list.insertAt(1, 3); //12 3 9
		
		assertEquals(new Integer(9), list.get(2));
		assertEquals(new Integer(3), list.get(1));
		assertEquals(new Integer(12), list.get(0));
		
		assertEquals(new Integer(3), list.removeAt(1)); //12 9
		assertEquals(new Integer(12), list.removeAt(0)); //9
		assertEquals(new Integer(9), list.removeAt(0));
		
		assertTrue(list.isEmpty());
	}
	
	@Test (timeout = 100, expected = IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsException() 
	{
		list.insertAt(1, 12);
	}
	
	@Test
	public void testIndexOf() 
	{
		list.insertAt(0, 3); //3
		list.insertAt(0, 5); //5 3
		list.insertAt(1, 9); //5 9 3
		
		assertEquals(-1, list.indexOf(new Integer(1)));
		assertEquals(1, list.indexOf(new Integer(9)));
		assertEquals(0, list.indexOf(new Integer(5)));
		assertEquals(2, list.indexOf(new Integer(3)));
	}
	
	@Test
	public void testRemoveLast() 
	{
		list.insertAt(0, 3); //3
		list.insertAt(0, 5); //5 3
		list.insertAt(1, 9); //5 9 3
		
		assertEquals(new Integer(3), list.removeLast());
		assertEquals(new Integer(9), list.removeLast());
		assertEquals(new Integer(5), list.removeLast());
	}
	
	@Test (timeout = 100, expected = IllegalStateException.class)
	public void testIllegalStateException() 
	{
		list.removeLast();
	}
	
	@Test
	public void testIterator() 
	{
		list.insertFirst(6);
		list.insertFirst(7);
		list.insertFirst(8);
		Iterator<Integer> itr = list.iterator();
		
		assertEquals(new Integer(8), itr.next());
		assertEquals(new Integer(7), itr.next());
		assertEquals(new Integer(6), itr.next());
		assertEquals(null, itr.next());
	}
	
}
