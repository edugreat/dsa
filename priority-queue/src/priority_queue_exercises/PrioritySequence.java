package priority_queue_exercises;

import java.util.PriorityQueue;

/*
 *  Suppose that the sequence P R I O * R * * I * T * Y * * * Q U E * * * 
U * E (where a letter means insert and an asterisk means remove the maximum) is applied to an initially empty priority queue. Give the sequence of letters returned by the 
remove the maximum operations 
 */

// A priority queue can be implemented and have this tested
@SuppressWarnings("unchecked")
public class PrioritySequence<Key extends Comparable<Key>> {
	
	private Key[]pq; //the priority queue
	private int N; //size the priority queue
	
	
	public PrioritySequence(int sz) {
		pq = (Key[]) new Comparable[sz];
	}
	
	
	public void push(Key v) {
		

		if(N >= pq.length-2)
			resize(pq.length*2 +1);
		
		
		
		pq[++N] = v;
		
		swim(N);
		//System.out.println("N: "+N);
		
		
	}
	
	public Key delMax() {
		
		Key v = pq[1];
		
		//exchanges the index of the root node with the last node, and also decrements the queue size
		exchange(1, N--);
		
		sink(1);
		
		pq[N+1] = null;
		
		//System.out.println("N = "+N);
		if(N > 0 && N <= pq.length/4)
			resize(pq.length/2);
		
		return v;
		
		
	}
	
	private int size() {
		return N;
	}
	
	private boolean isEmpty() {
		
		return N == 0;
	}
	
	private boolean less(int i,int j) {
		
		return pq[i].compareTo(pq[j]) == -1;
	}
	
	private void exchange(int i, int j) {
		
		Key v = pq[i];
		pq[i] = pq[j];
		pq[j] = v;
	}
	
	
	private void swim(int i) {
		
		//check if the parent node is less the child node
		while(i > 1 && less(i/2  ,i)) {
			
			//swaps the position of the parent node with that of the child node
			exchange(i/2 , i);
			i/=2; //change the next index to point to the parent node
			
		}
	}
	
	private void sink(int i) {
		
		while(2*i <= N) {
			
			//get the index of the left child of the parent node
			int j = 2*i;
			
			//check if the left child is less than its right sibling
			if(less(j, j+1))
				j++;
			
			//break out of the loop if the parent node is greater than the child node
			if(! less(i, j))
				break;
			
			//exchange the positions of parent and child node
			exchange(i, j);
			
			i = j; //change the parent node the child node for subsequent comparisons
			
			
		}
	}

	private void resize(int sz) {
		
		Key[] tempPq = (Key[]) new Comparable[sz];
		
		for(int i = 1; i <= N; i++)
			tempPq[i] = pq[i];
		
		pq = tempPq;
	}
	
	public static void main(String[] args) {
		
		PriorityQueue<String> q = new PriorityQueue<>();
		
		Character[] seq = {'P','R','I','O','*','R','*','*','I','*','T','*','Y','*','*','*','Q','U','E','*','*','*','U','*','E'};
		PrioritySequence<Character> pq = new PrioritySequence<>(10);
		for(char c: seq) {
			
			if(c != '*')
				pq.push(c);
			else if(! pq.isEmpty()) {
				System.out.print(pq.delMax()+" ");
			}
		}
		
		//it shows that the output is: P O R I I T Y R Q E U U 
	}
}
