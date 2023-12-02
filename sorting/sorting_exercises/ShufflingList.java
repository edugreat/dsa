package sorting_exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Shuffling a list.Develop and implement a divide-and-conquer algorithm that randomly shuffles
 * a linked list in linearithmic time and logarithmic extra space
 */
public class ShufflingList {

	public static void main(String[] args) {
		
	}

	public static <T extends Comparable<T>> void shuffle(List<T> list) {
		
		//initial terminating check
		if(list.size() < 2) return;
		
		int size = list.size();
		int middle = size/2;
		
		List<T> leftHalf = new ArrayList<>(list.subList(0, middle));
		List<T> rightHalf = new ArrayList<>(list.subList(middle, size));
		
		//recursive calls
		shuffle(leftHalf);
		shuffle(rightHalf);
		shuffling(list, leftHalf, rightHalf);
		
		
	}
	
	private static <T extends Comparable<T>> void shuffling(List<T> list, List<T> leftHalf, List<T> rightHalf) {
		
		int listSize = list.size();
		//get some unique random numbers. Each of the random numbers must be less than the size of the list
		Random rand = new Random();
		Integer[] randomArr = new Integer[listSize];
		
		for(int i = 0; i < listSize;) {
			int r = rand.nextInt(listSize);
			if(!contains(randomArr, r)) //functionality that achieves a Set type of collection
				randomArr[i++] = r;
		}
		
		int x = 0, y = 0, z =0;
		
		while(true) {
			if(x < leftHalf.size())
				list.set(randomArr[z++], leftHalf.get(x++));
			
			if(y < rightHalf.size())
				list.set(randomArr[z++], rightHalf.get(y++));
			
			if(x >= leftHalf.size() && y >= rightHalf.size()) break;
			
		}
		
	}
	//utility method to achieve a Set type of collection
	private static boolean contains(Integer[]a, Integer i) {

		for(Integer x: a)
			if(x == i)
				return true;
		
		return false;
	}
}
