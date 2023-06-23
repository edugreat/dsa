package learning.chapter1;

import java.util.Iterator;
import java.util.Scanner;

public class Chapter1 {

	public static void main(String[] args) {
		
		Infix inf = new Infix();
		String infixed = inf.infix(args[0]);
		System.out.println(infixed);
		
		// int x = Integer.parseInt(args[0]);
		// System.out.println(x*4);

//		Parentheses p = new Parentheses();
//		String inputs= args[0];
//		String[] items = inputs.split("");
//		System.out.println("length = "+items.length);
//		for(String item:items) {
//			if(item.equals("-"))
//				break;
//			
//			p.push(item);
//		}
//		boolean perfect = p.isPerfect();
//		System.out.println(perfect== true? "Perfect": "Not perfect");
		
		
		
//		Stack<Integer> stack =new Stack<Integer>();
//		for(int i = 0; i<5; i++)
//			stack.push(i);
//
//		System.out.println("pop1 "+stack.pop());
//		System.out.println("pop2 "+stack.pop());
//		Check chk1 = new Check(2);
//		int[] x = new int[2];
//		
//		 getCheck(chk1, x);
//		
//		System.out.println("chk1 with x=2 is "+chk1.getX());
//		System.out.println(x[0]);
		// System.out.println("\nchk2 has x= "+chk2.getX());

//		int[] arr = {1,2,4,2,1,6,9,2,1,0};
//		int[] outcome = histogram(arr, 9);
//		int frequency = getElement(outcome, 4);
//		System.out.println(frequency);
//		System.out.println(factorial(5,3));
//		
//       boolean arr [][] = exercise(3);
//       System.out.println(arr[0][0]);
//	

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
	String[] arr;
	int N;

	// the index of an array
	int index;

	public Parentheses() {
		arr = new String[2];
	}

	public void push(String item) {

		increaseSize();
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == null) {
				arr[i] = item;
				N++;
				break;
			}

	}

	// method that removes the perfectly enclosing parentheses and resizes the
	// arrays
	// for instance the method would remove () from the array of [(){ and (){} from
	// the array of [(){}( and resizes the arrays to contain only [{ and [(
	// respectively.
	// Note: the arrays that are passed to this method are half the original arrays.
	// Therefore, after the filtering and reduction, the two arrays are compared to
	// to know if the original array is perfect
	public String[] compareAndResize(String[] arr) {

		for (index = arr.length - 1; index > 0; index--) {
			String item = arr[index];
			
			switch (item) {
			case "]": {
				if (arr[index - 1].equals("[")) {
					arr[index] = arr[index - 1] = null;
					arr = decreaseSize(arr);
					return compareAndResize(arr);
				}

			}
				break;

			case "}": {
				if (arr[index - 1].equals("{")) {
					arr[index] = arr[index - 1] = null;
					arr = decreaseSize(arr);

					return compareAndResize(arr);
				}
			}
				break;

			case ")": {
				if (arr[index - 1].equals("(")) {
					arr[index] = arr[index - 1] = null;
					arr = decreaseSize(arr);

					return compareAndResize(arr);
				}
			}

			}
		}

		return arr;

	}

	// method that divides arrays into two equal parts and test the perfectness of
	// the arrays
	public boolean isPerfect() {

		boolean perfect = true;
		compact(); // removes possible initial null items in our 'arr'instance variable

		if (arr.length % 2 != 0) {
			return perfect = false;
		}

		else if (arr.length == 2) {
			perfect = switch (arr[0]) {
			case "(": {

				yield arr[1].equals(")") ? true : false;
			}
			case "{": {
				yield arr[1].equals("}") ? true : false;
			}

			case "[": {
				yield arr[1].equals("]") ? true : false;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + arr[0]);

			};
		} else {

			String[] firstHalf = new String[arr.length / 2];
			String[] secondHalf = new String[arr.length / 2];
			for (int i = 0; i < secondHalf.length; i++) {
				firstHalf[i] = arr[i];
				secondHalf[i] = arr[secondHalf.length + i];
			}

			firstHalf = compareAndResize(firstHalf);
			secondHalf = compareAndResize(secondHalf);

			for (int i = 0; i < firstHalf.length; i++) {
				String item1 = firstHalf[i];
				String item2 = secondHalf[secondHalf.length - 1 - i];
				if (item1.equals("(")) {
					if (!(item2.equals(")"))) {
						perfect = false;
						break;
					}
				}

				if (item1.equals("[")) {
					if (!(item2.equals("]"))) {
						perfect = false;
						break;
					}
				}

				if (item1.equals("{")) {
					if (!(item2.equals("}"))) {
						perfect = false;
						break;
					}
				}
			}
		}
		return perfect;

	}

	private void increaseSize() {
		String[] newArr;
		if (N == arr.length) {
			newArr = new String[arr.length * 2];
			for (int i = 0; i < arr.length; i++)
				newArr[i] = arr[i];

			arr = newArr;
		}
	}

	public String[] decreaseSize(String[] arr) {

		int ValidIndex = -1;
		String[] temp = new String[arr.length - 2];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				temp[++ValidIndex] = arr[i];
			}
		}

		return temp;
	}

	
	/*
	 * Due to the fact that our 'arr' is a dynamically resizeable one with the
	 * effect being the pre-population of items with null values, we need a method
	 * that can return a new array of the same items in the 'arr' except the null
	 * values
	 */
	public void compact() {

		// check if our 'arr' instance has at least a null item by testing the last
		// element of the array.
		if (arr[arr.length - 1] == null) {
			// get the length of our 'arr' instance variable
			int length = arr.length;

			// keep track of the number of null items
			int sumOfNulls = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == null)
					++sumOfNulls;

			}
			// create new array of length of length-sumOfNulls
			String[] newArr = new String[length - sumOfNulls];
			for (int k = 0; k < newArr.length; k++) {
				newArr[k] = arr[k];
			}
			arr = newArr;

		}
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
	private boolean isEmpty() {
		
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
		
		for(int i = 0; i< lastClosingParentheses; i++)
			infix+=")";
		
		return infix;
	}
}
