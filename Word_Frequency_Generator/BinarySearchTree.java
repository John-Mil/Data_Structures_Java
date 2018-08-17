/*
 * Reference based Binary Search Tree
 * Represents the underlying structure of the word frequency generator
 * Allows for efficient access to the container of WordFreq objects
 */

package app;

import java.util.LinkedList;

public class BinarySearchTree<T extends Comparable<T>>
{
	
	//INSTANCE VARIABLES
	protected BSTNode<T> root;			//Reference to the root node of this BST
	protected boolean found;				//For searching
	
	//For Traversal
	protected LinkedList<T> inOrderQueue;
	protected LinkedList<T> preOrderQueue;
	protected LinkedList<T> postOrderQueue;
	//used to specify traversal order
	protected static final int INORDER = 1;
	protected static final int PREORDER = 2;
	protected static final int POSTORDER = 3;
	
	
	
	//CONSTRUCTOR
	public BinarySearchTree() 
	{
		root = null;
	}
	
	
	
	//RECURSIVE HELPERS
	private int recSizeHelper(BSTNode<T> node) 
	//Called by size()
	{
		if(node == null)
			return 0;
		else 
			return 1 + recSizeHelper(node.getLeftChild()) + recSizeHelper(node.getRightChild());
	}
	
	private BSTNode<T> recAddHelper(T element, BSTNode<T> node) 
	//Reconstructs the BST to maintain BST properties
	//Called by add()
	{
		if(node == null)
			node = new BSTNode<T>(element);
		else if(element.compareTo(node.getInfo()) <= 0)
			node.setLeft(recAddHelper(element, node.getLeftChild()));
		else
			node.setRight(recAddHelper(element, node.getRightChild()));
		return node;
	}
	
	private BSTNode<T> recRemoveHelper(T element, BSTNode<T> node) 
	//Called by remove()
	{
		//Not in BST
		if(node == null)
			found = false;
		//Element less than current node, check left child
		else if(element.compareTo(node.getInfo()) < 0)
			node.setLeft(recRemoveHelper(element, node.getLeftChild()));
		//Element greater than current node, check right child
		else if(element.compareTo(node.getInfo()) > 0)
			node.setRight(recRemoveHelper(element, node.getRightChild()));
		//Element equal to current node, remove it
		else 
		{
			node = removeNode(node);
			found = true;
		}
		return node;
	}
	
	private BSTNode<T> removeNode(BSTNode<T> node) 
	//Removes the information at the node referenced by node
	{
		//If no children return null
		//Or, if one child return that child
		if(node.getLeftChild() == null)
			return node.getRightChild();
		else if(node.getRightChild() == null)
			return node.getLeftChild();
		
		//Two children
		//Replace node's information by its logical predecessor and the predecessor's node is removed
		else 
		{
			T data = getPredessor(node.getLeftChild());
			node.setInfo(data);
			node.setLeft(recRemoveHelper(data, node.getLeftChild()));
			return node;
		}
	}
	
	private T getPredessor(BSTNode<T> node) 
	//Returns the greatest (rightmost) element in the tree referred to by node
	{
		while(node.getRightChild() != null)
			node = node.getRightChild();
		return node.getInfo();
	}
	
	private T recGetHelper(T element, BSTNode<T> node) 
	//Called by get()
	{
		//Not in BST
		if(node == null)
			return null;
		
		else if(element.compareTo(node.getInfo()) < 0)
			return recGetHelper(element, node.getLeftChild());
		else if(element.compareTo(node.getInfo()) > 0)
			return recGetHelper(element, node.getRightChild());
		else
			return node.getInfo();
	}
	
	private boolean recContainsHelper(T element, BSTNode<T> node) 
	//Called by contains()
	{
		//Not in BST
		if(node == null)
			return false;
		
		else if(element.compareTo(node.getInfo()) < 0) 
			return recContainsHelper(element, node.getLeftChild());
		else if(element.compareTo(node.getInfo()) > 0)
			return recContainsHelper(element, node.getRightChild());
		else
			return true;
	}
	
	
	
	//IMPLEMENTATION
	public int size() 
	//Returns the number of elements in this BST
	{
		return recSizeHelper(root);
	}
	
	public boolean isEmpty() 
	{
		return (root == null);
	}
	
	public void add(T element) 
	//Inserts element into the correct position in the BST
	{
		root = recAddHelper(element, root);
	}
	
	public boolean remove(T element) 
	//Removes an element e from this BST such that e.compareTo(element) == 0 and returns true
	//If no such element exists, returns false
	{
		root = recRemoveHelper(element, root);
		return found;
	}
	
	public boolean contains(T element) 
	//Returns true if this BST contains element
	//Otherwise, returns false
	{
		return recContainsHelper(element, root);
	}
	
	public T get(T element) 
	//Returns an element e in this BST such that e.compareTo(element) == 0
	//If no such element exists, return null
	{
		return recGetHelper(element, root);
	}
	
	
	
	//TRAVERSAL METHODS
	public int reset(int orderType)
	//initializes current position for an iteration through this BST in
	//orderType order. Returns number of nodes in the BST
	{
		int numNodes = size();
		if(orderType == INORDER) 
		{				
			inOrderQueue = new LinkedList<T>();
			inOrder(root);
		}			
		if(orderType == PREORDER) 
		{
			preOrderQueue = new LinkedList<T>();				
			preOrder(root);
		}
		if(orderType == POSTORDER) 
		{				
			postOrderQueue = new LinkedList<T>();
			postOrder(root);
		}			
		return numNodes;
	}
	
	public T getNext(int orderType) 
	//preconditions: BST not empty, been reset, has not been modified since reset, 
	//				 end of orderType iteration not reached
	//returns element at current position on this BST for orderType iteration
	//and advances the current position based on orderType
	{
		if(orderType == INORDER)
			return inOrderQueue.remove();
		if(orderType == PREORDER)
			return preOrderQueue.remove();
		if(orderType == POSTORDER)
			return postOrderQueue.remove();
		else 
			return null;
	}
	
	
	//TRAVERSAL HELPER METHODS
	private void inOrder(BSTNode<T> tree) 
	//Sets up queue for inOrder traversal
	{
		if(tree != null) 
		{
			inOrder(tree.getLeftChild());
			inOrderQueue.add(tree.getInfo());
			inOrder(tree.getRightChild());
		}
	}
	
	private void preOrder(BSTNode<T> tree) 
	//Sets up queue for preOrder traversal
	{
		if(tree != null) 
		{
			preOrderQueue.add(tree.getInfo());
			preOrder(tree.getLeftChild());
			preOrder(tree.getRightChild());
		}
	}
	
	private void postOrder(BSTNode<T> tree) 
	//Sets up queue for postOrder traversal
	{
		if(tree != null) 
		{
			postOrder(tree.getLeftChild());
			postOrder(tree.getRightChild());
			postOrderQueue.add(tree.getInfo());
		}
	}
	
}
