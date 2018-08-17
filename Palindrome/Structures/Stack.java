//Reference based LIFO stack data structure
//Supports generics: type of elements is decided by client
//No null elements permitted

package structures;

import exceptions.StackUnderflowException;

public class Stack<T>
{
	
	private Node<T> top;			//Node that references the top of the stack
	private int numElements;		//Number of elements in the stack
	
	public Stack() 
	{
		top = null;
		numElements = 0;
	}
	
	public void push(T element) 
	//Adds element to the top of the stack
	//Throws NullPointerException if element is null
	{
		if(element == null)
			throw new NullPointerException("No null elements permitted");
		Node<T> newNode = new Node<T>(element);
		newNode.setLink(top);
		top = newNode;
		numElements++;
	}
	
	public T top() 
	//Returns the element from the top of the stack
	{
		if(isEmpty())
			throw new StackUnderflowException("Element not returned: Stack is empty");
		return top.getInfo();
	}
	
	public void pop() 
	//Removes the top element of the stack
	{
		if(isEmpty())
			throw new StackUnderflowException("Element not removed: Stack is empty");
		top = top.getLink();
		numElements--;
	}
	
	public boolean isEmpty() 
	//Returns true if the stack is empty
	//otherwise, returns false
	{return (top == null);}
	
	public int size()
	//Returns the number of elements in the stack
	{return numElements;}
	
}
