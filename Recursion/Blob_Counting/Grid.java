/*
 * Objects of this class represent a grid of blob and nonblob characters
 * Provides method to count the number of blobs in a specific grid
 * User defines the size of the grid as well as the percentage of blob characters
 */

package blobs;

import java.util.Random;

public class Grid 
{
	
	private boolean[][] grid;				//Represents a blob character if index (r,c) is true
	private boolean[][] visited;			//Declares blob characters as being visited for blob counting
	private int rows;						//Number of rows in a grid
	private int cols;						//Number of columns in a grid
	
	public Grid(int rows, int cols, int percentage) 
	{
		//Set rows and cols
		this.rows = rows;
		this.cols = cols;
		
		//Set up random number generation
		int randInt;
		Random rand = new Random();
		
		//Instantiate the grid
		grid = new boolean[rows][cols];
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < cols; c++) 
			{
				randInt = rand.nextInt(100); //Random number 0 - 99
				if(randInt < percentage)
					grid[r][c] = true;
				else
					grid[r][c] = false;
			}
	}
	
	
	public String toString() 
	//Returns a visual representation of the grid with "X"s representing blob characters
	//and "-"s representing nonblob characters
	{
		String str = "";
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; c++) 
			{
				if(grid[r][c])
					str += "X";
				else
					str += "-";
			}
			str += "\n"; //end of row
		}
		return str;
	}
	
	public int blobCount() 
	//Counts the number of blobs in this grid and returns
	{
		int numBlobs = 0; //number of blobs
		
		//Instantiate visited array
		visited = new boolean[rows][cols];
		
		//Check for blobs in the grid
		//If the current index (r,c) is a blob that hasn't been visited, mark it
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < cols; c++) 
				if(grid[r][c] && !visited[r][c])
				{
					markBlob(r, c);
					numBlobs++;
				}
		return numBlobs;
	}
	
	private void markBlob(int r, int c) 
	//Marks the blob represented by position (r,c) as visited
	//Checks the blobs neighbors and if they are blobs then marks them too
	//essentially marking all the other characters in the blob
	{
		//Set the blob character as visited
		visited[r][c] = true;
		
		//Check the character above this blob
		if((r-1) >= 0) 		//row above this blob is in grid
			if(grid[r-1][c]) 	//character is a blob
				if(!visited[r-1][c]) 	//character has not been visited (not in another blob)
					markBlob(r-1, c);		//then mark it
		
		//Check the character below this blob
		if((r+1) < rows) 		//row below this blob is in grid
			if(grid[r+1][c]) 	//character is a blob
				if(!visited[r+1][c]) 	//character has not been visited (not in another blob)
					markBlob(r+1, c);		//then mark it
		
		//Check the character to the left of this blob
		if((c-1) >= 0) 		//col next to this blob is in grid
			if(grid[r][c-1]) 	//character is a blob
				if(!visited[r][c-1]) 	//character has not been visited (not in another blob)
					markBlob(r, c-1);		//then mark it
		
		//Check the character to the right of this blob
		if((c+1) < cols) 		//col next to this blob is in grid
			if(grid[r][c+1]) 	//character is a blob
				if(!visited[r][c+1]) 	//character has not been visited (not in another blob)
					markBlob(r, c+1);		//then mark it
	}
	
}
