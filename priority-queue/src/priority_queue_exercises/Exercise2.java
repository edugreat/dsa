package priority_queue_exercises;

/*
 * Give the heap that results when the keys E A S Y Q U E S T  I O N 
 * are inserted in that order into an initially empty max-oriented heap
 */
@SuppressWarnings("unchecked")
public class Exercise2<Key extends Comparable<Key>> {

	private int N;
	private Key[]pq;
	private static final int INITIAL_SIZE = 10;
	
	public Exercise2() {
		pq = (Key[])new Comparable[INITIAL_SIZE];
	}
	
	private int size() {
		
		
		
		return N;
	}
	
	private boolean isEmpty() {
		
		
		return N == 0;
	}
	
	private void resize(int sz) {
		Key[] temp = (Key[]) new Comparable[sz];
		
		for(int i = 1; i <= N; i++) {
			temp[i] = pq[i];
		}
		
		pq = temp;
	}
	
	private boolean less(int i, int j) {
		
		return pq[i].compareTo(pq[j]) == -1;
	}
	
	private void exchange(int i, int j) {
		Key v = pq[i];
		pq[i] = pq[j];
		pq[j] =v;
	}
	
	private void sink(int index) {
		while(2 * index <= N) {
			
			int j = 2*index;
			if(j < N && less(j, j+1))
				j++;
			if(! less(index, j))
				break;
			exchange(index, j);
			
			index = j;
		}
	}
	
	private void swim(int index) {
		
		while(index > 1 && less(index/2, index)) {
			
		
				exchange(index/2, index);
			
			
			index /=2;
		}
	}
	
	
	private void put(Key v) {
		
		if(N+1 == pq.length)
			resize(1+ N *2);
		pq[++N] = v;
		swim(N);
		
		
	}

	private Key delMax() {
		
		Key k = pq[1];
		exchange(1, N--);
		
		sink(1);
		pq[N+1] =null;
		
		if(4*N <= pq.length)
			resize(1+ 2*N);
		
		return k;
	}
	
	//method that displays the internal arrangements of the queue
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("[ ");
		
		while(! isEmpty()) {
			
			if(size() > 1) {
				builder.append(delMax()).append(", ");
			}
			else
			
			builder.append(delMax());
		}
			
		
		return builder.append(" ]").toString();
	}
	
	public static void main(String[] args) {
		
		
		String[] c = {"E","A","S","Y","Q","U","E","S","T","I","O","N"};

		Exercise2<String> pq = new Exercise2();
	  for(String ch: c) {
		  pq.put(ch);
	  }
	  
	  System.out.println(pq);
	  
		
	  // the output shows the items in the priority queue was of the arrangement: [ E, N, O, I, T, S, E, U, Q, Y, S, A ]
	}
	
}
