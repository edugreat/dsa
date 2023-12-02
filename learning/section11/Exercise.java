package section11;

public class Exercise {

	public static void main(String[] args) {
	
		

	}
	
	/*
	 * Write a code that outputs the binary representation
	 * of a positive integer N into a string s.
	 * 
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

	// implementation of the method above using for loop
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

		
		
		
		
		
		
		
		
         // a convenient method to output the contents of a multi-dimensional array
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

}
