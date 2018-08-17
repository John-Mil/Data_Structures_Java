/*
 * An entry describes an element of a priority queue
 * <P> - the priority type for this entry
 * <V> - the value type for this entry
 */

package structures;

public class Entry<P, V> 
{
	final private P priority;
	final private V value;
	
	public Entry(P priority, V value) 
	{
		if(priority == null || value == null)
			throw new NullPointerException("Priority and Value must be non-null");
		this.priority = priority;
		this.value = value;
	}
	
	public final P getPriority() {return priority;}
	
	public final V getValue() {return value;}
}
