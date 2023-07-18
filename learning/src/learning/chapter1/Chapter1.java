package learning.chapter1;

import java.util.Iterator;
import java.util.Scanner;

import javax.naming.ldap.SortControl;

public class Chapter1 {
	public static int count(int[] a) {
		int N = a.length;
		int count = 0;
		for(int i = 0; i< N; i++)
			for(int j = i+1; j< N; j++)
				for(int k = j+1; k<N; k++)
					if(a[i]+a[j]+a[k] == 0);
						count++;
		return count;
	}

	public static void main(String[] args) {
//		System.out.println("length = "+args.length);
//		int []a = new int[args.length];
//		for(int i = 0; i< a.length; i++)
//			a[i] = Integer.parseInt(args[i]);
//		long then = System.currentTimeMillis();
//		int count = count(a);
//		long now = System.currentTimeMillis();
//		System.out.println(count+", elapsed = "+(now-then)/1000.0);
		
	
	}

	

	/*
	 * Method that does the array multiplications
	 */
	public static int[][] multiplyArrays(int[][] x, int[][] y) {

		int[][] result = new int[x.length][x.length];

		for (int i = 0; i < x.length; i++) {

			for (int j = 0; j < x.length; j++) {

				for (int k = 0; k < x.length; k++) {

					result[i][j] += (x[i][k]) * (y[k][j]);
				}
			}
		}

		return result;

	}

	/*
	 * Method that returns the binary of positive integers
	 */
	public static String binaryOfPositiveInts(int num) {

		if (!(num < 0)) {
			String binary = "";
			while (num > 0) {
				binary = (num % 2) + binary;
				num = num / 2;
			}
			return (num == 0) ? "0" : binary;
		}

		return String.format("Value %s, not supported", num);
	}

	// This version uses the for loop
	public static String binaryOfPositiveInts1(int num) {
		int temp = num;
		if (!(num < 0)) {
			String binary = "";
			for (int i = num; num > 0; num = num / 2) {
				binary = (num % 2) + binary;
			}
			return (temp == 0) ? "0" : binary;
		}
		return String.format("Value %s, not supported", num);

	}

	/*
	 * Write a code fragment that prints the contents of a two-dimensional boolean
	 * array, using * to represent true and a space to represent false. Include row
	 * and column numbers
	 */
	public static String twoDimensionalPrints(boolean[][] arr) {
		String output = "";
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == true)
					output += String.format("* %5s\n", i + " " + j);
				else {
					output += String.format(" %6s\n", i + " " + j);
				}
			}
		}
		return output;
	}

	/*
	 * Write a code fragment that prints the transposition (rows and columns
	 * changes) of a two-dimensional array with M rows and N columns
	 * 
	 */
	public static String twoDimensionalTranspose(int oldArr[][]) {

		// get the row dimension of oldArr, which is the array length
		int row = oldArr.length;

		// get the column dimension of oldArr, which is the length of individual array
		int column = oldArr[0].length;

		// after transformation, the new array assumes
		// a row = column of old array, and a column = row of old array
		int[][] newArr = new int[column][row];

		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				// swap
				newArr[i][j] = oldArr[j][i];

			}
		}

		return printArray(newArr);

	}
	// method that prints the content of an arrays

	public static String printArray(int[][] arr) {
		String newLine = "";
		String content = "";
		for (int i = 0; i < arr.length; i++) {
			content += newLine;
			for (int j = 0; j < arr[0].length; j++) {
				content += String.format("%s   ", arr[i][j]);
			}
			newLine = "\n";
		}

		return content;
	}

	/*
	 * Write a static method histogram() that takes an array a[] of int values and
	 * an integer M as argument and return an array of length M whose ith entry is
	 * the number of times th integer i appeared in the argument. If the values in
	 * a[] are all between 0 and M-1, the sum of the values in the returned array
	 * should be equal to a.length
	 */
	public static int[] histogram(int[] a, int M) {

		int[] res = new int[M];

		for (int i = 0; i < a.length; i++) {
			int item = a[i];

			// check if we have an element in res with such index
			if (item < M && res[item] == 0) {

				for (int j = 0; j < a.length; j++) {
					if (a[j] == item)
						res[item] += 1; // increment the frequency of the index
				}

			}
		}

		return res;
	}

	// methods that prints array elemnts by their index
	public static int getElement(int[] arr, int item) {
		int element = 0;

		try {
			element = arr[item];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("More than array size");
			return -1;
		}

		return element;
	}

	public static double factorial(int N, int K) {

		double numerator = 1;
		K = N - K;
		double denominator = 1;
		while (N > 0) {
			numerator *= N;
			N--;
		}
		while (K > 0) {
			denominator *= K--;

		}
		return numerator / denominator;

	}

	/*
	 * Write a code fragment that creates an N-by-N boolean array a[][] such that
	 * a[i][j] is true if i and j have no common factors and false otherwise
	 */
	public static boolean[][] exercise(int length) {

		boolean[][] arr = new boolean[length][length];

		for (int i = 0; i < arr.length; i++) {

			for (int j = 0; j < arr.length; j++) {

				if ((i != j) && ((i == 1 || i == 0) || (j == 1 || j == 0))) {

					arr[i][j] = true;

				} else {
					for (int k = 2; k <= j; k++) {
						if ((i % k == 0) && (j % k == 0)) {
							arr[i][j] = false;
							break;
						}
					}

				}

			}

		}

		return arr;
	}

	public static void getCheck(Check chk, int[] x) {

		chk.setX(chk.getX() + 25);
		x[0] = 26;

	}

}

class Check {
	int x;

	public Check(int x) {
		this.x = x;

	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}
}

/*
 * Write a client Parentheses that reads in a text stream from the standard
 * input and uses a stack to determine whether its parentheses are properly
 * balanced. For example, your program should print true for[()]{}{[()()]()} and
 * false for [(])
 * 
 */
class Parentheses {
	private static final char LEFT_PAREN = '(';
	private static final char LEFT_BRACKET = '[';
	private static final char LEFT_BRACE = '{';
	
	private static final char RIGHT_PAREN = ')';
	private static final char RIGHT_BRACKET = ']';
	private static final char RIGHT_BRACE = '}';
	
    public static boolean isPerfect(String s) {
    	Stack<Character> stack = new Stack<Character>();
    	for(int i = 0; i< s.length(); i++) {
    		if(s.charAt(i) == LEFT_PAREN)
    			stack.push(LEFT_PAREN);
    		if(s.charAt(i) == LEFT_BRACKET)
    			stack.push(LEFT_BRACKET);
    		if(s.charAt(i) == LEFT_BRACE)
    			stack.push(LEFT_BRACE);
    		
    		if(s.charAt(i) == RIGHT_PAREN) {
    			if(stack.isEmpty()) return false;
    			if(stack.pop() != LEFT_PAREN) return false;
    			
    		}else if(s.charAt(i) == RIGHT_BRACKET) {
    			if(stack.isEmpty()) return false;
    			if(stack.pop() != LEFT_BRACKET) return false;
    		}else if(s.charAt(i) == RIGHT_BRACE) {
    			if(stack.isEmpty()) return false;
    			if(stack.pop() != LEFT_BRACE) return false;
    			
    		}
    	}
    	
    	return stack.isEmpty();
    }
	
}

// Stack implementation
class Stack<Item> implements Iterable<Item>{
	private Node first;
	private int N;
	
	private class Node{
		public Node next;
		public Item item;
	}
	
	public void push(Item item) {
		Node old = first;
		first = new Node();
		first.item = item;
		first.next = old;
		N++;
	}
	
	public Item pop() {
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	public boolean isEmpty() {
		
		return (first ==null);
	}
	
	// Returns the newly added item item without popping the item
	public Item peek() {
		Item item =first.item;
		
		return item;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new StackIterator();
	}
	
	// The iterator class implementation
	private class StackIterator implements Iterator<Item>{
		
	

		@Override
		public boolean hasNext() {
			
			return !isEmpty();
		}

		@Override
		public Item next() {
		
			Item item = first.item;
			first = first.next;
			N--;
			
			return item;
		}
	}
}
/* 
 * Write an program that takes from the standard input an expression without left parentheses and prints the equivalent
 * infix expression with the parentheses inserted. For example, given the input:
 * 1+2)*3-4)*5-6))) your program should print ((1+2)*((3-4)*(5-6)))
 * */
class Infix{
	
	public String infix(String expression) {
		//split the expression to extract the inner contents
		String[] contents = expression.split("\\)");
		
		//To get the number of closing brackets at the end of the expression,
		// perform: expression.length-(sum of characters in 'contents' + contents.length)
		//Note: the length of the 'contents' array gives the number of closing brackets each sub expression
		int contentCounts = 0;
		for(String s: contents)
			contentCounts+=s.length();
		
		int lastClosingParentheses = expression.length()-(contentCounts+contents.length);
		
		//keeps a track of the number of closing parentheses
		int totalClosingParentheses =0;
		
		//split the expression so we can count the number of total closing parentheses
		String[] expr = expression.split("");
		
		//counts the number of total closing parentheses
		for(String exp: expr) 
			if(exp.equals(")"))
				totalClosingParentheses++;
		
		//affix ')' after each sub expression
		for(int i =0; i< contents.length; i++)
			contents[i]+=")";
			
	//	System.out.println("total closing = "+totalClosingParentheses);
		
		//begin to affix the opening parentheses
		for(int i = 0; i <= totalClosingParentheses; )
			for(int k = 0; k < contents.length; k++) {
				++i;
				if(i > totalClosingParentheses)
					break;
				
				if(k == 0) //{
					contents[k] = "("+contents[k];
					//System.out.println("k = "+k+", i="+i+", item= "+contents[k]);
				//}
					
		
				else //{
					contents[k] = (contents[k].charAt(0)) + "("+ contents[k].substring(1, contents[k].length());
					//System.out.println("k = "+k+", i="+i+", item= "+contents[k]);
				//}
					
				
				
			}
				
				
		//finally, concatenate the elements in 'contents' and assign it to variable
		String infix = "";
		for(String s: contents)
			infix+=s;
		
		for(int i = 0; i < lastClosingParentheses; i++)
			infix+=")";
		
		return infix;
	}
	
}
