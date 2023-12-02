package miscellaneous;

/*
 * For a given input number, return the number in reverse order, maintaining the decimal place.
 * For instance, the given input number 193.56 should return 653.91
 */
public class Number_Reversal {

	public static void main(String [] args) {
		
		
	}
	
	public static double reverse(double number) {
		
		final String temp = String.valueOf(number);//gets the string value of 'number'
		final int DECIMAL_POINT_INDEX = temp.indexOf("."); //gets the index of the decimal point
		final int LENGTH = temp.length();//length of the character
		final double DENOMINATOR = Math.pow(10, LENGTH-(DECIMAL_POINT_INDEX+1)); //get the actual denominator(non zero-based)
		
		
		String s ="";
		for(int i = temp.length()-1; i >= 0; i--) {
			if(temp.indexOf(temp.charAt(i)) != DECIMAL_POINT_INDEX) {
				s = s+temp.charAt(i);
			}
		}
		
		return Double.parseDouble(s)/DENOMINATOR;
		
	}
}
