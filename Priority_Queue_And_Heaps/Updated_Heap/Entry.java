// Elements of Priority Queue
// Stores the value of the underlying object of the heap
// Also stores its priority value

package Heap;

public class Entry<P, V> 
{
	
	private P priority;
	private V value;
	
	public Entry(P priority, V value) 
	{
		if(priority == null || value == null)
			throw new NullPointerException("Priority and Value must not be null");
		this.priority = priority;
		this.value = value;
	}
	
	public P getPriority() {return priority;}
	public V getValue() {return value;}
}