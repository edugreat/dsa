package priority_queue_exercises;

/*
 * Write a data type 'Domain' that represents domain names, including an appropriate compareTo()
 * method where the natural order is in order of the reverse domain name. For example, the reverse domain 'cs.princeton.edu' is 'edu.princeton.cs'.
 * Hint: Use s.plit("\\.") to split the string s into tokens, delimited by dots.
 * Write a client that reads domain names from
 * the standard input and  prints the reverse domains in sorted order.
 */

//note: here I used min-heap priority queue to achieve the task
public class Domain {
	private String[] pq;
	private int N;
	
	public Domain(int sz) {
		pq = new String[sz+1];
	}
	
	//get the size of the priority queue
	private int size() {
		
		return N;
	}
	
	//asserts priority queue to be empty
	private boolean isEmpty() {
		return N == 0;
	}
	
	//implements compareTo method to determine whether a domain is less than another domain
	private boolean less(String x, String y) {
		
		String[] i = x.split("\\.");
		String[] j = y.split("\\.");
		
		for(int k = 0; k < i.length; k++) {
			
			if(i[k].compareTo(j[k]) < 0)
				return true;
			
			if(i[k].compareTo(j[k]) > 0)
				return false;
			
		}
		
		return false;
		
	}
	
	//swaps the elements' positions
	private void swap(int i, int j) {
		
		String s = pq[i];
		pq[i] = pq[j];
		pq[j] = s;
	}
	
	//reverses and returns the order of domain
	private String reverse(String s) {
		
		String[] sp = s.split("\\.");
		
		String str = "";
		for(int i = sp.length-1; i >= 0; i--) {
			str += (i == 0 ? sp[i] : sp[i]+".");
		}
		
		return str;
	}
	
	private void swim(int index) {
		
		while(index > 1 && less(pq[index], pq[index/2])) {
			
			swap(index, index/2);
			
			index/=2;
			
		}
	}
	
	private void sink(int index) {
		
		while(2 * index < N) {
			
			int j = 2*index;
			
			if(j < N && less(pq[j+1], pq[j])) {
				j++;
			}
			
			if(less(pq[index], pq[j]))
				break;
			
			swap(index, j);
			
			index = j;
		}
	}

	//for dynamic property
	private void resize(int sz) {
		
		String[] s = new String[sz];
		for(int i = 1; i <= N; i++) {
			s[i] = pq[i];
		}
		
		pq = s;
	}
	
	
	public void insert(String s) {
		
		
		if(N+1 == pq.length - 1)
			resize(pq.length * 2);
		
		String x = reverse(s);
		pq[++N] = x;
		swim(N);
	}
	
	private String delMin() {
		
		String s = pq[1];
		
		swap(1, N--);
		
		sink(1);
		pq[N+1] = null;
		
		return s;
	}
	
	//method that outputs the items in the queue in sorted order
	
	public String[] get() {
		
		
		int index = 0;
		String[] s = new String[N];
		
		while(! isEmpty()) {
			
			s[index++] = delMin();
		}
		
		
		return s;
	}
	
	public static void main(String[] args) {
		
		//initialize an array of some domain names
		String[] s = {"cs.princeton.edu", "cs.princeton.edu", "bio.hackerank.learn", "chem.bootcamp.io", "math.codechef.learn"};
		
		//initialize the Domain data types
		Domain d = new Domain(10);
		for(String x: s)
			d.insert(x); //inserts into the Domain data type
		
		String[] y = d.get(); //get the reversed sorted domain names from the Domain data type
		
		
		for(String str: y)
			System.out.println(str); //print the reversed sorted domain names
	}

}
