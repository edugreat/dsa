/*
 * Design a data type that supports insert in 
logarithmic time, find median in constant time and
 * delete median in logarithmic time.
 */
//Note: it's possible to use the minimum oriented priority queue for the purpose of sorting to achieve this

package priority_queue_exercises;

public class DynamicMedianFind {
	//If not for the functionality that mandates the deletion of the median(where the median can be the mean of two elements for array of even distribution),
	//the queue is better implemented using an array of Integer
	private Integer[] minPQ;
	private int N;
	private int median; //median instance variable
	
	private int[] sorted; //array of sorted elements from which the median is found
	public DynamicMedianFind() {
		minPQ = new Integer[10];//initialize the minPQ[] to an array of size 10 by default
	}
	
	private boolean isEmpty() {
		
		return N == 0;
	}
	
	private int size() {
		
		return N;
	}
	
	private void resize(int sz) {
		Integer[] a = new Integer[sz];
		for(int i = 1; i <= N; i++) {
			a[i] = minPQ[i];
		}
		
		minPQ = a;
	}
	
	private boolean less(int i, int j) {
		
		return i < j;
	}
	
	private void exchange(int i, int j) {
		
		int k = minPQ[i];
		minPQ[i] = minPQ[j];
		minPQ[j] = k;
		
	}
	
	private void sink(int index) {
		
		while(2*index <= N) {
			
			int j = 2*index;
			if(j < N && less(minPQ[j+1], minPQ[j]))
				j++;
			if(less(minPQ[index], minPQ[j]))
				break;
			
			exchange(index, j);
			
			index = j;
		}
	}
	
	private void swim(int index) {
		
		while(index > 1 && less(minPQ[index], minPQ[index/2])) {
			exchange(index, index/2);
			
			index/=2;
		}
	}
	
	public void insert(int x) {
		
		if(minPQ.length-1 == N)
			resize(N*2);
		
		minPQ[++N] = x;
		swim(N);
	}

	private int del() {
		int value = minPQ[1];
		exchange(1, N--);
		
		minPQ[N+1] = null;
		sink(1);
		
		return value;
	}
	
	private int findMedianIndex() {
		
		sorted = new int[N];
		int index = 0;
		while(! isEmpty()) {
			sorted[index++] = del();
		}
		
		return (sorted.length - 1)/2;
		
	}
	
	public int findMedian() {
		int medianIndex  = findMedianIndex();
		
		median =  sorted[medianIndex];
		
		return median;
	}
	
	//deletes the median
	public int deleteMedian() {
		int med  = median;//gets the median to delete from queue
		for(int in: sorted) {
			//better check would've been the use of compareTo method for Integers
			if(in != med) {
				insert(in);
			}
		}
		
		return med;//returns the just deleted median to the caller
	}
	
	
	public static void main (String[] args) {
		DynamicMedianFind dmf = new DynamicMedianFind();
		Integer[]i = {5,1,0,7,2,9,11,10,6,4,3,19,39,10};
		for(int in:i)
			dmf.insert(in);
		int median = dmf.findMedian();
		System.out.println(median);
		
		System.out.println(dmf.deleteMedian());
		median = dmf.findMedian();
		System.out.println(median);
			
	}
}
