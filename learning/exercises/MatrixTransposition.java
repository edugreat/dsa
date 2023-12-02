package exercises;


/*
 * Write a code fragment that prints the transposition (rows and columns
 * changes) of a two-dimensional array with M rows and N columns
 * 
 */

public class MatrixTransposition {

	public static void main(String[] args) {
		
	}
	private static String transpose(int[][]a){
		
		int row = a[0].length;
		int column = a[1].length;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++)
				if(i != j && i < j) {
					int temp = a[i][j];
					//System.out.println("replacing "+temp+" at index "+i+" "+j+" with "+a[j][i]+" "+" at index "+j+" "+i);
					a[i][j] = a[j][i];
					a[j][i] = temp;
				}
				
		}
		
		return printArray(a);
		
	}
	//helper method that prints the content of an array matrix
	private static String printArray(int[][]a) {
		String matrix = "";
		int row  = a[0].length;
		int column = a[1].length;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				
				matrix = matrix+a[i][j]+" ";
				if(column - j == 1 )//here we have reached the end of the current row
					matrix = matrix+"\n";
			}
		}
		return matrix;
	}

}
