package app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class BSTtesting 
{
	BinarySearchTree<Integer> bst;
	
	@Before
	public void setup() 
	{
		bst = new BinarySearchTree<Integer>();
	}
	
	@Test
	public void testMethods() 
	{
		bst.add(20); bst.add(10); bst.add(30); bst.add(5); bst.add(15); bst.add(12); bst.add(17);
		
		//Test size() and isEmpty()
		assertEquals(7, bst.size());
		assertFalse(bst.isEmpty());
		
		//Test contains()
		assertTrue(bst.contains(12));
		assertTrue(bst.contains(17));
		assertFalse(bst.contains(16));
		
		//Test get()
		assertEquals(new Integer(5), bst.get(5));
		assertEquals(null, bst.get(16));
		
		//Test remove()
		assertTrue(bst.remove(15));
		assertEquals(6, bst.size());
		assertFalse(bst.contains(15));
		assertTrue(bst.contains(17));
		assertEquals(null, bst.get(15));
		
		//Test tree configuration
		assertEquals(new Integer(20), bst.root.getInfo());
		assertEquals(new Integer(10), bst.root.getLeftChild().getInfo());
		assertEquals(new Integer(30), bst.root.getRightChild().getInfo());
		assertEquals(new Integer(12), bst.root.getLeftChild().getRightChild().getInfo());
		assertEquals(new Integer(17), bst.root.getLeftChild().getRightChild().getRightChild().getInfo());
		assertEquals(null, bst.root.getLeftChild().getRightChild().getLeftChild());
	}
	
	@Test
	public void inOrderTest() 
	{
		bst.add(20); bst.add(10); bst.add(30); bst.add(25); bst.add(5);
		int size = bst.reset(BinarySearchTree.INORDER);
		assertEquals(new Integer(5), bst.getNext(BinarySearchTree.INORDER));
		assertEquals(new Integer(10), bst.getNext(BinarySearchTree.INORDER));
		assertEquals(new Integer(20), bst.getNext(BinarySearchTree.INORDER));
		assertEquals(new Integer(25), bst.getNext(BinarySearchTree.INORDER));
		assertEquals(new Integer(30), bst.getNext(BinarySearchTree.INORDER));
	}
	
	@Test
	public void preOrderTest() 
	{
		bst.add(20); bst.add(10); bst.add(30); bst.add(25); bst.add(5);
		int size = bst.reset(BinarySearchTree.PREORDER);
		assertEquals(new Integer(20), bst.getNext(BinarySearchTree.PREORDER));
		assertEquals(new Integer(10), bst.getNext(BinarySearchTree.PREORDER));
		assertEquals(new Integer(5), bst.getNext(BinarySearchTree.PREORDER));
		assertEquals(new Integer(30), bst.getNext(BinarySearchTree.PREORDER));
		assertEquals(new Integer(25), bst.getNext(BinarySearchTree.PREORDER));
	}
	
	@Test
	public void postOrderTest() 
	{
		bst.add(20); bst.add(10); bst.add(30); bst.add(25); bst.add(5);
		int size = bst.reset(BinarySearchTree.POSTORDER);
		assertEquals(new Integer(5), bst.getNext(BinarySearchTree.POSTORDER));
		assertEquals(new Integer(10), bst.getNext(BinarySearchTree.POSTORDER));
		assertEquals(new Integer(25), bst.getNext(BinarySearchTree.POSTORDER));
		assertEquals(new Integer(30), bst.getNext(BinarySearchTree.POSTORDER));
		assertEquals(new Integer(20), bst.getNext(BinarySearchTree.POSTORDER));
	}
}
