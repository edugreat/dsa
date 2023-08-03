package exercises;

/*
 * Farthest pair(in one dimension). Write a program that, given an array a[] of N doubles,
 * find a farthest pair: two values whose difference is no less than the difference of any other pair pair(in absolute value).
 * The running time of your program should be linear in the worst case.
 */
public class FarthestPair {

	public static void main(String[] args) {
		double[]a = {9.8,99,1.0,4.9,0.9,0.7,1,2,-3.9,-3.8};
		String closest = farthest(a);
		System.out.println(closest);
		

	}
	
	public static String farthest(double[] a) {
		
		double largestDifference = Double.MIN_VALUE;
		double firstPair = 0.0;
		double secondPair =  0.0;
		for(int i = 1; i < a.length; i++) {
			if(Math.abs(a[i] - a[i-1]) > largestDifference) {
				largestDifference = Math.abs(a[i] - a[i-1]);
				firstPair = a[i-1];
				secondPair = a[i];
			}
		}
		
		return String.format("farthest pairs: %s", firstPair+", "+secondPair);
	}

}
