/*
 * Implementation of a heap that represents the binary tree using an array
 * By doing this, we can easily maintain the complete tree property required by a heap
 * <P> - the type of the priority values
 * <V> - the type of the associated values
 */

package structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Heap<P, V> 
{
	protected ArrayList<Entry<P, V>> heap;			//The array representation of the heap
	protected Comparator<P> comparator;				//The comparator which determines priorities
	
	public Heap(Comparator<P> comparator) 
	{
		if(comparator == null)
			throw new NullPointerException("Comparator must be non-null");
		this.comparator = comparator;
		heap = new ArrayList<Entry<P, V>>();
	}
	
	public Heap<P, V> add(P priority, V value) 
	//Adds the specified value to this heap with the specified priority
	//Returns the altered Heap
	{
		if(priority == null || value == null)
			throw new NullPointerException("Priority and Value must be non-null");
		Entry<P, V> entry = new Entry<P,V>(priority, value);
		heap.add(entry);
		bubbleUp(heap.size() - 1);
		return this;
	}
	
	public V peek() 
	//Returns the value that has the highest priority as determined by comparator
	{
		if (heap.isEmpty())
			throw new NoSuchElementException("Cannot peek at an empty ArrayHeap.");
		return heap.get(0).getValue();
	}
	
	public V remove() 
	//Removes the value that has the highest priority as determined by comparator from this heap
	//Returns the altered Heap
	{
		if (isEmpty())
			throw new NoSuchElementException("Cannot peek at an empty ArrayHeap.");
		swap(0, heap.size() - 1);
		V removed = heap.remove(heap.size() - 1).getValue();
		if(!isEmpty())
			bubbleDown(0);
		return removed;
	}
	
	public int size() 
	//Returns the number of elements in this heap
	{
		return heap.size();
	}
	
	public boolean isEmpty() 
	//Returns true if there are no elements in this heap
	//Otherwise, returns false
	{
		return (heap.isEmpty());
	}
	
	public List<Entry<P, V>> asList() 
	//Returns an immutable view of the underlying array structure of this heap
	{
		return Collections.unmodifiableList(heap);
	}
	
	public Comparator<P> getComparator() 
	//Returns the comparator that is used to determine priority in this heap
	{
		return comparator;
	}
	
	protected void swap(int ind0, int ind1) 
	//Given two indices, swaps the values of the two indices in this heap
	{
		if (ind0 < 0 || ind0 >= heap.size() || ind1 < 0 || ind1 >= heap.size())
			throw new IndexOutOfBoundsException();
		Entry<P, V> e0 = heap.get(ind0);
		Entry<P, V> e1 = heap.get(ind1);
		heap.set(ind0, e1);
		heap.set(ind1, e0);
	}
	
	protected int getLeftChildOf(int index) 
	//Given an index of some "node" returns the index to that "nodes" left child
	{
		if(index < 0)
			throw new IndexOutOfBoundsException("Index invalid");
		return (index*2) + 1;
	}
	
	protected int getRightChildOf(int index) 
	//Given an index of some "node" returns the index to that "nodes" left child
	{
		if(index < 0)
			throw new IndexOutOfBoundsException("Index invalid");
		return (index*2) + 2;
	}
	
	protected int getParentOf(int index) 
	//Given an index of some "node" returns the index to that "nodes" parent.
	{
		if(index < 1)
			throw new IndexOutOfBoundsException("Index invalid");
		return (index-1)/2;
	}
	
	protected void bubbleUp(int index) 
	//This results in the entry at the specified index moving up the heap to a position
	//such that the heap maintains its order property
	//Runs O(Log(size)) time
	//Note: When add is called, an Entry is placed at the end of the internal array
	//and then this method is called on that index
	{
		if(index == 0)
			return; //Moved hole up to root
		else 
		{
			P hole = heap.get(index).getPriority(); //Priority Entry to move
			P parent = heap.get(getParentOf(index)).getPriority(); //Priority of parent of the Entry to move
			while((index > 0) && (comparator.compare(hole, parent) > 0)) //If the Entry is greater than its parent
			{
				swap(index, getParentOf(index)); //Move the hole up the heap
				index = getParentOf(index); //Update index
				if(index != 0)
					parent = heap.get(getParentOf(index)).getPriority(); //Update Parent
			}
		}
	}
	
	protected void bubbleDown(int index) 
	//This results in the entry at the specified index moving down in the heap to a position
	//such that the heap maintains its order property
	//Runs O(Log(size)) time
	//Note: When remove is called, if there are elements remaining in this heap, the bottom most
	//element of the heap is swapped with index 0, and this method is called for index 0
	{
		int hole = index; //Index of Entry to move down the heap
		
		int newHole = newHole(index); 	//Index of new position of the Entry
		while(newHole != hole) 			//If new position was different than original
		{
			swap(hole, newHole); //Move Entry down the heap
			hole = newHole;		 //Update the Entry position
			newHole = newHole(hole);	//Get new position
		}
	}
	
	protected int newHole(int index) 
	//bubbleDown Helper method
	//Returns index of new position of Entry when moving down the heap
	{
		int Li = getLeftChildOf(index);		//Left child index
		int Ri = getRightChildOf(index); 	//Right child index
		
		P hole = heap.get(index).getPriority();	//Priority of Entry to move down the heap
		
		//Hole has no children
		if(Li > (heap.size() - 1))
			return index;
		
		//Only Left Child
		P LiP = heap.get(Li).getPriority();
		if(Li == (heap.size() - 1))
			if(comparator.compare(LiP, hole) > 0)
				return Li;
			else
				return index;
		
		//Two Children
		P RiP = heap.get(Ri).getPriority();
		if(comparator.compare(RiP, LiP) > 0) //Right child greater than left child
			if(comparator.compare(RiP, hole) > 0)	//Right child greater than hole
				return Ri;
			else
				return index;
		else //Left child greater than right child
			if(comparator.compare(LiP, hole) > 0) //Left child greater than hole
				return Li;
			else
				return index;
	}	
	
}
