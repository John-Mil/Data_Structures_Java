/*
 *
 * Objects of this class represent a maximum PriorityQueue that stores values of type <V>
 * and are prioritized with Integers. Integers' priority is determined by the
 * IntegerComparator class
 */

package structures;

import comparator.IntegerComparator;

public class MaxQueue<V> extends PriorityQueue<Integer, V>
{
	public MaxQueue(Integer priority, V value) 
	{
		comparator = new IntegerComparator();
		priQ = new Heap<Integer, V>(comparator);
	}
}
