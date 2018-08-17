//Reference based FIFO queue data structure
//Supports generics: type of elements is decided by client
//No null elements permitted

package structures;

import exceptions.QueueUnderflowException;

public class Queue<T>
{
	
	private Node<T> front;			//Node referencing the front of the queue
	private Node<T> rear;			//Node referencing the rear of the queue
	private int numElements;		//Number of elements in the queue
	
	public Queue() 
	{
		this.front = null;
		this.rear = null;
		numElements = 0;
	}
	
	public void enqueue(T element) 
	//Adds element to the rear of the queue
	//Throws NullPointerException if element is null
	{
		if(element == null)
			throw new NullPointerException("No null elements permitted");
		Node<T> newNode = new Node<T>(element);
		if(isEmpty()) //Insert into empty queue
			front = newNode;
		else
			rear.setLink(newNode);
		rear = newNode;
		numElements++;
	}
	
	public T dequeue() 
	//Removes element from the front of the queue and returns it
	{
		if(isEmpty())
			throw new QueueUnderflowException("Element not removed: Queue is empty");
		T removed = front.getInfo();
		front = front.getLink();
		if(isEmpty()) //Only one element
			rear = null;
		return removed;
	}
	
	public boolean isEmpty() 
	//Returns true if the queue is empty
	//Otherwise, returns false
	{return (front == null);}
	
	public int size() 
	//Returns the number of elements in the queue
	{return numElements;}
	
}
