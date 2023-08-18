package miscellaneous;

public class WordFind {
	/*
	 * Implement an algorithm that accepts some words and a second argument n, then prints the nth
	 * word in the words. For instance, for the words 'I love programming', if n = 3,then it should
	 * print the 3rd word in the sentence(which is 'programming')
	 */

	public static void main(String[] args) {
		
		

	}
	public static String search(String words, int n) {
		String[] s = words.trim().split("\\s");
		if(s.length < n )
			return "Out of range";
		
		return s[n-1];
	}

}
