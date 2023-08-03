package exercises;

/*
 * Write a code fragment that creates an N-by-N boolean array a[][] such that
 * a[i][j] is true if i and j have no common factors and false otherwise
 */

public class ConditionalCommonFactorMatrix {

	public static void main(String[] args) {
		boolean [][] b = commonFactorChecks(10);
		System.out.println(b[2][0]);
		

	}
	
	private static boolean[][] commonFactorChecks(int length){
		boolean[][] matrix = new boolean[length][length];
		
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < length; j++) {
				if(i == 0 || j == 0) {}//false by default
				else if(i % j != 0 || j % i != 0)
                 matrix[i][j] = true;
			}
		}
		return matrix;
	}

}
