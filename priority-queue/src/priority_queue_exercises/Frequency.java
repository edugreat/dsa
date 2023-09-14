/*
 * Write a program 'Frequency' that reads strings from the standard input and prints
 * the number of times each string occurs, in descending order.
 */
package priority_queue_exercises;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Frequency {
	//map that holds the stream of string inputs
	private Map<String, Integer> map = new HashMap<>();
	private String[]sorted; //sorted map contents would be persisted here
	
	private boolean greater(String a, String b) {
		//successively compares two values in the map.
		return Integer.parseInt(a.split(" ")[1]) >  Integer.parseInt(b.split(" ")[1]);
	}
	
	private void swap(int i, int j) {
		String x  = sorted[i];
		sorted[i] = sorted[j];
		sorted[j] = x;
	}
	
	public void insert(String x) {
		//persists into the map, the stream of string inputs from the client. 
		map.put(x, map.getOrDefault(x, 0) + 1);
	}

	private void sort() {
		
		//inserts into the sorted[], contents in the map
		sorted = new String[map.size()];
		
		int index = 0;
		for(String s: map.keySet()) {
			sorted[index++] = s+" "+map.get(s);
		}
		
		
		for(int i = 1; i < sorted.length; i++) {
			for(int j = i; j > 0; j--) {
				
				if(greater(sorted[j], sorted[j-1]))
					swap(j, j-1); //rearranges the sorted[] elements in descending order of the map values.
		}
		
		}
		
	}
	
	public void printFrequency() {
		
		sort();//sorts the contents in the sorted[]
		
		String frq = "";
		
		for(String s : sorted)
			frq+=s+"\n";
		
		System.out.println(frq);
	}
	
	public static void main(String[] args) {
		
		Frequency frq = new Frequency();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("enter some words");
		String wrd = sc.next();
		while(! wrd.equals("-")) {
			frq.insert(wrd);
			
			wrd = sc.next();
		}
		
		sc.close();
		frq.printFrequency();
		
	}
}
