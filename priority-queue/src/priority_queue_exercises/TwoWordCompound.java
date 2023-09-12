/*
 * Write a program that reads list of words from standard input and prints all two-word compound words
 * in the list. For example, if 'after', 'thought', and 'afterthought' are in the list, then 'afterthought' is a compound word
 */
package priority_queue_exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TwoWordCompound {
	
	//array that holds the string inputs read from the standard input
	private String[] words;
	
	//keeps counts on the number of words read
	private int index;
	public TwoWordCompound() {
		words = new String[10];//initialize the array to a size of 10 by default
	}
	
	//resizes the array to enable dynamic feature
	private void resize(int sz) {
		String[] temp = new String[sz];
		
		for(int i = 0; i < index; i++) {
			temp[i] = words[i];
		}
		
		words = temp;
	}
	
	//asserts that two concatenated words is same as another word
	private boolean same(String x, String y, String z) {
		
		return 
				x.concat(y).compareTo(z) == 0 ||
				y.concat(x).compareTo(z) == 0;
	}

	//reads stream of words and saves to the words[]
	public void readWords(String s) {
		
		if(words.length - index == 1)
			resize(index*2);
		
		words[index++] = s;
	}
	
	//method that gets the two-word compounds and saves them to list
	private List<String>getsCompounds(){
		List<String> cmp = new ArrayList<>();
		
		for(int i = 0; i < index; i++) {
			for(int j = i+1; j < index; j++) {
				for(int k = 0; k < index; k++) {
					if(same(words[i], words[j], words[k]))
						cmp.add(words[k]);
				}
			}
		}
		
		return cmp;
	}
	
	//prints two-word compound words
	public String printsTwoWordsCompound() {
		String twoWords ="";
		
		List<String> cmp = getsCompounds();
		for(String s:cmp)
			twoWords+=s+" ";
		
		return twoWords;
	}
	
	public static void main(String[] args) {
		TwoWordCompound twc = new TwoWordCompound();
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter some words\n");
		String input = sc.next();
		while(! input.equals("-")) {
			twc.readWords(input);
			input = sc.next();
		}
		sc.close();
		String cmp = twc.printsTwoWordsCompound();
		System.out.println(cmp);
	}
}
