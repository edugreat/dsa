package section13;

public class Exercise {

	public static void main(String[] args) {
		

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
			arr = new String[2];//initialize with capacity of 2
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
}
