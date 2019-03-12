// Heap based priority queue
// See Heap class for implementation of methods
//
// John Milmore

package Heap;

import java.util.Comparator;

public class PriorityQueue<P, V>
{
	private Heap<P, V> priQ;
	
	public PriorityQueue(Comparator<P> comparator) 
	{
		priQ = new Heap<P, V>(comparator);
	}
	
	public V peek() {return priQ.peek();}
	
	public void enqueue(P priority, V value) {priQ.insert(priority, value);}
	
	public V dequeue() {return priQ.remove();}
	
	public boolean isEmpty() {return priQ.isEmpty();}
	
	public int size() {return priQ.size();}
	
	public String toString() {return priQ.toString();}
}