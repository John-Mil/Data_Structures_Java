package structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import comparator.IntegerComparator;
import java.util.List;

public class HeapTest 
{
	private Heap<Integer, String> heap;
	
	@Before
	public void setup() 
	{
		heap = new Heap<Integer, String>(new IntegerComparator());
	}
	
	@Test
	public void testHeap() 
	{
		heap.add(10, "High Priority");
		heap.add(5, "Medium Priority");
		heap.add(0, "Low Priority");
		heap.add(20, "Very High Priority");
		assertEquals(4, heap.size());
		
		assertEquals("Very High Priority", heap.peek());
		heap.remove(); //removes "Very High Priority"
		
		assertEquals("High Priority", heap.peek());
		heap.remove(); //removes "High Priority"
		
		assertEquals("Medium Priority", heap.peek());
		heap.remove(); //removes "Medium Priority"
		
		assertEquals("Low Priority", heap.peek());
		heap.remove(); //removes "Low Priority"
		
		assertTrue(heap.isEmpty());
	}
	
	@Test
	public void testBubbleUpAndBubbleDown() 
	{
		List<Entry<Integer, String>> list = heap.asList();
		
		heap.add(100, "100"); heap.add(50, "50"); heap.add(25, "25"); heap.add(75, "75");
		
		//Test the tree as is
		assertEquals("100", list.get(0).getValue());
		assertEquals("75", list.get(1).getValue());
		assertEquals("25", list.get(2).getValue());
		assertEquals("50", list.get(3).getValue());
		
		heap.remove(); //removed "100"
		//Test BubbleDown
		assertEquals("75", list.get(0).getValue());
		assertEquals("50", list.get(1).getValue());
		assertEquals("25", list.get(2).getValue());
		
		heap.add(60, "60");
		//Test Bubble Up
		assertEquals("75", list.get(0).getValue());
		assertEquals("60", list.get(1).getValue());
		assertEquals("25", list.get(2).getValue());
		assertEquals("50", list.get(3).getValue());
		
		heap.add(40, "40"); heap.add(30, "30");
		assertEquals("30", list.get(2).getValue());
		
		heap.remove();
		//Test BubbleDown
		assertEquals("60", list.get(0).getValue());
		assertEquals("50", list.get(1).getValue());
		assertEquals("30", list.get(2).getValue());
		assertEquals("25", list.get(3).getValue());
		assertEquals("40", list.get(4).getValue());
	}
	
	@Test 
	public void testIndiceFunctions()
	{
	    assertEquals(1, heap.getLeftChildOf(0));
	    assertEquals(2, heap.getRightChildOf(0));
	    assertEquals(3, heap.getLeftChildOf(1));
	    assertEquals(4, heap.getRightChildOf(1));
	    assertEquals(5, heap.getLeftChildOf(2));
	    assertEquals(6, heap.getRightChildOf(2));
	    assertEquals(0, heap.getParentOf(1));
	    assertEquals(0, heap.getParentOf(2));
	    assertEquals(1, heap.getParentOf(3));
	    assertEquals(1, heap.getParentOf(4));
	    assertEquals(2, heap.getParentOf(5));
	    assertEquals(2, heap.getParentOf(6));
	}
	
	@Test (timeout = 100, expected = IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsException1()
	{
		heap.getLeftChildOf(-1);
	}
		
	@Test (timeout = 100, expected = IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsException2()
	{
		heap.getRightChildOf(-1);
	}
		
	@Test (timeout = 100, expected = IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsException3()
	{
		heap.getParentOf(0);
	}
}
