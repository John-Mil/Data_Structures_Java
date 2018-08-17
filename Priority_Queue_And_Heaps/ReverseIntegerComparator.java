//A ReverseIntegerComparator compares integers in reverse order,
//e.g. compare(2, 1) returns a negative number.


package comparator;

import java.util.Comparator;

public class ReverseIntegerComparator implements Comparator<Integer>
{
	
	public int compare(Integer arg0, Integer arg1) 
	{
		if(arg0 > arg1) return -1;
		else if(arg1 > arg0) return 1;
		else return arg0.compareTo(arg1);
	}
	
}
