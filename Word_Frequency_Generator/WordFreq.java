/*
 * Objects of this class will keep track of the frequency of objects of type T
 */

package app;

import java.text.DecimalFormat;

public class WordFreq implements Comparable<WordFreq>
{
	private String word;			//Object being tracked
	private int freq;		//Frequency of the object 
	
	DecimalFormat fmt = new DecimalFormat("00000");
	
	public WordFreq(String newWord) 
	//Initialize frequency to 0 ... must increment at instantiation
	{
		word = newWord;
		freq = 0;
	}
	
	public String getWord() 
	{
		return word;
	}
	
	public int getFreq() 
	{
		return freq;
	}
	
	public void inc() 
	//Increment the frequency
	{
		freq++;
	}
	
	public int compareTo(WordFreq other) 
	{
		return word.compareTo(other.word);
	}
	
	public String toString() 
	{
		return (fmt.format(freq) + " " + word);
	}
}
