package sorting_exercises;

import java.util.ArrayList;
import java.util.List;

/*
 * Implement a natural mergesort for linked lists.
 */
public class LinkedListSort {

	public static void main(String[] args) {
		
	}
	
	
 public  static <T extends Comparable<T>>  void mergeSort(List<T> t) {
	 
	 
	 
	 if(t.size() < 2)
		 return;
	 
		 int hi = t.size();
		 int middle = hi/2;
		 
	 List<T> leftHalf = new ArrayList<>(t.subList(0, middle));
	 
	 
	  
	  List<T> rightHalf = new ArrayList<>(t.subList(middle, hi));
	  
	  mergeSort(leftHalf);
	  mergeSort(rightHalf);
	  merge(t, leftHalf, rightHalf);
	 
 }
 
 private static <T extends Comparable<T>> void merge(List<T> original, List<T> leftHalf, List<T> rightHalf) {
	
	 int x = 0;//for leftHalf list
	 int y = 0;//for rightHalf list
	 int z = 0;//for the list to which we intend to merge both left and right
	
	 while(x < leftHalf.size() && y < rightHalf.size()) {
		 if(lessOrEquals(leftHalf.get(x), rightHalf.get(y)))
			 original.set(z++, leftHalf.get(x++));
		 else
			 original.set(z++, rightHalf.get(y++));
	 
	 }
	 while(x < leftHalf.size())
		 original.set(z++, leftHalf.get(x++));
	 
	 while(y < rightHalf.size())
		 original.set(z++, rightHalf.get(y++));
 }
 
 private static <T extends Comparable<T>> boolean lessOrEquals(T a, T b) {
	 
	 return (a.compareTo(b) < 0) || (a.compareTo(b) == 0);
 }
}
