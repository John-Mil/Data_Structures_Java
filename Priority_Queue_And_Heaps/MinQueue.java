/*
 * Objects of this class represent a minimum PriorityQueue that stores values of type <V>
 * and are prioritized with Integers. Integers' priority is determined by the
 * ReverseIntegerComparator class
 */

package structures;

import comparator.ReverseIntegerComparator;

public class MinQueue<V> extends PriorityQueue<Integer, V>
{
	public MinQueue(Integer priority, V value) 
	{
		comparator = new ReverseIntegerComparator();
		priQ = new Heap<Integer, V>(comparator);
	}
}
