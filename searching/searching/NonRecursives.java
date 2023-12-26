package searching;

//give non recursive implementations of min(), max(),floor(),ceiling(), select() and rank()
public class NonRecursives<Key extends Comparable<Key>, Value> {
	
	private Node root;
	
	private class Node{
		private Node left, right;
		private Key key;
		private Value val;
		private int N;
		public Node(Key key, Value val, int N) {
			
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	//return the comparison of two node keys
	private int compare(Key k1, Key k2) {
		
		return k1.compareTo(k2);
	}
	
	//implements the put method to add to the binary tree
	private Node put(Node node, Key key, Value val) {
		
		//if the node is non-existent, simply add new node and return it
		if(node == null) return new Node(key, val, 1);
		
		//compare the key of the incoming node with the ones already in existence
		int cmp = compare(key, node.key);
		//add to left if key of incoming node is less than the current root key
		if(cmp < 0) node.left = put(node.left, key, val);
		//add to right if key of incoming node is greater than root key
		else if(cmp > 0) node.right = put(node.right, key, val);
		
		//update the counts node counts
		node.N = size(node.left) + size(node.right) +1;
		
		return node;
		
		
	}
	
	public void put(Key key, Value val) {
		
		root = put(root, key, val);
		
		return;
	}

	//implements the size method
	private int size(Node node) {
		
		if(node == null) return 0;
		return node.N;
	}
	
	public int size() {
		
		return size(root);
	}
	
	
	//non recursive min() implementation
	public Key min() {
		
		Node x = root;
		//keep going to left until left link is null, then return key of node whose left link is null
		while(x.left != null) {
			
			x = x.left;
		}
		
		return x.key;
	}
	
	//non recursive max() implementation
	public Key max() {
		
		Node x = root;
		//keep going to right until node whose right link is null, then return its key as the largest key
		while(x.right != null) {
			
			x = x.right;
		}
		
		return x.key;
	}
	
	private Node floorOf(Key key) {
		//local integer variable for the compareTo
		int cmp;
		//a pointer to the root node
		 Node x = root;
		 //initialize a node to null value
		 Node tempFloor = null;
		 //iterate until current node is null
		 while(x != null) {
			 //compare the key of interest with to the key of current parent node
			cmp = compare(x.key, key);
			//if the keys are the same, break out of the loop
			if(cmp == 0) break;
			//if the current parent node's key is less than the key of interest, loop right
			if(cmp < 0 ) x = x.right;
			else {
				/*
				 * initialize temp to the current node. This would definitely be 
				 * the node with the key representing the floor of the key argument if
				 * we couldn't get any other key along the left link that is greater than the key 
				 * variable 
				 */
				tempFloor = x;
				x = x.left;
				
			}
			 
		 }
		 //if the variable x is null, then return temp
		 //if x is null, it means the argument 'x' does not exist in the binary tree,
		 //then return it floor
		 return (x == null ? tempFloor : x);
	}
	
	//non recursive floor implementation
	public Key floor(Key key) {
		
		Node x = floorOf(key);
		
		return (x == null ? null : x.key);
	}
 //non recursive implementation of ceiling
	private Node ceilingOf(Key key) {
		
		//get a pointer to the root node
		Node x = root;
		
		Node tempCeiling = null;
		//an integer value of compareTo result
		int cmp;
		//iterate loop while node is non-null
		while(x != null) {
			
			//compare the key with the current parent node
			cmp = compare(x.key, key);
			//break from the loop if we're at the node whose key we're interested in the ceiling
			if(cmp == 0)break;
			//if the current parent node has key greater than the key argument, loop to left
			else if(cmp > 0) x = x.left;
			
			//if the current parent node has key less than key argument, initialize temp to null
			//this would definitely be the node with the key less than key argument
			//if we didn't get any other key less than key argument along the right link
			else {
				tempCeiling = x;
				
				x = x.right;
				
			}
		}
		
		return (x == null ? tempCeiling : x);
	}
	
	public Key ceiling(Key key) {
		
		Node x = ceilingOf(key);
		
		return (x == null ? null : x.key);
	}
	
	//non recursive select()
	private Node selectFor(int r) {
		//get reference to the root node
		Node x = root;
		//number of left children for the current parent node
		int leftChild;
		int rank = r;
		
		//iterate while current is still non-null
		while(x  != null) {
			//get the number of left children
			leftChild = size(x.left);
			//leftChild == r break out of the loop
			if(leftChild == rank) break;
			if(leftChild > rank) {
				//if the current leftChild rank, search towards the left of the binary search tree
				x = x.left;
			}else {
				//decrement the rank by the number of current left children and search to the right
				rank -= (leftChild+1);
				x = x.right;
			}
		}
		
		return x;
	}
	
	public Key select(int r) {
		Node x = selectFor(r);
		
		return (x == null ? null : x.key);
	}
	
	//non recursive implementation of rank
	private int rankOf(Key key) {
		
		//reference to the root node
		Node x = root;
		int cmp;
		int leftChild = 0;
		//iterate while node is non-null
		while(x!= null) {
			cmp = compare(key, x.key);
			if(cmp == 0) return leftChild + size(x.left); //return number of left children
			
				if(cmp < 0) x = x.left;
				else {
					leftChild = leftChild+size(x.left) +1;//add the previous leftChildren to the current size of left children, then loop towards right
					x = x.right;
				
			}
		}
		
		return leftChild;//return this value since the key argument is not yet in the binary search tree. This is value of the rank if the key was present
	}
	
	public int rank(Key key) {
		
		return rankOf(key);
	}
	
	
	public static void main(String[]args) {
		
		String in = "ADEQJMTS";
		NonRecursives<String, String> bst = new NonRecursives<>();
		String[] keys = in.split("");
		for(String s: keys)
			bst.put(s, s.toLowerCase());
		

		
		System.out.printf("%-2s %4s %6s %8s %10s %12s %14s %16s", "rank", "value", "select", "value", "floor", "value", "ceiling","value\n");
		for(int i = 0; i<keys.length; i++) {
		System.out.printf("%-2s %4d %6s %8s %10s %12s %14s %16s", 
				keys[i], bst.rank(keys[i]), keys[i], bst.select(i), keys[i], bst.floor(keys[i]), keys[i], bst.ceiling(keys[i])+"\n");				
		}
		
		
	}
}
