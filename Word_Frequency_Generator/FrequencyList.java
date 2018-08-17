/*
 * Displays a word frequency list of the words listed in the input file
 * Only displays words of a minimum size and a minimum frequency
 * 
 * Assumes the document to be examined is in a text file "words.dat"
 */

package app;

import java.util.Scanner;
import java.io.*;

public class FrequencyList {

	public static void main(String[] args) throws IOException
	{
		int minSize;		//Minimum word size
		int minFreq;		//Minimum word frequency
		String skip;		//To get rest of line after reading in int
		
		String word;			//Each word to process
		int numWords = 0;		//Total number of words in text file
		int numValidWords = 0;	//Total number of words with minimum length
		int numValidFreq = 0;	//Total number of words with minimum frequency
		WordFreq wordToTry;		//New WordFreq object for every word of minimum length
		WordFreq wordInTree;	//WordFreq object to test if word is already in tree
		WordFreq wordFromTree;	//Get WordFreq object from tree to print
		int treeSize;			//Number of WordFreq objects made (treeSize == numValidWords)
		
		
		BinarySearchTree<WordFreq> tree = new BinarySearchTree<WordFreq>();
		
		//Set up file reading
		FileReader fin = new FileReader("words.dat");
		Scanner wordsIn = new Scanner(fin);
		wordsIn.useDelimiter("[^a-zA-Z0-9]");
		
		//Set up console reading
		Scanner conIn = new Scanner(System.in);
		
		//Get word and frequency limits from the user
		System.out.println("Minimum word size: ");
		minSize = conIn.nextInt();
		skip = conIn.nextLine();
		
		System.out.println("Minimum word frequency: ");
		minFreq = conIn.nextInt();
		skip = conIn.nextLine();
		
		while(wordsIn.hasNext())	//While more words to process 
		{
			word = wordsIn.next();
			numWords++;
			if(word.length() >= minSize) 
			{
				numValidWords++;
				word = word.toLowerCase();
				wordToTry = new WordFreq(word);
				wordInTree = tree.get(wordToTry);
				if(wordInTree == null) //This word not in tree already
				{
					wordToTry.inc();	//Set frequency to 1
					tree.add(wordToTry);
				}
				else //This word already in tree 
				{
					wordInTree.inc();	//Increment frequency
				}
			}
		}
		
		//Print words with minFreq
		treeSize = tree.reset(BinarySearchTree.INORDER);
		System.out.println("The words of length " + minSize + " and above,");
		System.out.println("with frequency counts of at least " + minFreq + "\n");
		System.out.println("Freq       Word");
		System.out.println("-----      --------------");
		
		for(int i = 1; i <= treeSize; i ++) 
		{
			wordFromTree = tree.getNext(BinarySearchTree.INORDER);
			if(wordFromTree.getFreq() >= minFreq) 
			{
				numValidFreq++;
				System.out.println(wordFromTree);
			}
		}
		
		//Statistics
		System.out.println();
		System.out.println(numWords + " words in the input file");
		System.out.println(numValidWords + " of them are at least " + minSize + " characters");
		System.out.println(numValidFreq + " of them occur at least " + minFreq + " times");
		System.out.println("Program completed");
	}

}
