/*
 * Instantiates a grid based on user input for the size and the percentage of blob characters
 * Reports the number of blobs
 */

package blobs;

import java.util.Scanner;

public class BlobApp 
{
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		
		int numRows;		//Number of rows in grid
		int numCols;		//Number of columns in grid
		int percentage;		//Percentage of characters in grid to be blob characters
		
		String skip;		//Used to get rest of line after integer input
		
		//Get rows from user
		System.out.println("Enter number of rows ...");
		numRows = scan.nextInt();
		skip = scan.nextLine();
		System.out.println();
		
		//Get columns from user
		System.out.println("Enter number of columns ...");
		numCols = scan.nextInt();
		skip = scan.nextLine();
		System.out.println();
		
		//Get percentage from user
		System.out.println("Enter a percentage: number from (0 - 99)");
		percentage = scan.nextInt();
		skip = scan.nextLine();
		System.out.println();
		
		//Generate grid
		Grid blobs = new Grid(numRows, numCols, percentage);
		
		//Print grid
		System.out.println(blobs);
		
		//Print number of blobs
		System.out.println("Number of blobs: " + blobs.blobCount());
	}
}
