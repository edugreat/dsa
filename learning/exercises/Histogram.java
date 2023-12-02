package exercises;


/*
 * Write a static method histogram() that takes an array a[] of int values and
 * an integer M as argument and return an array of length M whose ith entry is
 * the number of times the integer i appeared in the argument. If the values in
 * a[] are all between 0 and M-1, the sum of the values in the returned array
 * should be equal to a.length
 */

public class Histogram {

	public static void main(String[] args) {
		int[] a = {1,2,3,2,2,4,4,4,1,1,0,4};
		int[] freq = histogram(a, 5);
		System.out.println(freq[4]);

	}
	
	private static int[] histogram(int[] a, int M) {
		//note, the ith entry of the array of length M shows the frequency of the entry in a[]
		int count = 0;// frequency of occurrence of each ith entry in a[]
		int[] freqArray = new int[M];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < a.length; j++) {
				if(a[j] == i)
					count++;
				
			}
			freqArray[i] = count;
			count = 0;
		}
		return freqArray;
	}

}
