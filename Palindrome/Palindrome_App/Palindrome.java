//Provides a static method that determines whether a String is a palindrome or not

package palindrome;

import structures.Queue;
import structures.Stack;

public class Palindrome 
{
	
	public static boolean test(String candidate) 
	{
		
		//Create Stack and Queue
		Stack<Character> stack = new Stack<Character>();
		Queue<Character> queue = new Queue<Character>();
	
		char ch;
		//Fill stack and queue with the letters in candidate
		for(int i = 0; i < candidate.length(); i++) 
		{
			ch = candidate.charAt(i);
			ch = Character.toLowerCase(ch);
			if(Character.isLetter(ch)) 
			{
				stack.push(ch);
				queue.enqueue(ch);
			}
		}
		
		//Print each element in reverse and forward order to check if equal
		//Pop and Dequeue elements from stack and queue
		boolean stillPalindrome = true;
		char fromStack;
		char fromQueue;
		while(!stack.isEmpty() && stillPalindrome) 
		{
			fromQueue = queue.dequeue();
			fromStack = stack.top();
			stack.pop();
			if(fromQueue != fromStack) 
				stillPalindrome = false;
		}
		
		return stillPalindrome;
	}
	
}
