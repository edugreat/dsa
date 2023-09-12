/*
 * Min/Max priority queue. Design  a data type that supports the following 
   operations: insert, delete the maximum,
 * and delete the minimum(all in constant time); and find the maximum and find the minimum(both in a constant time).
 * Hint: Use two heaps
 */
package priority_queue_exercises;

@SuppressWarnings("unchecked")
public class MinMaxPQ<Key extends Comparable<Key>> {
	
	private Key[] minPQ; //minimum oriented priority queue
	private Key[]maxPQ;  //maximum oriented priority queue
	
	private int N;      //queue size
	public MinMaxPQ(int sz) {
		minPQ = (Key[])new Comparable[sz+1]; //initialize the queues to empty arrays of priority queues
		maxPQ = (Key[])new Comparable[sz+1];
	}
	
	//get the size of the priority queues
	private int size() {
		
		return N;
		
	}
	
	//asserts the queues are empty
	private boolean isEmpty() {
		return N == 0;
	}
	
	//determines if item k1 is less than item k2
	private boolean less(Key k1, Key k2) {
		
		return k1.compareTo(k2) < 0;
	}
	
	//swaps the ith and jth items of the priority queues
	private void swap(int i, int j, Key[] pq) {
		
		Key k = pq[i];
		pq[i] = pq[j];
		pq[j] = k;
		
		
	}
	
	//reorders the queue elements by sinking the topmost element down to its rightful position
	private void sink(int index, Key[] pq) {
		
		if(pq == minPQ) {
			
			while(2* index <= N) {
				int j = 2*index;
				
				if(j < N && less(minPQ[j+1], minPQ[j]))
					j++;
				
				if(less(minPQ[index], minPQ[j]))
					break;
				
				swap(index, j, minPQ);
				
				index = j;
			}
			
		}else if(pq == maxPQ){
			
			while(2* index <= N) {
				
				int j = 2*index;
				if(j < N && less(maxPQ[j], maxPQ[j+1]))
					j++;
				
				
				if(less(maxPQ[j], maxPQ[index]))
					break;
				
				swap(index, j, maxPQ);
				index = j;
			}
		}
	}

	//reorders the queue elements by swimming the just inserted element up to its rightful position
	private void swim(int index, Key[] pq) {
		
		if(pq == minPQ) {
			
			while(index > 1 && less(minPQ[index], minPQ[index/2])) {
				swap(index, index/2, minPQ);
				
				index/=2;
			}
				
		}else if(pq == maxPQ) {
			
			while(index > 1 && less(maxPQ[index/2], maxPQ[index])) {
				swap(index, index/2, maxPQ);
				
				index/=2;
			}
				
		}
	}
	
	//inserts new element to the queues
	public void insert(Key v) {
		
		if(N+1 == minPQ.length-1) {
			resize(N*2 +1);
		}
		minPQ[++N] = v;
		swim(N, minPQ);
		
		maxPQ[N] = v;
		swim(N, maxPQ);
	}
	
	//resizes the queue to accommodate new elements
	private void resize(int sz) {
		
		Key[] q1 = (Key[]) new Comparable[sz];
		Key[] q2 = (Key[]) new Comparable[sz];
		
		for(int i = 1; i <= N; i++) {
			q1[i] = minPQ[i];
			q2[i] =maxPQ[i];
		}
		
		minPQ = q1;
		maxPQ = q2;
			
	}
	
	//deletes the minimum elements of the queue
	public Key delMin() {
		
		if(size() == 0)
			return null;
		
		Key k = minPQ[1];
		swap(N--, 1, minPQ);
		sink(1, minPQ);
		
		minPQ[N+1] = null;
		
		heapify(maxPQ);
			
		return k;
	}
	
	//delete and returns the maximum elements of the queue.Returns null if all elements have been deleted
	public Key delMax() {
		if(size() == 0)
			return null;
		
		Key k = maxPQ[1];
		swap(N--, 1, maxPQ);
		maxPQ[N+1] = null;
		sink(1, maxPQ);
		heapify(minPQ);
		
		return k;
	}
	
	
	//finds and returns the maximum element from the queues
	public Key findMaximum() {
	
		return maxPQ[1];
	}
	
	//Finds and returns the minimum elements from the queue
	public Key findMinimum() {
		
		return minPQ[1];
	}
	
	//re-heapifies the queues  to ensure consistency and currentness 
	private void heapify(Key[] pq) {
		
		int len = N;
		N = 0;
		if(pq == minPQ) {
			pq = (Key[])new Comparable[len+1];
			for(int i = 1; i <= len; i++) {
				
					pq[++N] = maxPQ[i];
					swim(N, pq);
				
			}
			
			
		}else if(pq == maxPQ) {
			pq = (Key[]) new Comparable[len+1];
			for(int i = 1; i <= len; i++) {
				
					pq[++N] = minPQ[i];
					swim(N, pq);
				
			}
			
			
		}
			
		}
		
	
	
	public static void main(String[] args) {
		
		Integer[] in = {4,1,0,8,2,7,5,9,10};
		MinMaxPQ<Integer> pq = new MinMaxPQ<Integer>(10);
		for(int i:in) {
			pq.insert(i);
		}
		
	
		System.out.println("max = "+pq.findMaximum());
		System.out.println("min = "+pq.findMinimum());
	    System.out.println(pq.delMax());
		System.out.println(pq.delMin());
		System.out.println("max = "+pq.findMaximum());
		System.out.println("min = "+pq.findMinimum());
		System.out.println(pq.delMin());
		System.out.println(pq.delMax());
		
	}
}
