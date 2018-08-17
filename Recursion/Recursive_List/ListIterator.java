package structures;

import java.util.Iterator;

public class ListIterator<T> implements Iterator<T>
{
	
	RecursiveList<T> list;
	Node<T> curr;
	
	public ListIterator(RecursiveList<T> list) 
	{
		this.list = list;
		curr = list.head;
	}

	public boolean hasNext() 
	//Returns true if the list has another element to process
	//otherwise returns false
	{
		return (curr != null);
	}

	public T next() 
	//If current is null, returns null
	//Otherwise, returns the current element in this list and progresses the current element
	{
		if(!hasNext())
			return null;
		else 
		{
			T next = curr.getInfo();
			curr = curr.getLink();
			return next;
		}
	}
	
}
