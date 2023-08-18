package miscellaneous;

import java.util.ArrayList;
import java.util.List;

/*
 * An Armstrong number is a whole number that's equal to the sum of it's digits raised to the power
 * of the total number of digits. For instance 153 = (1 to the power of 3 + 3 to the power of 3 + 5 to the power of 3).
 * Create an Armstrong checker that return the Boolean TRUE if the input is an Armstrong. Also create an Armstrong number calculator
 * that returns all Armstrong numbers between 0 and the input number
 */
public class Armstrong_Numbers {
	
	public static void main(String[] args) {
	
		
	}
	
	//returns the N raised to the power of 'pow'
	private static double toPower(int N, int pow) {
		
		if(N == 0)
			return 1;
		
		
		return Math.pow(N, pow);
	}

	//returns the sum of each digit raised to the power of the number of digits there are in N.
	public static double checker(int N, int pow) {
		
		if(N%10 == 0)
			return 0;
		
		return toPower(N%10, pow) + checker(N/10, pow);
		
	}
	private static boolean isArmstrong(int N) {
		
		String stringVal = String.valueOf(N);
		final int LENGTH = stringVal.length();
		
		return checker(N, LENGTH) == N;
	}
	
	
	//returns all a list of all the armstrong values between zero and the 'range' argument
	public static List<Double> armstrongCalculator(int range) {
		
		if(range <= 0)
			return null;
		
		List<Double> list = new ArrayList<>();
		for(int test = 1; test < range; test++) {
			if(isArmstrong(test))
				list.add((double)test);
		}
		
		return list;
	}
	
}
