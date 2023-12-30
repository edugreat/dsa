package searching;



/*
 *  Write a method hasNoDuplicates() that takes a node as argument and returns true if there are no equal keys in
 *  the binary tree rooted at the argument node, false otherwise.
 */
public class BSTWITHEQUALITYKEYCHECKS<Key extends Comparable<Key>, Value> {

	// The root node of the BST
	private Node root;

	// private Node class that defines the properties of a node
	private class Node {

		// the left child of a node
		private Node left;
		// the right child of a node
		private Node right;
		// the size of a node(that number of children of a node)

		// Key associated with a node
		private Key key;

		// value associated with a particular key
		private Value value;
		private int N;

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.value = val;
			this.N = N;
		}

	}

	// implements the compareTo method
	private int compare(Key child, Key parent) {

		return child.compareTo(parent);

	}

	// get the size of a node(it's number of children)
	private int size(Node node) {

		// if the node doesn't exist return its size as zero
		if (node == null)
			return 0;

		else
			return node.N;

	}

	// get the total size of the root node
	public int size() {

		return size(root);

	}

	// add a key-value pair to the BST
	private Node put(Node node, Key key, Value val) {

		// if the node is empty, just create new node and return it
		if (node == null)
			return new Node(key, val, 1);

		// compare the incoming key with current parent key
		int cmp = compare(key, node.key);

		// if the child key is less than the parent's key, we recursively transfer it to
		// the left part of the node
		if (cmp < 0)
			node.left = put(node.left, key, val);
		// send it to the right if it is greater than its parent node
		else if (cmp > 0)
			node.right = put(node.right, key, val);

		// since the key we intend to insert already exists, just update the value
		else
			node.value = val;

		// update the size counter by return the size of the children with the current
		// parent node included
		node.N = size(node.left) + size(node.right) + 1;

		// returning the node would simply update the root node and it's properties
		return node;

	}

	// public method for adding new node
	public Node put(Key key, Value val) {

		return root = put(root, key, val);

	}

	// private helper method for calculating the minimum key in the BST
	private Node min(Node node) {

		// be going to the left until the node whose left child is null
		// once we hit a node whose left child is null, return that node(it is the node
		// with the minimum key)
		// If the BST is currently empty, the return value is null(the minimum node is
		// null)
		if (node.left == null)
			return node;

		// continue going until we hit a node whose left child is null
		return min(node.left);

	}

	// public method that calculates the minimum key value of a BST
	public Key min() {

		return min(root).key;
	}

	// private helper method that calculates the maximum BST key
	private Node max(Node node) {
		// continue going to the right until you hit a node whose right child is null,
		// the return it
		if (node.right == null)
			return node;

		return max(node.right);

	}

	// public method that calculates the maximum key in a BST
	public Key max() {
		return max(root).key;
	}

	// get the rank of a key
	// the rank of a key is the number representing the number key less than the key
	private int rank(Node node, Key key) {

		// if the BST is empty or the key does not exist in the BST, return zero
		if (node == null)
			return 0;

		int cmp = compare(key, node.key);

		if (cmp < 0)
			return rank(node.left, key);// go towards left
		else if (cmp == 0)
			return size(node.left);// get the size of all left children

		// however, if the key is greater than the parent node
		// then add all the elements to the left of the parent and continue going to the
		// right
		else
			return 1 + size(node.left) + rank(node.right, key);

	}

	// public method that returns the rank of a key
	public int rank(Key key) {

		return rank(root, key);
	}

	// private helper method that returns the key whose rank is the given int
	// argument
	// that is, get the key that has the number of keys less than it equals 'rank'
	private Node select(Node node, int rank) {
		if (node == null)
			return null;

		// get the size of the left children
		int x = size(node.left);

		// if the size of left children is greater than the rank, then the key we're
		// looking for
		// is to the left of the current parent node
		if (rank < x)
			return select(node.left, rank);

		else if (rank == x)
			return node;

		// however, if the rank is greater than the number of left element
		// then we go to the right of the current parent node
		// but we must take into account the current number of left children and that of
		// the current
		// parent node by subtracting those values from the rank argument
		else {
			int currentRank = rank - x - 1;
			return select(node.right, currentRank);
		}

	}

	// public method that returns the key whose rank is the given int argument
	public Node select(int rank) {

		Node x = selectFor(root, rank);
		if(x != null) return x;
		
		return null;
	}

	// get the floor of the given key argument
	// the floor of a key is the largest key that is smaller than or equal to the
	// given argument
	private Node floor(Node node, Key key) {

		// if the current node is null return null
		if (node == null)
			return null;

		// compare the current parent node with the key whose floor is to be returned
		int cmp = compare(key, node.key);

		// if the key is less than the current parent, search to the left
		if (cmp < 0)
			return floor(node.left, key);

		// if we've found the node return it
		else if (cmp == 0)
			return node;

		// if the key is greater than the current node,
		// then move to the right of the current parent node
		// if the returned value is null, then we couldn't find the we are looking for,
		// and the node at which we branched to go the right is the node with the floor
		// of the given key,
		// otherwise we've found the node we're looking for
		Node t = floor(node.right, key);
		if (t != null)
			return t;
		else
			return node;
	}

	// public method that returns the floor of the given key argument
	public Key floor(Key key) {

		Node x = floor(root, key);
		if (x == null)
			return null;// here the BST is empty

		return x.key;
	}

	// private helper method that returns the ceiling of a key
	// the ceiling of a key is the smallest key that is greater than or equal to the
	// given key argument
	private Node ceiling(Node node, Key key) {

		if (node == null)
			return null;

		int cmp = compare(key, node.key);

		if (cmp > 0)
			return ceiling(node.right, key);

		else if (cmp == 0)
			return node;

		Node t = ceiling(node.left, key);

		if (t != null)
			return t; // we've found the key

		else
			return node; // we get couldn't the key, hence current node from which we branched towards
							// left is the ceiling of key

	}

	// public method that returns the ceiling of a give key argument
	public Key ceiling(Key key) {

		Node t = ceiling(root, key);
		if (t == null)
			return null; // it shows the BST is currently empty;

		return t.key;
	}

	// private helper method that deletes a particular key-value pair from the BST
	private Node delete(Node node, Key key) {
		// steps involved:
		// 1. If node is null, simply return null (base case)
		// 2. If the key to delete is less than the current parent key, recursively go
		// left
		// 3. If the key to delete is greater than the current parent key, recursively
		// go right
		// 4. If we've found the key to delete check:
		// 5. If the has neither left no right children, simply return null
		// 6. If the key has left child(ren) then return the largest of it's left
		// child.This is the successor of the deleted key
		// 7.After step 6 delete from the original link, the largest key returned in
		// step 6
		// 8. Update the counter up the root node
		// If condition 6 fails,then the key has right child(ren)
		// 9: return the smallest key of the children,this is the successor of the
		// deleted key
		// 10: After step 9, delete from the original subtree the just returned key
		// 11: Update the counter way up the root node

		if (node == null)
			return null;
		// compare the key to delete with its parent key
		int cmp = compare(key, node.key);
		// go left if the key is less than the parent key
		if (cmp < 0)
			node.left = delete(node.left, key);
		// go right if the key is larger than the parent key
		else if (cmp > 0)
			node.right = delete(node.right, key);

		// here we've actually found the key
		else {
			// check if the key has either left or right children
			if (node.left == null)
				return node.right;
			if (node.right == null)
				return node.left;

			// the link has both left and right links.Get a handle on the left link of the
			// node
			Node t = node.left;

			// save away the right right to the variable t
			Node r = node.right;
			// get the node with the largest key from the left link
			node = max(t);
			// node = min(r);
			// delete the succeeding node from the original subtree and return the remaining
			// subtree as left link
			node.left = deleteMax(t);
			// node.right = deleteMin(r);

			// complete the binary search tree by attaching the right branch
			node.right = r;

		}
		// update counter up the root node
		node.N = size(node.left) + size(node.right) + 1;
		return node;

	}

	// public method that deletes a key-value pair from the BST
	public void delete(Key key) {

		root = delete(root, key);

	}

	// helper method that deletes the largest node from a subtree
	private Node deleteMax(Node x) {

		// continue going to the right until a node with null right link, then return
		// it's left link
		if (x.right == null)
			return x.left;
		else
			x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;

	}

	// private method that returns a node for a given rank argument
	private Node selectFor(Node node, int r) {
		// get reference to the root node
		Node x = node;
		// number of left children for the current parent node
		int leftChild;
		int rank = r;

		// iterate while current is still non-null
		while (x != null) {
			// get the number of left children
			leftChild = size(x.left);
			// leftChild == r break out of the loop
			if (leftChild == rank)
				break;
			if (leftChild > rank) {
				// if the current leftChild rank, search towards the left of the binary search
				// tree
				x = x.left;
			} else {
				// decrement the rank by the number of current left children and search to the
				// right
				rank -= (leftChild + 1);
				x = x.right;
			}
		}

		return x;
	}
	
	// duplicates checks
	private boolean hasDuplicates(Node node) {
		
		Node x = node;
		
		//get size of the current node argument, excluding the parent node
		int size = size(node) - 1;
		
		int counter1 = 0;//keeps tracks of the 'size' local variable
		
		while(counter1 < size) {
			
			
			//get the key to compare against another key
			Key firstKey = selectFor(x, counter1).key;
			
			int counter2 = counter1+1; //keeps count of the second iterative operation
			while(counter2 <= size) {
				
				//get the second key to compare against the first key
				Key secondKey = selectFor(x, counter2++).key;
				
				System.out.println("comparing "+firstKey+" --> "+secondKey);
				//if(compare(firstKey, secondKey) == 0) return true;
			}
			
			counter1++;
		}
		
		return false;
	}
	
	public boolean hasNoDuplicates(Node x) {
		
		return !hasDuplicates(x);
		
	}
	
	public static void main(String[] args) {
		
		String in = "ADEQJMTS";
		String[] sp = in.split("");
		BSTWITHEQUALITYKEYCHECKS<String, String> bst = new BSTWITHEQUALITYKEYCHECKS<>();
		for(String s: sp)
			bst.put(s, s.toLowerCase());
		
		
		int sz = bst.size();
		for(int i = 0; i < sz; i++) {
			
			
			System.out.println("has no duplicates: "+bst.hasNoDuplicates(bst.select(i)));
		
		}
		
		System.out.println("has no duplicates: "+bst.hasNoDuplicates(bst.select(0)));
	}
}
