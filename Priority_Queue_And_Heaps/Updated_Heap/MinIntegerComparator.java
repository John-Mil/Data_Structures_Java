// 10.compare(5): -1
// 5.compare(10): 1
// 5.compare(5): 0

package Heap;

import java.util.Comparator;

public class MinIntegerComparator implements Comparator<Integer> 
{
	@Override
	public int compare(Integer arg0, Integer arg1) 
	{
		if(arg0 < arg1)
			return 1;
		else if(arg1 < arg0)
			return -1;
		else 
			return 0;
	}
}