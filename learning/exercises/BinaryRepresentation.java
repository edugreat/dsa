package exercises;

/*
 * Write a code that outputs the binary representation
 * of a positive integer N into a string s.
 * 
 */

public class BinaryRepresentation {

	public static void main(String[] args) {
		
	}
	
	private static String binary(int number) {
		
		if(number < 0)
			return String.format("Value %s not supported", number);
		if(number == 0)
			return 0+"";
		if(number == 1)
			return ""+1;
		
		return binary(number/2)+""+(number%2);
		}
			
		
		
	}


