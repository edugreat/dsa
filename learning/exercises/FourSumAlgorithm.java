package exercises;

import java.util.Arrays;

// Develop an algorithm for the 4-sum problem
// a four-sum problem is a situation where in an array
//of integers, there exist four numbers whose addition would give zero
public class FourSumAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static int fourSum(int[]a) {
		//For there to exist in the array four numbers whose sum is zero,
		//this means there exists in the array some certain numbers
		//whose value is the negative of the sum of three numbers existing else where in the array
		Arrays.sort(a);
		int sum = 0;
		for(int i = 0; i< a.length; i++) {
			for(int j = i+1; j < a.length; j++) {
				for(int k = j+1; k < a.length; k++) {
					if(Arrays.binarySearch(a, -(a[i]+a[j]+a[j])) > j)
						sum+=1;
				}
			}
		}
		
		return sum;
		
	}

}
