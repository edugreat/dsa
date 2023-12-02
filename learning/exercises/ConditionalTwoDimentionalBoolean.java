package exercises;


/*
 * Write a code fragment that prints the contents of a two-dimensional boolean
 * array, using * to represent true and a space to represent false. Include row
 * and column numbers
 */

public class ConditionalTwoDimentionalBoolean {

	public static void main(String[] args) {
//		boolean [][] a = {{true, false},{true, true}};
//		System.out.println(conditionalPrint(a));

	}
	private static String conditionalPrint(boolean[][]a) {
		String output = "";
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				if(a[i][j])
					output+="[ * ]"+"("+i+","+j+")";
				else
					output+="[   ]"+"("+i+","+j+")";
				if(a.length - j == 1)
					output+="\n";
			}
		}
		
		return output;
	}

}
