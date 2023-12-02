package exercises;

/*
 * Closest pair (in one dimension). Write a program that,given an array a[] of N
 * double values, find the closest pair: two values whose difference is no greater than the
 * difference of any other pair(in absolute value). The running time of your program should be 
 * linearithmic in the worst case
 */

public class ClosestPairs {

	public static void main(String[] args) {
		double[]a = {9.8,99,1.0,4.9,0.9,0.7,1,2,-3.9,-3.8};
		String closest = closest(a);
		System.out.println(closest);
		
		
	}
	public static String closest(double[]a) {
		
		double smallestDiff = Double.MAX_VALUE;
		double firstPair = 0;
		double secondPair = 0;
		
		for(int i = 0; (i+1) < a.length; i++) {
			if((Math.abs(a[i+1] - a[i])) < smallestDiff) {
				smallestDiff = Math.abs(a[i+1] - a[i]);
				firstPair = a[i];
				secondPair = a[i+1];
			}
		}
		
		return String.format("closest pairs: %s", firstPair+", "+secondPair);
	}

}
