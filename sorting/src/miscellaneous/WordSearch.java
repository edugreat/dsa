package miscellaneous;

/*
 * For an input string, return a Boolean true if the string starts with a given
 * input word.
 */
public class WordSearch {

	public static void main(String[] args) {
		
	}
	
	public static boolean search(String words, String test) {
		
		String[] s = words.trim().split("\\s");
		
		return s[0].equals(test);
	}

}
