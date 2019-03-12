// Application that allows user to define a min or max priority queue
// where the priority queue holds Entries that store Strings
// and have integer valued priorities
// Does so by receiving input from the user
//
// John Milmore

package Heap;

import java.util.Scanner;

public class PriorityQueueApp 
{
	public static void main(String[] args) 
	{
		PriorityQueue<Integer, String> pq = null;
		Scanner in = new Scanner(System.in);
		String order = "";
		
		// Ask user for minimum or maximum pq
		System.out.println("How would you like to order your pq");
		while(true)
		{
			System.out.println("Enter 'min' for a minimum queue or 'max' for a max queue");
			order = in.nextLine();
			if(order.equalsIgnoreCase("min") || order.equalsIgnoreCase("max"))
				break;
			else
				System.out.println("Invalid input: Try again");
		}
		
		// Initialize pq comparator as min or max
		if(order.equalsIgnoreCase("min"))
			pq = new PriorityQueue<Integer, String>(new MinIntegerComparator());
		else
			pq = new PriorityQueue<Integer, String>(new MaxIntegerComparator());
		
		// Perform operations on the pq given by user
		String dir = "";
		int pri;
		String val;
		String skip = "";
		boolean test = true;
		while(test) 
		{
			System.out.println("\nEnter first letter of the following instructions:");
			System.out.println("show, insert, remove, top, exit");
			dir = in.nextLine();
			switch(dir) 
			{
				case "s":
					System.out.println(pq);
					break;
				case "i":
					System.out.println("Enter string value:");
					val = in.nextLine();
					System.out.println("Enter integer valued priority:");
					pri = in.nextInt();
					skip = in.nextLine();
					pq.enqueue(pri, val);
					break;
				case "r":
					System.out.println("Item Removed: " + pq.dequeue());
					break;
				case "t":
					System.out.println(pq.peek());
					break;
				case "e":
					test = false;
					break;
				default:
					System.out.println("Invalid input");
			}
		}
		
		// Finish
		System.out.println("\nExiting");
		in.close();
	}
}
