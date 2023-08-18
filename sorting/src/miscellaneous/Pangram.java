package miscellaneous;

/*
 * A pangram is a sentence that contains all the 26 letters of the English alphabets. One othe most well
 * known examples of a pangram is, 'The quick brown fox jumps over the lazy dog. Create a pangram checker
 * that returns a boolean TRUE if an input string is a pangram and FALSE if it isn't.
 */
public class Pangram {

	public static void main(String[] args) {
	}
	
	private static boolean checker(char c, String sentence) {
		sentence = sentence.toLowerCase().trim(); // returns the sentence in lower case order for uniform comparisons
		
		return sentence.indexOf(c) != -1; //-1 index indicates absence of a given character from the input sentence
	}
	
	public static boolean pangramChecker(String sentence) {
		
		//iterates overs all the lower case English alphabet and checks the existence of pangram
		for(char c = 'a'; c <= 'z'; c++) {
			
			if(! checker(c, sentence)) return false;
		}
		
		return true;
		
	}

}
