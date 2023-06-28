package section13;

import java.util.Iterator;

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
			arr = new String[2];// initialize with capacity of 2
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
	 * Write an program that takes from the standard input an expression without
	 * left parentheses and prints the equivalent infix expression with the
	 * parentheses inserted. For example, given the input: 1+2)*3-4)*5-6))) your
	 * program should print ((1+2)*((3-4)*(5-6)))
	 */
	class Infix {

		public String infix(String expression) {
			// split the expression to extract the inner contents
			String[] contents = expression.split("\\)");

			// To get the number of closing brackets at the end of the expression,
			// perform: expression.length-(sum of characters in 'contents' +
			// contents.length)
			// Note: the length of the 'contents' array gives the number of closing brackets
			// each sub expression
			int contentCounts = 0;
			for (String s : contents)
				contentCounts += s.length();

			int lastClosingParentheses = expression.length() - (contentCounts + contents.length);

			// keeps a track of the number of closing parentheses
			int totalClosingParentheses = 0;

			// split the expression so we can count the number of total closing parentheses
			String[] expr = expression.split("");

			// counts the number of total closing parentheses
			for (String exp : expr)
				if (exp.equals(")"))
					totalClosingParentheses++;

			// affix ')' after each sub expression
			for (int i = 0; i < contents.length; i++)
				contents[i] += ")";

			// System.out.println("total closing = "+totalClosingParentheses);

			// begin to affix the opening parentheses
			for (int i = 0; i <= totalClosingParentheses;)
				for (int k = 0; k < contents.length; k++) {
					++i;
					if (i > totalClosingParentheses)
						break;

					if (k == 0) // {
						contents[k] = "(" + contents[k];
					// System.out.println("k = "+k+", i="+i+", item= "+contents[k]);
					// }

					else // {
						contents[k] = (contents[k].charAt(0)) + "(" + contents[k].substring(1, contents[k].length());
					// System.out.println("k = "+k+", i="+i+", item= "+contents[k]);
					// }

				}

			// finally, concatenate the elements in 'contents' and assign it to variable
			String infix = "";
			for (String s : contents)
				infix += s;

			for (int i = 0; i < lastClosingParentheses; i++)
				infix += ")";

			return infix;
		}
	}
}

//Write an iterable stack client that has a static method copy() that takes
// a stack of strings as argument and returns a copy the stack.
class Stack<Item> implements Iterable<Item> {

	// an instance veriable
	private Node first;

	// size of the stack
	private int size;

	private class Node {

		private Node next;
		private Item item;
	}

	// check if stack is empty
	private boolean isEmpty() {

		return size == 0;
	}

	// push something into to the stack
	public void push(Item item) {

		// create a new Node
		Node newNode = new Node();
		newNode.item = item;
		newNode.next = first;
		first = newNode;
		++size;
	}

	// pop item out of the stack,beginning with last item added
	public Item pop() {
		// first get the popped off item
		Item item = first.item;

		// migrate the next node to the top of the stack
		first = first.next;

		// return the item just popped off
		return item;
	}

	@Override
	public Iterator<Item> iterator() {

		return new StackIterator();
	}

	// implement Iterator interface
	private class StackIterator implements Iterator<Item> {
		private Node currentNode = first;

		@Override
		public boolean hasNext() {

			return !isEmpty();
		}

		@Override
		public Item next() {

			Item item = currentNode.item;
			currentNode = currentNode.next;

			// decrement the stack size
			size--;

			return item;
		}

	}

	// method that accepts a stack and returns a copy of it
	public static Stack<String> copy(Stack<String> stack) {

		Stack<String> copied = new Stack<String>();
		int counter = 0;
		String[] items = new String[stack.size];
		Iterator<String> iterator = stack.iterator();
		while (iterator.hasNext())
			items[counter++] = iterator.next();

		for (int i = items.length - 1; i >= 0; i--)
			copied.push(items[i]);

		return copied;
	}

}

// Develop a class ResizingArrayQueueOfStrings that implements the queue abstraction with a fixed-size array,
// extend your implementation to use array resizing to remove the size restriction.
class ResizingArrayQueueOfStrings implements Iterable<String> {

	// tracks the number of items added to the stack
	private int counter;

	// array index
	int index;

	String[] queue;

	public ResizingArrayQueueOfStrings() {
		queue = new String[4];
	}

	private boolean isEmpty() {
		return counter == 0;
	}

	public void push(String item) {
		resize(queue.length); // possible resizing of the stack
		queue[index++] = item;
		++counter;

	}

	// removes items in the queue starting with the least added
	public String pop() {
		String item = queue[0];

		// for easy garbage collection
		queue[0] = null;

		// one item has just been removed from queue
		--counter;
		shiftItem(queue);// shift items towards left

		return item;

	}

	// method that makes the stack's size resizable
	private void resize(int size) {
		int index = 0;
		// checks if we've filled the queue array
		if (queue.length == counter) {
			String[] tempStack = new String[size * 2]; // create new String double the size of the queue

			// populate the array with the items initially in the queue
			for (String item : queue)
				tempStack[index++] = item;

			queue = tempStack;

		}
	}

	// method that shifts the position of the items in the queue towards left after
	// each item removal
	private void shiftItem(String[] queue) {
		int i = 0;
		String[] temp = new String[queue.length - 1]; // decrease the array by one since we've remove an item from our
														// queue
		for (int j = 0; j < queue.length; j++) {
			if (j != 0) // do not add the item at the zero position, since we just removed from there
						// and have made it null
				temp[i++] = queue[j];
		}
		index--; // pointer to the last index in the queue has
		this.queue = temp;
	}

	@Override
	public Iterator<String> iterator() {
		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator<String> {

		@Override
		public boolean hasNext() {

			return !isEmpty();
		}

		@Override
		public String next() {
			String item = pop();

			return item;
		}
	}
}

/*
 * Write a Queue client that takes a command-line argument k and prints the kth
 * from the last string found on standard input (assuming that standard input
 * has k or more strings)
 */
class Queue<Item> {
	private Node first;
	private Node last;
	private int size;

	private class Node {
		private Node next;
		private Item item;
	}

	// checks if the array is empty or not
	private boolean isEmpty() {
		return size == 0;
	}

	// push an item to the Queue
	public void enqueue(Item item) {
		Node initialLast = last;
		last = new Node();
		last.item = item;
		last.next = null;

		if (first == null)
			first = last;
		else
			initialLast.next = last;
		size++;
	}

	// removes item from the Queue
	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		size--;

		return item;
	}

	// method that takes k and prints the kth element from the last string in the
	// queue
	public Item printKthItem(int k) {
		if (size >= k && k != 0) {
			int fromBeginning = size - k; // position of the item from the beginning of the Queue(zero based index)

			Node node = first;

			for (int i = 0; i < fromBeginning; i++)
				node = node.next; // traverse the Queue

			return node.item;
		}

		return null; // if the specified position is greater than the size of the Queue or is zero
	}

}

/**
 * Write a method delete() that takes an int argument k and deletes the kth
 * element in a linked list, if it exists.
 */
//linked-list implementation
class LinkedList<Item> implements Iterable<Item> {
	private Node first;// first Node in a list
	private Node last; // last Node in the Queue
	private int size; // number of items in the list
	private Node node; // this is used to implement forEach functionality in the next()

	private class Node {
		private Node next;
		private Item item;
	}

	// verifies if the list is empty
	private boolean isEmpty() {
		return size == 0;
	}

	// add item to the linked-list
	public void add(Item item) {

		Node oldLast = last;

		last = new Node();
		last.item = item;
		last.next = null;

		if (isEmpty())
			node = first = last;
		else
			oldLast.next = last;
		size++;

	}

	// each recursive call to this method progressively returns the items in the
	// linked list starting from the beginning of the list.
	private Item get() {

		Item item = null;
		if (node != null) {
			item = node.item;
			node = node.next;

		}

		return item;
	}

	@Override
	public Iterator<Item> iterator() {

		return new ListIterator();
	}

	// iterator implementation
	class ListIterator implements Iterator<Item> {

		@Override
		public boolean hasNext() {

			return node != null;
		}

		@Override
		public Item next() {

			return get();

		}
	}

	// return item at the given position
	public Item get(int position) {

		if (position == 1)
			return first.item;

		// ensure the list has item at such position
		if (size > position && position > 1) {
			Node node = first;
			for (int i = 2; i <= position; i++)// zero based index iteration
				node = node.next;

			return node.item;
		}
		return null; // if the position provided does not exist
	}

	// method that deletes and returns the deleted item from the list based on index
	// position
	@SuppressWarnings("unchecked")
	public Item delete(int position) {

		Item item = null; // item to return after deletion

		// ensure the list has item at such position
		if (size >= position && position > 0) {

			// we need to temporarily save away all the items before the item to delete
			Item[] bucket = (Item[]) new Object[position - 1];

			// check if the has already been deleted
			for (int i = 0; i <= position - 1; i++)
				if (i < position - 1) // save the items from the beginning of the list but not including the item we
										// intend to delete
				{
					bucket[i] = first.item;
					first = first.next;
					size--;
				} else { // this is where the intended item is removed and never cached
					item = first.item; // the deleted item

					first = first.next;

					size--;

				}

			// refill the list with the cached items
			for (int i = bucket.length - 1; i >= 0; i--) {
				Node tempNode = new Node();
				tempNode.item = bucket[i];
				tempNode.next = first;
				first = tempNode;
				size++;
			}

			this.node = first;

		}

		return item; // return the deleted item or null, if nothing to delete
	}

	// write a method find() that takes a linked list and a string 'key'
	// and returns true if some node in the list has 'key' as as its item field,
	// false otherwise.
	public boolean find(LinkedList<Item> list, String key) {
		boolean found = false;

		Node node = list.first;
		int iteration = 0;
		System.out.println("size = " + size);
		while (!found && ++iteration <= size) {
			if (String.valueOf(node.item).toUpperCase().equalsIgnoreCase(key))// test if the item is in this Node
				found = true;
			else
				node = node.next; // move to the next Node and continue testing
		}

		return found;
	}
   
	/*
	 * Write a method removeAfter() that takes a linked-list Node as argument and removes the node
	 * following the given one (and does nothing if the argument or the next field in the argument node is null)
	 */
	@SuppressWarnings("unchecked")
	public void removeAfter(Node node) {
		
		if(node != null) {
			
			int index = 0;
		
			//array to temporarily cache the Node items as we traverse through the list
			Item[] items = (Item[]) new Object[size-1];
			while(true) {
				if(first == node) {
					first = first.next; //thereby removing the current Node
				     break;
				}else {
					items[index++] = first.item; //cache the item
					first = first.next;
				}
			}
			//refill the list
			for(int i = items.length-1; i >= 0; i--) {
				if(items[i] != null) {
				Node N = new Node();
				N.item = items[i];
				N.next = first;
				first = N;
				}
				
				
			}
			
			this.node = first; // so as not to break the forEach Iterable implementation.
		}
		
		
	}
	//method that returns the Node after the specified position in the list
	// this method can be called in removeAfter(Node) method to supply the Node to delete
	public Node getNodeAfterPos(int position){
		
		if(position >= 1 && position < size) {
			Node N = first;
			for(int i = 1; i <= position; ++i)
			    N = N.next;
			
			
			return N;
		}
		
	   return null;
	}
	
}
