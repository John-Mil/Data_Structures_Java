//Receives Strings from the user and checks whether the input String is a palindrome

package palindrome;

import java.util.Scanner;

public class PalindromeApp 
{
	
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		
		String input;
		boolean isPalindrome;
		
		System.out.println("Enter a sentence to see if it's a palindrome ... press enter to stop");
		
		input = scan.nextLine();
		
		while(input.length() > 0) 
		{
			isPalindrome = Palindrome.test(input);
			if(isPalindrome) 
				System.out.println(input + " ...\n" + "Is a palindrome! \n");
			else
				System.out.println(input + " ...\n" + "Is NOT a palindrome! \n");
			input = scan.nextLine();
		}
		
		System.out.println("Goodbye!");
	}
}
