/*
 * A PriorityQueue is a queue data structure that allows elements to be enqueued with a
 * priority such that higher priority elements are dequeued first
 * <P> - the priority type of this PriorityQueue
 * <V> - the value type of this PriorityQueue
 * 
 * Classes that extend PriorityQueue are themselves priority queues and must simply
 * declare a priority (Comparator) and a value
 */

package structures;

import java.util.Comparator;
import java.util.Iterator;

public abstract class PriorityQueue<P, V> 
{
	protected Comparator<P> comparator;
	protected Heap<P, V> priQ;
	
	
	public final PriorityQueue<P, V> enqueue(P priority, V value) 
	//Enqueues the specified value into this PriorityQueue with
	//the specified priority. This runs in O(log(size)) time. For
	//convenience this method returns the modified PriorityQueue.
	{
		priQ.add(priority, value);
		return this;
	}
	
	public final V dequeue() 
	//Removes the value with the highest priority in this PriorityQueue
	//and then returns it. This runs in O(log(size)) time.
	{
		return priQ.remove();
	}
	
	public final V peek() 
	//Returns the value with the highest priority in this PriorityQueue
	{
		return priQ.peek();
	}
	
	public final Iterator<Entry<P, V>> iterator() 
	//Returns an Iterator over all of the entries in this
	//PriorityQueue. The order of these elements is unspecified and a
	//call to Iterator#remove() results in an UnsupportedOperationException
	{
		return priQ.heap.iterator();
	}
	
	public final Comparator<P> getComparator() 
	//Returns the Comparator that is used to determine the ordering of
	//Entries in this PriorityQueue.
	{
		return comparator;
	}
	
	public final int size() 
	//Returns the total number of elements in this PriorityQueue
	{
		return priQ.size();
	}
	
	public final boolean isEmpty() 
	//Returns true if this PrioirtyQueue has no elements and
	//false otherwise.
	{
		return priQ.isEmpty();
	}
}
