//An IntegerComparator compared integers in the natural way

package comparator;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer>
{

	public int compare(Integer arg0, Integer arg1) 
	{
		if(arg0 > arg1) return 1;
		else if(arg1 > arg0) return -1;
		else return arg0.compareTo(arg1);
	}
	
}
