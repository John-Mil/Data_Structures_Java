/*
 * Objects of RecursiveList represent containers that support insertion, removal and searching of elements
 * Referenced based List
 * List methods implemented recursively
 * RecursiveList supports generics: element type declared by client
 * No null elements permitted
 * Indexing begins with zero
 * An iterator is used to traverse the list
 */

package structures;

import java.util.Iterator;

public class RecursiveList<T> implements Iterable<T>
{
	
	//Instance variables
	protected Node<T> head;			//Reference to the front node of this list
	protected int numElements;		//Number of elements in this list
	
	//For searching
	private Node<T> current;
	private Node<T> previous;
	private int indexItr;
	private boolean found;
	
	public RecursiveList() 
	{
		head = null;
		numElements = 0;
	}
	
	//RECURSIVE HELPERS---------------------------------------------------------------------------------------------
	private void reset() 
	//Resets the instance variable related to searching
	{
		current = head;
		previous = null;
		indexItr = 0;
		found = false;
	}
	
	private void find(T elem) 
	//Sets current to the node in the list in which current.getInfo().equals(elem)
	//Returns when the node is found, or when the entire list is searched
	//If in list, sets found to true, otherwise sets found to false
	//Runs O(size) time
	{
		if((current == null)) 
			return;
		else if (current.getInfo().equals(elem)) 
		{
			found = true;
			return;
		}
		else 
		{
			inc();
			find(elem);
		}
	}
	
	private void lastNode() 
	//Sets current to the last node in the list
	//Runs O(size) time
	{
		if(current.getLink() == null)
			return;
		else 
		{
			inc();
			lastNode();
		}
	}
	
	private void indexNode(int i) 
	//Increments indexItr to represent the desired index i
	//Sets current to reference the node at this index
	//Sets previous to reference the node proceeding this index
	//Runs O(i) time
	{
		if(indexItr == i)
			return;
		else 
		{
			inc();
			indexNode(i);
		}
	}
	
	private void inc() 
	//Increments current and previous to the next nodes
	//Increments indexItr to the next index
	{
		previous = current;
		current = current.getLink();
		indexItr++;
	}

	
	//LIST IMPLEMENTATION---------------------------------------------------------------------------------------------
	public int size() 
	//Returns the number of elements in this list
	//Runs in O(1) time.
	{
		return numElements;
	}
	
	public RecursiveList<T> insertFirst(T element) 
	//Adds an element to the front of this list
	//Runs O(1) time
	//Returns this list for convenience
	{
		if(element == null)
			throw new NullPointerException("No null elemented permitted");
		Node<T> newNode = new Node<T>(element);
		newNode.setLink(head);
		head = newNode;
		numElements++;
		return this;
	}
	
	public RecursiveList<T> insertLast(T element) 
	//Adds an element to the end of this list interface
	//Runs O(size) time
	//Returns this list for convenience
	{
		if(element == null)
			throw new NullPointerException("No null elemented permitted");
		Node<T> newNode = new Node<T>(element);
		if(isEmpty())
			head = newNode;
		else 
		{
			reset();
			lastNode();
			current.setLink(newNode);
		}
		numElements++;
		return this;
	}
	
	public RecursiveList<T> insertAt(int index, T element)
	//Adds an element at the specified index, such that a call to get(index) will return the inserted value
	//Runs O(index) time
	//Returns this list for convenience
	//@throws IndexOutOfBoundsException if i < 0 or i >= size
	{
		if(element == null)
			throw new NullPointerException("No null elemented permitted");
		if((index < 0) || (index > size()))
			throw new IndexOutOfBoundsException("Index invalid");
		if(index == 0)
			return insertFirst(element);
		else if(index == size())
			return insertLast(element);
		else 
		{
			Node<T> newNode = new Node<T>(element);
			reset();
			indexNode(index);
			previous.setLink(newNode);
			newNode.setLink(current);
			numElements++;
			return this;
		}
	}
	
	public T removeFirst() 
	//Removes the first element from this list and returns it
 	//Runs O(1) time
	//@throws IllegalStateException if the list is empty.
	{
		if(isEmpty())
			throw new IllegalStateException("Empty list");
		T removed = head.getInfo();
		head = head.getLink();
		numElements--;
		return removed;
	}
	
	public T removeLast() 
	//Removes the last element from this list and returns it
	//Runs O(size) time
	//@throws IllegalStateException if the list is empty.
	{
		if(isEmpty())
			throw new IllegalStateException("Empty list");
		reset();
		lastNode();
		T removed = current.getInfo();
		if(previous == null) //Only one element
			head = null;
		else
			previous.setLink(current.getLink());
		numElements--;
		return removed;
	}
	
	public T removeAt(int i) 
	//Removes the ith element from this list and returns it
	//Runs O(i) time
	//@throws IndexOutOfBoundsException if i < 0 or i >= size
	{
		if((i < 0) || (i >= numElements))
			throw new IndexOutOfBoundsException("Index invalid");
		reset();
		indexNode(i);
		T removed = current.getInfo();
		if(current == head) //i equals zero
			head = head.getLink();
		else
			previous.setLink(current.getLink());
		numElements--;
		return removed;
	}
	
	public T getFirst() 
	//Returns the first element in this list
	//Runs O(1) time.
	//@throws IllegalStateException if the list is empty.
	{
		if(isEmpty())
			throw new IllegalStateException("List is empty");
		return head.getInfo();
	}
	
	public T getLast() 
	//Returns the last element in this list
	//Runs O(size) time.
	//@throws IllegalStateException if this list is empty.
	{
		if(isEmpty())
			throw new IllegalStateException("List is empty");
		reset();
		lastNode();
		return current.getInfo();
	}
	
	public T get(int i) 
	//Returns the ith element in this list
	//Runs O(i) time.
	//@throws IndexOutOfBoundsException if i < 0 or i >= size
	{
		if((i < 0) || (i >= numElements))
			throw new IndexOutOfBoundsException("Index invalid");
		reset();
		indexNode(i);
		return current.getInfo();
	}
	
	public boolean remove(T elem) 
	//Removes elem from this list if it exists. If
	//multiple instances of elem exist in this list
	//the one associated with the smallest index is removed
	//Runs O(size) time.
	//@throws NullPointerException elem null
	//returns true if elem was removed and false otherwise
	{
		if(elem == null)
			throw new NullPointerException("No null elemented permitted");
		reset();
		find(elem);
		if(!found) //elem not in list
			return false;
		else 
		{
			if(current == head) //remove first 
				head = head.getLink();
			else
				previous.setLink(current.getLink());
			numElements--;
			return true;
		}
	}

	public int indexOf(T elem) 
	//Returns the smallest index that contains elem
	//If list does not contain elem, -1 is returned
	//Runs O(size) time
	//@throws NullPointerException if elem is null
	{
		if(elem == null)
			throw new NullPointerException("No null elemented permitted");
		reset();
		find(elem);
		if(!found)
			return -1;
		else
			return indexItr;
	}
	
	public boolean isEmpty() 
	//Returns true if this list contains no elements, otherwise returns false
	//Runs O(1) time
	{
		return (head == null);
	}
	
	public Iterator<T> iterator() 
	//Creates an object of type Iterator used to iterate over this list
	//Precondition: list is not empty
	{
		return new ListIterator<T>(this);
	}

}
