package exercises;

import java.util.Arrays;

/*
 * Write program that given two sorted arrays of N int values,prints all elements that appear
 * in both arrays,in sorted order. The running time of your program should be proportional to N in the worst case
 */
public class ArrayElementIntersection {

	public static void main(String[] args) {
		
		int[]a = {2,4,6,8,10,15,50};
		int[]b = {6,8,10,20,27,40,50};
		printIntersections(a, b);
	}
	
	public static void printIntersections(int[]a, int[]b) {
		
		//using binary search
		for(int i = 0; i < a.length; i++) {
			if(binarySearch(b, a[i]) > -1)
				System.out.print(a[i]+" ");
		}
	}
	
	private static int binarySearch(int[]a, int searchKey) {
		Arrays.sort(a);
		
		int lowIndex = 0;
		int highIndex = a.length-1;
		int middleIndex = highIndex/2;
		while(lowIndex <= highIndex) {
			middleIndex = lowIndex+(highIndex - lowIndex)/2;
			int currentItem = a[middleIndex];
			if(searchKey == currentItem)
				return middleIndex;
			else if(searchKey > currentItem) {
				lowIndex = middleIndex+1;
				
			}else if(searchKey < currentItem) {
				highIndex = middleIndex-1;
			}
		}
		return -1; //the item was not found
	}

}
