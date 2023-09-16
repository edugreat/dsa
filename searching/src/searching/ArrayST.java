/*
 * Develop a symbol-table 'ArrayST' that uses an (unordered) array as the underlying 
 * data structure to implement our basic symbol-table API. 
 */
package searching;

@SuppressWarnings("unchecked")
public class ArrayST<Key, Value> {

	private Key[] keys; //unordered array for the Keys
	private Value[] vals; //unordered array for the Values
	
	private int N; //the number of Key-Value pairs there are in the symbol-table
	private static final int INITIAL_SIZE = 10;//initial array sizes
	public ArrayST() {
		
		//initializes the arrays to empty object arrays 
		keys = (Key[])new Object[INITIAL_SIZE];
		vals = (Value[]) new Object[INITIAL_SIZE];
	}
	
	//returns the size of the Key-value pairs in the ST
	private int size() {
		return N;
	}
	
	//Asserts the ST to be empty
	private boolean isEmpty() {
		return N == 0;
	}
	
	//returns the index of the current key in the keys[]
	private int indexOf(Key key) {
		
		
		for(int j = 0; j < size(); j++) {
			if(key.equals(keys[j])) {
				
				return j;
			}
		}
		
		return -1; //return -1 if the current key doesn't exist in the ST
	}
	
	//Gets the value associated with a particular key
	public Value get(Key key) {
		
		if(isEmpty())
			return null;
		
		if(key == null)
			return null;
		
		int i = indexOf(key);
		
		return i != -1 ? vals[i] : null;
		
	}
	
	public void put(Key key, Value val) {
		
		if(key == null || val == null)
			return;
		
		//check if the current key is already in the ST
		int i = indexOf(key);
		if(i >= 0) {
			vals[i] = val;//updates the current value associated with the current key
			return;
		}
		
		if(N == keys.length) //resizes the arrays if they are already filled
			resize(N*2);
		
		keys[N++] = key;//adds new key-value pair to the ST
		vals[N-1] = val;
		
	}
	
	//Deletes a key-value pair from the ST
	public void delete(Key key) {
		
		//get the index of the key to delete
		int i = indexOf(key);
		if(i < 0)
			return;
		
		for(int j = i; j < size(); j++) {
			keys[j] = keys[j+1];
			vals[j] = vals[j+1];
		}
		keys[N-1] = null;
		vals[--N] = null; //decrements the size of the ST
	}
	
	
	//resizes the ST arrays 
	private void resize(int sz) {
		Key[] k = (Key[])new Object[sz];
		Value[] v = (Value[])new Object[sz];
		for(int i = 0; i < keys.length; i++) {
			k[i] = keys[i];
			v[i] = vals[i];
		}
		
		keys = k;
		vals = v;
	}
	
	
	public static void main(String[]args) {
		
		Integer[]keys= {3,5,1,6,7,8,2,9,4};
		String[] vals = {"Three","Five","One","Six","Seven","Eight","Two","Nine","Four"};
		ArrayST<Integer, String> st = new ArrayST<>();
		for(int i = 0; i < keys.length; i++) {
			st.put(keys[i], vals[i]);
		}
		
		System.out.println(st.get(2));
		System.out.println(st.get(3));
		System.out.println(st.get(8));
		st.put(8, "eight");
		st.put(10, "Ten");
		st.put(100, "One hundred");
		System.out.println(st.get(100));
		System.out.println(st.get(8));
		
		st.delete(3);
		System.out.println(st.get(3));
		System.out.println(st.get(4));
		
	}
}
