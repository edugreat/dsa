package searching;
/*
 * Develop a symbol-table implementation 'OrderedSequentialSearchST' that uses an ordered linked list as the underlying data structure to implement our ordered symbol-table API.
 */
public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {

	private Node top;//reference to the top node
	private Node first;//reference to the node first none(top) as in a queue-type implementation
	private Node last;//reference to the last node(as in a queue-type implementation
	
	private Node maximum;//reference to the node containing the maximum key in a sorted scenario
	private int N;//reference to the size of the key-value pair ST
	
	
	private class Node{
		Key key;
		Value value;
		Node next;
		public Node(Key key, Value val, Node next) {
			
			this.key = key;
			this.value = val;
			this.next = next;
		}
	}
	
	private boolean isEmpty() {
		
		return N == 0;
	}
	
	public int size() {
		
		return N;
	}
	
	//the rank of a key is the number of keys less than or equal to the key
	//this also can the be the actual position of the key in a sorted scenario
	private int rank(Key key) {
		int i = 0;
		for(Node node = top; node != null; node = node.next) {
			if(key.compareTo(node.key) > 0)
				i++;
		}
		
		return i;
	}
	//the isSame() method checks if two keys are equal in all sense
	private boolean isSame(Key k1, Key k2) {
		return k1.compareTo(k2) == 0;
	}
	
	//the get() method returns the value associated with the given key
	public Value get(Key key) {
		if(isEmpty())return null;
			
		
		if(key == null)return null;
		
		for(Node node = top;  node!=null; node = node.next) {
			if(isSame(key, node.key)) return node.value;
		}
		
		return null; //the keys does not exist
	}
	
	//the position() determines the position of a key using its rank
	//it form a queue of nodes from the beginning of the ST to the rank.
	//this queue is later attached to the original ST after key whose rank(or position) is 'rank' has been attached to the ST
	private void position(int rank) {
		
		int i = 0;
		while(++i <= rank) {
			
			//form a queue of all keys from the beginning of the ST to the 'rank'
			if(first == null) {
				first = new Node(top.key, top.value, null);
				last = first;
				top = top.next;
				continue;
			}
			
			Node node = new Node(top.key, top.value, null);
			last.next = node;
			last = node;
			top = top.next;
		}
	}
	
	//the put() method associates a value with a given key
	//if the ST is initially empty, it attaches the key-value and terminates after incrementing the size counter
	//if the key is null, it terminated early.
	//if the key already exists in the ST,it updates the value associated with the key and terminates.
	//Finally, it uses the rank of the key to determine its position and rightly attaches the key-value pair to its position
	public void put(Key key, Value val) {
		
		if(key == null)return;
		
		if(isEmpty()) {
			top = new Node(key, val, null);
			maximum = top;
			N++;
			return;
		}
		
		
		for(Node node = top; node != null; node = node.next) {
			if(isSame(key, node.key)) {
				node.value = val;
				N++;
				return;
			}
		}
		
		int r = rank(key);
		//if the incoming key is no greater than any of the existing keys,
		//then its position is at the front of the ST
		if(r == 0) {
			Node node = new Node(key, val, null);
			node.next = top;
			top = node;
			N++;
			return;
		}
		
				
		//the position of the key is in-between the first and last node of the ST
		position(r);//splits the ST into two(from the rank to beginning and from the rank to the end of the ST)
		Node node = new Node(key, val, null);//creates new node with the new key-value pair
		node.next = top;//attaches the new key-value of node to the right side of the ST resulted from the call to position()
		last.next = node;//attaches the remaining left side of the ST the newly added key-value pair
		
		top = first;//returns the ST to a queue based implementation
		N++;
		first = null;//sets the node to its default for next possible operation
	
		//updates the maximum node
		if(key.compareTo(maximum.key) > 0)
			maximum = node;

	}
	//the delete method deletes a key from the ST. The method
	//simply associates the given key with a null value
	public void delete(Key key) {
		
		for(Node node = top; node != null; node = node.next) {
			if(isSame(key, node.key)) {
				node.value = null;
				N--;
				break; //breaks out of the loop in time
			}
		}
		
		//if the key just deleted is the maximum key, computes the next maximum
		if(isSame(key, maximum.key))
			nextMax();
	}
	
	//the contains method checks if there is any such associated with a non null value
	public boolean contains(Key key) {
		
		for(Node node = top; node != null; node = node.next) {
			if(isSame(key, node.key))
				return node.value != null;
		}
		
		return false;
	}
	
	//return the minimum key
	public Key min() {
		return top.key;
	}
	
	//returns the maximum key
	public Key max() {
		
		return maximum.key;
	}
	
	//private method that computes the next maximum key after current maximum key is being deleted
	private void nextMax() {
		
		for(Node node = top; node != null; node = node.next) {
			if(node.value != null) maximum = node;//iterates through a sorted ST, assigning the maximum to each node until the last node whose value is not null
		}
		
		
	}
	public static void main(String[] args) {
		OrderedSequentialSearchST<Integer, String> ST = new OrderedSequentialSearchST<>();
		Integer[] keys = {10,4,6,0,1,7,11};
		String[] vals = {"Ten","Four","Six","Zero","One","Seven","Eleven"};
		for(int i = 0; i<keys.length; i++) {
			
			ST.put(keys[i], vals[i]);
		}

		System.out.println(ST.contains(0));
		ST.delete(0);
		System.out.println(ST.contains(0));
		System.out.println(ST.contains(6));
		System.out.println(ST.contains(11));
		System.out.println("minimum key = "+ST.min());
		System.out.println("maximum key = "+ST.max());
		ST.delete(11);
		System.out.println("maximum key = "+ST.max());
		ST.delete(10);
		System.out.println("maximum key = "+ST.max());
		//System.out.println(ST.get(11));
		
		
	}
}
