package priority_queue_exercises;


/*
 * Implement a method String[] dedup(String[]a) that returns the objects in a[],in sorted order
 * with duplicates removed
 */

//Steps to tackle this:
// Implement a method that sorts a[],preferably using insertion sort
// implement dedup(String[]a) that removes duplicates from a[]
public class NoDuplicates {
	
	private static boolean less(int i, int j, String[] a) {
		
		return a[i].compareTo(a[j])  < 0;
		
	}
	
	private static void swap(int i, int j, String[]a) {
		String u = a[i];
		a[i] = a[j];
		a[j] = u;
	}

	private static void sort(String[]a) {
		
		for(int i = 0; i < a.length; i++) {
			
			for(int j = i; j-1 >= 0; j--) {
				
				if(less(j, j-1, a))
					swap(j, j-1, a);
			}
		}
		
		
	}
	
	//method that returns true if two String objects are duplicates
	private static boolean isDups(String x, String y) {
		
		return x.compareTo(y) == 0;
	}
	//method that get the number of duplicates there are a[]
	private static int duplicates(String[] a) {
		
		int dups = 0;
		
		for(int i = 1; i < a.length; i++) {
			if(isDups(a[i], a[i-1]))
				dups++;
		}
		
		return dups;
	}
	
	private static String[] dedup(String[] a) {
		
		sort(a); //sort a[] first
		
		int dups = duplicates(a);
		
		if(dups == 0)
			return a; //if there are no duplicates, just return the sorted array object
		
		int len = a.length;
		
		
		String[] unique = new String[len - dups]; // declare an array of size without the duplicates
		int index = 0;
		
		for(int i = 1; i < a.length; i++) {
			//if we're at the end of the array and the current object isn't same with the object before it, include all to the unique[]
			if(len - i == 1 && !isDups(a[i], a[i - 1])) {
				unique[index++] = a[i - 1];
				unique[index] = a[i];
				
				a = unique; // compensates for space allocation
				break;
			}
			
			//if we're at the end of the array and the current object is same with the object  before it, just add any one of them to unique[]
			if(len - i == 1 && isDups(a[i], a[i-1])) {
				unique[index] = a[i];
				
				a = unique;
				break;
			}
			
			//traverse the array until we're able to locate two non-duplicate contiguous objects
			if(! isDups(a[i-1], a[i]))
				unique[index++] = a[i-1];
			
		}
		
		return a;
	}
	public static void main(String[] args) {
		String[] a = {"A", "B", "Y", "C", "A","C","D","E","B","Z","X","W","K","X", "A","B","C"};
		
		String[] unique = dedup(a);
		for(String s: unique)
			System.out.print(s+" ");

	}

}
