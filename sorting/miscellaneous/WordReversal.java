package miscellaneous;

import java.util.Scanner;

/*
 * Implement an algorithm that accepts a String input and returns
 * an output of the String in reversed order
 */
public class WordReversal {

	public static void main(String[] args) {
		
		
	}
	public static String reverse(String words) {
		
		
		String[]s = words.split("\\s");
		String output = "";
		for(int i = s.length-1; i >= 0; i--)
			output+=s[i]+" ";
		
		return output;
		
		
	}

}
