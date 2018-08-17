/*
 * Objects of this class represent nodes for a BST
 * Objects of class T must implement Comparable as they are elements in a BST
 */

package app;

public class BSTNode<T extends Comparable<T>> 
{
	
	private T info;							//Element in this node
	private BSTNode<T> leftChild;			//Reference to this node's left child
	private BSTNode<T> rightChild;			//Reference to this node's right child
	
	
	public BSTNode(T info) 
	{
		this.info = info;
		leftChild = rightChild = null;
	}
	
	
	//GETTERS AND SETTERS
	public void setInfo(T info) 
	{
		this.info = info;
	}
	
	public void setLeft(BSTNode<T> left) 
	{
		this.leftChild = left;
	}
	
	public void setRight(BSTNode<T> right) 
	{
		this.rightChild = right;
	}
	
	public T getInfo() 
	{
		return info;
	}
	
	public BSTNode<T> getLeftChild() 
	{
		return leftChild;
	}
	
	public BSTNode<T> getRightChild() 
	{
		return rightChild;
	}
	
}
