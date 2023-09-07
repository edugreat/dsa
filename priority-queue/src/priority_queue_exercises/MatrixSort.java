
package priority_queue_exercises;

/*
 * Implement a data type that accepts a generic matrix ( generic multi-dimensional array) and sort it
 */

public class MatrixSort<Item extends Comparable<Item>> {

	private Item[][] items;
	public MatrixSort(Item[][] items) {
		
		this.items = items;
	}
	
	//asserts that an item is less than the other
	private boolean less(Item a, Item b) {
		
		return a.compareTo(b) < 0;
	}
	
	//swaps the ith item with the jth item
	private void swap(int i, int j, int k, int l) {
		
		Item it = items[i][j];
		items[i][j] = items[k][l];
		items[k][l] = it;
	}
	
	//sorts the row
	private void sortRow() {
		
		for(int i = 0; i < items.length; i++) {
			
			for(int j = 1; j < items[i].length; j++) {
				
				for(int k = j; k > 0; k--) {
					if(less(items[i][k], items[i][k-1]))
						swap(i, k, i, k-1);
				}
			}
		}
	}
	
	private void sortColumn() {
		
		for(int i = 1; i < items.length; i++) {
			
			for(int j = 0; j < items[i].length; j++) {
				
				for(int k = i; k > 0;  k--) {
					
					if(less(items[k][j], items[k-1][j]))
						swap(k, j, k-1, j);
				}
			}
		}
	}
	
	private void sort() {
		sortRow();
		sortColumn();
	}
	
	@Override
	public String toString() {
		
		sort();
		String matr = "";
		
		for(int i = 0; i < items.length; i++) {
			
			for(int j = 0; j < items[i].length; j++)
				matr+= (items[i].length - j == 1 ? items[i][j]+"\n" : items[i][j]+" ");
		}
		
		return matr;
		
	}
	
	public static void main(String[] args) {
		
		Integer[][]items = {{2, 1, 7, 0},{8, 1, 9, 1}, {6, 1, 8, 0}, {1, 5, 2, 9}};
		MatrixSort<Integer> m = new MatrixSort<>(items);
		
		System.out.println(m);
	}
}
