package miscellaneous;

/*
 * For a given input array of numbers, find the two that result in the largest product.
 * The output should include the two numbers in the array along with their product.
 * As an extra challenge, use an input of two arrays of numbers and find two numbers - one from 
 * each input array - that results in the largest product
 */
public class ProductMaximizer {
	
	public static void main(String[] args) {
		
	}
	
	
	//gets both the largest and 2nd most largest numbers in an array
	public static int[] largestProductOfSingleArray(int[] a) {
		
		int largestItem = Integer.MIN_VALUE;
		int secondLargestItem = Integer.MIN_VALUE;
		
		for(int item: a) {
			
			if(item > largestItem) {
				secondLargestItem = largestItem;
				largestItem = item;
			}else if(item < largestItem && item > secondLargestItem)
				secondLargestItem = item;
				
		}
		
		int[] maximizer = new int[3];
		maximizer[0] = secondLargestItem;
		maximizer[1] = largestItem;
		maximizer[2] = largestItem * secondLargestItem;
		
		return maximizer;
		
	}
	
	//returns the greatest number in an array
	private static int getLargestItemFrom(int[]a) {
		
		int largestItem = Integer.MIN_VALUE;
		for(int test: a) {
			if(test > largestItem)
				largestItem = test;
		}
		
		return largestItem;
	}
	
	public static int[] largestProductOf2Arrays(int[]a, int[]b) {
		
		int largestOfA = getLargestItemFrom(a);
		int largestOfB = getLargestItemFrom(b);
		
		int[] maximizer = new int[3];
		maximizer[0] = largestOfA;
		maximizer[1] = largestOfB;
		maximizer[2] = largestOfA * largestOfB;
		
		return maximizer;
		
	}

}
