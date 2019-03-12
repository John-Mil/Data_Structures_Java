// Implementation of a heap tree
// Tree represented as an ArrayList
//     - Automatically resizes array (nice for insertion)
//     - Automatically keeps track of size of array
//	       - easily track index of last element (size-1)
//     - Easy add/remove/access: heap.add(), heap.remove(), heap.get()
//         - Saves work of directly indexing array
//
// Heap is a binary tree in with the following characteristics
//     - It is a complete tree
//     - Every node's priority value is greater than (or equal to)
//       it's children's priority values
// These characteristics must be maintained during insertion and removal
//
// Heap is used to implement a priority queue
// This heap implementation is versatile in the sense that the user can
// define the object that the heap holds, its priority values and its comparator
// More specifically, the heap is implemented by an ArrayList of Entries where
//     - Entry<P, V>
//     - P: priority (type and value of the priority)
//     - V: value (type and value of the object to be stored)
//
// For example: to define a max queue storing strings that each have an integer valued priority
//     - Can define a comparator - MaxIntegerComparator that implements Comparator<Integer>
//     - So define priority queue as
//       Heap<Integer, String> priQ = new Heap<Integer, String>(new MaxIntegerComparator());
//     - This priority queue will allow constant time viewing of the max element
//       where the max element is the string with the highest integer valued priority
//
// John Milmore


package Heap;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<P, V>
{
	// Underlying structure of heap: ArrayList
	// ArrayList consists of Entry nodes which store the value of the
	// object along with its priority value
	private ArrayList<Entry<P, V>> heap;
	
	// Comparator object
	// Has method for comparing elements in our heap (Entry<P, V>)
	// Compares the priority (P) values
	// Determines how priority of elements is defined
	private Comparator<P> comparator;
	
	public Heap(Comparator<P> comparator) 
	{
		if(comparator == null)
			throw new NullPointerException("Comparator must not be null");
		this.comparator = comparator;
		heap = new ArrayList<Entry<P, V>>();
	}
	
	// HELPERS
	// Return index of left/right child or parent
	private int leftInd(int i)  {return 2*i + 1;}
	private int rightInd(int i) {return 2*i + 2;}
	private int parInd(int i)   {return (i-1)/2;}
	
	// Return value of Entry at index i
	private V getVal(int i)   {return heap.get(i).getValue();}
	
	// Return priority value of left/right child or parent or Entry at index i
	private P leftPri(int i)  {return heap.get(leftInd(i)).getPriority();}
	private P rightPri(int i) {return heap.get(rightInd(i)).getPriority();}
	private P parPri(int i)   {return heap.get(parInd(i)).getPriority();}
	private P getPri(int i)   {return heap.get(i).getPriority();}
	
	// Return true if has left/right child or parent
	private boolean hasLeft(int i)  {return leftInd(i) <= (heap.size()-1);}
	private boolean hasRight(int i) {return rightInd(i) <= (heap.size()-1);}
	private boolean hasPar(int i)   {return i > 0;}
	
	private void swap(int a, int b)
	// Swap values for bubbleUp and bubbleDown
	// Simply swaps Entry objects in the ArrayList
	{
		Entry<P, V> temp = heap.get(a);
		heap.set(a, heap.get(b));
		heap.set(b, temp);
	}
	
	// IMPLEMENTATION
	public V peek()
	// Returns value of Entry at the root of the heap tree
	// Element with the greatest priority
	{
		if(heap.isEmpty()) 
		{
			//throw new IllegalStateException("No elements");
			System.out.println("Heap is empty!");
			return null;
		}
		return getVal(0);
	}
	
	public boolean isEmpty() {return heap.isEmpty();}
	
	public int size() {return heap.size();}
	
	public void insert(P priority, V value) 
	// Inserts an Entry object into our heap tree (ArrayList)
	// Does so by the following
	//     - Insert Entry into the next spot in bottom of the tree (last index in ArrayList)
	//     - Then, compare priority of the Entry with its parent and move Entry
	//       up the tree until it is in a place such that the heap condition holds
	{
		Entry<P, V> newNode = new Entry<P, V>(priority, value);
		heap.add(newNode);
		bubbleUp();
	}
	 
	private void bubbleUp() 
	// Moves Entry up the tree until its priority value is less than or equal to its parent
	// priority value
	{
		int i = heap.size()-1;
		P iPri = getPri(i);
		while(hasPar(i) && (comparator.compare(iPri, parPri(i)) > 0)) 
		{
			swap(i, parInd(i));
			i = parInd(i);
			iPri = getPri(i);
		}
	}
	
	public V remove() 
	// Removes and returns the highest priority Entry from the heap
	// Does so by the following
	//     - Retrieves the root node (first element in ArrayList)
	//     - Copies lowest level/rightmost Entry in the tree (last element in ArrayList) 
	//       and moves it the newly removed root
	//     - Removes last element from the heap (that we just copied up to the root)
	//     - Moves the root down the tree until the heap condition holds
	{
		if(heap.isEmpty()) 
		{
			//throw new IllegalStateException("No elements");
			System.out.println("Heap is empty!");
			return null;
		}
		V ret = getVal(0);
		heap.set(0, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		bubbleDown();
		return ret;
	}
	
	private void bubbleDown() 
	// Compares the root node to its max valued (priority) child
	// and moves root down the tree if its priority value is less 
	// than its highest priority valued child
	{
		int i = 0;
		while(hasLeft(i)) 
		{
			int maxChildInd = getMaxChildInd(i);
			P childPri = getPri(maxChildInd);
			P iPri = getPri(i);
			if(comparator.compare(childPri, iPri) > 0) 
			{
				swap(i, maxChildInd);
				i = maxChildInd;
			}
			else
				break;
		}
	}
	
	private int getMaxChildInd(int i) 
	// Returns the index of the highest priority valued child of the Entry at
	// index i
	{
		int l = leftInd(i);
		P lPri = leftPri(i);
		int ret = l;
		if(hasRight(i)) 
		{
			int r = rightInd(i);
			P rPri = rightPri(i);
			if(comparator.compare(rPri, lPri) > 0)
				ret = r;
		}
		return ret;
	}
	
	public String toString() 
	// Prints values of the Entries in the heap
	// Heap is weakly ordered (unlike binary trees) so
	// order is not specific in any way
	// (in fact, if you remove and Entry and add it back,
	// you do not necessarily restore the original heap)
	{
		if(heap.isEmpty())
			return "Heap is Empty! Add some elements.";
		String out = "";
		for(int i = 0; i < heap.size(); i++) 
		{
			out +=  getVal(i).toString() + "|";
		}
		return out;
	}
}