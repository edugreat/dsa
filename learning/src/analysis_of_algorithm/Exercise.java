package analysis_of_algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Exercise {

	public static void main(String[] args) {
	 
		int[]a = {2,8,9};
		int[]b = {-3,2,9,0,10};
		commonElements2(a, b);
		
	}

	
	
	/*
	 * Modify binary search so that it always returns the element with
	 * the smallest index that matches the search element (and still guarantees logarithmic running time) 
	 */
	public static int modifiedBinarySearch(int[] arr,int search){
		
		//Arrays.sort(arr);
		int low = arr[0];
		int high = arr.length-1;
		
		while(low <= high) {
			
			int middle = low+(high-low)/2;
			if(search < arr[middle]) {
				
				high = middle-1;
			}else if(search > arr[middle]) {
				
				low = middle+1;
				
			}else {
				if(middle == 0)
					return arr[middle];
				else {
					
					for(int i = middle; i >= 0; i--)
						if(arr[i] != search)
							return arr[i+1];
				}
			}
			 
		
		}
		
		return -1;
	}
	
	/*
	 * Write program that given two sorted arrays of N int values,prints all elements that appear
	 * in both arrays,in sorted order. The running time of your program should be proportional to N in the worst case
	 */
	public static String printIntersections(int[] sortedArr1,int[] sortedArr2) {
		
		String s = "";
		for(int i = 0; i< sortedArr1.length; i++)
			if(binarySearch(sortedArr2, sortedArr1[i])  != -1)
				s+=sortedArr1[i]+", ";
		return s;
		
	}
	
	private static int binarySearch(int[] arr, int search) {
		
		Arrays.sort(arr);
		//System.out.println("searching for "+ search);
		int low = 0;
		int high = arr.length-1;
		while(low <= high) {
			int middle = low+(high-low)/2;
			if(search > arr[middle]) {
				low = middle+1;
			}
				
			else if(search < arr[middle])
				high = middle-1;
			else
			{
				
				return middle;
			}
			
			
		}
		return -1;
		
		
		
	}
	
	/*
	 * Develop an algorithm for the 4-sum problem
	 */
	public static int fourSum(int[] a) {
		//counts the occurrence of four numbers whose sum is zero 
		Arrays.sort(a);
		int count = 0;
		for(int i = 0; i < a.length; i++)
			for(int j = i+1; j < a.length; j++)
				for(int k = j+1; k < a.length; k++)
					if(binarySearch(a, (-a[i]-a[j]-a[k])) > k)
						count++;
		
		return count;
		
	}
	
	//Faster 3-sum.As a warmup,develop an implementation TwoSumFaster that use a linear algorithm to
	//count the pairs that sum to zero after the array is sorted(instead of using the binary-based-search linearithmic algorithm).
	//Then apply a similar idea to develop a quadratic algorithm for the 3-sum problem
	
	public int towSumFaster(int[] a) {
		//here the array is assumed to have been sorted already
		//therefore,before passing the array to this method, it must first be sorted
		
		
		int count = 0; //counts the number of pairs that sum to zero
		for(int i = 0; i< a.length;i++)
			if(binarySearch(a, -a[i]) > i)
				count++;
		
		return count;
	}
	
	public static int faster3Sum(int[] a) {
		//array is assumed to have been sorted
		int count = 0;//counts the number of pairs that sum to zero
		for(int i = 0; i < a.length;i++)
			for(int j = i+1; j < a.length; j++)
				if(binarySearch(a, -a[i]-a[j]) > j)
					count++;
		
		return count;
	}
	/*
	 * Closest pair (in one dimension). Write a program that,given an array a[] of N
	 * double values, find the closest pair: two values whose difference is no greater than the
	 * difference of any other pair(in absolute value). The running time of your program should be 
	 * linearithmic in the worst case
	 */
	
	public static Map<String, Double> closestPair(double[] a){
		
	
		//my implementation follows this process:
		//get the smallest & second to the smallest numbers in the array
		
		//sort the array first
		Arrays.sort(a);
		
		//we wouldnt't want the array to have duplicate values
		boolean hasDuplicate = hasDuplicates(a);
		
		if(hasDuplicate)
			throw new IllegalArgumentException("Duplicate values detected!");
		
		double closestLeft = a[0];
		double closestRigth =  a[1];
		for(int i = 1; i <= a.length-2; ++i) {
			if(a[i+1] - a[i] < closestRigth-closestLeft) {
				closestLeft = a[i];
				closestRigth = a[i+1];
			}
			
				
		}
		
		
		Map<String,Double> closestPairs = new HashMap<>();
		
		closestPairs.put("left", closestLeft);
		closestPairs.put("right", closestRigth);
		
		return closestPairs;
	}
	
	private static boolean hasDuplicates(double[]a) {
		
		for(int i = 0; i  <= a.length-2; ++i)
			if(a[i+1] == a[i])
				return true;
		
		return false;
		
	}
	/*
	 * Farthest pair(in one dimension). Write a program that,given an array a[] of N doubles,
	 * find a farthest pair: two values whose difference is no greater than the difference of any other pair pair(in absolute value).
	 * The running time of your program should be linear in the worst case.
	 */
	public static String farthestPair(double[] a) {
		
		
		 double currentFarLeft = a[0];
		 double currentFarRight = a[1];
		 if(a[1] < a[0]) {
			 currentFarLeft = a[1];
			 currentFarRight = a[0];
		 }
			 
		for(int i = 2; i < a.length; i++) {
			if(a[i] > currentFarRight)
				currentFarRight = a[i];
			if(a[i] < currentFarLeft)
				currentFarLeft= a[i];
		}
		
		return String.format("farthest pairs: %s and %s", currentFarLeft, currentFarRight);
	}
	
	
	
	public static void commonElements(int[]a, int[]b) {
		//I've assumed none of the arrays has duplicates
		Arrays.sort(a);
		Arrays.sort(b);
		for(int i = 0; i< a.length;i++)
			if(Arrays.binarySearch(b, a[i]) > -1)
				System.out.println(a[i]);
	}
	/*
	 * Repeat the above exercise but assume the first array has M integers and the second
	 * has N integers where M is much less than N. Give algorithm that runs in NlogM time
	 */
	public static void commonElements2(int[] a,int[]b) {
		Arrays.sort(a);
		Arrays.sort(b);
		for(int i = 0; i< a.length; i++)
			if(Arrays.binarySearch(b, a[i]) > -1)
				System.out.println(a[i]);
	}
}
