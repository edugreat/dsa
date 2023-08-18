package miscellaneous;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Two words are anagram if they contain the same letters
 * but in different order. Implement an algorithm that detects anagrams.
 * For instance the words 'listen' and 'silent'are anagrams. As an added challenge, for a given array
 * of strings, return separate list that group anagrams together. For instance, the input {"tar","rat","art","meats","steam"},
 * the output should look something like {["tar","rat","art"],["meats","steam"]}
 * 
 */
public class Anagram {
	
	public static void main(String[] args) {
		String[]a = {"tar","rat","art","meats","steam"};

		List<List<String>> grouped =  anagramList(a);
		for(List<String> l: grouped) {
			System.out.println(l);
		}
		
		
		
	}
   public static boolean isAnagram(String a, String b) {
	   
	   if(a.equals(b) || a.length() != b.length()) //the words must have equal characters and must never have same alphabetical arrangement
		   return false;
	   
	   a = a.toLowerCase(); //safe approach
	   b = b.toLowerCase();
	 
	   for(int i = 0; i < a.length(); i++) {
		   
		   if(b.indexOf(a.charAt(i)) < 0 || a.indexOf(b.charAt(i)) < 0 )
			   return false;
	   }
	 
	   return true;
   }
   
   public static List<List<String>> anagramList(String[] a){
	   
	   int N = a.length;
	   int index = 0;
	   List<List<String>> grouped = new ArrayList<>();
	   while(N > 0 && index < a.length) {
		   String current = a[index++];
		   if(grouped.isEmpty()) {
			   List<String> list = new ArrayList<>();
			   list.add(current);
			   for(int i = 0; i < a.length; i++) {
				   if(isAnagram(a[i], current)) {
					   list.add(a[i]);
					   a[i] = "";
					   N--;
				   }
					  
			   }
			   grouped.add(list);
		   }else {
			   
			   if(current.length() > 0) {
				   List<String> list= new ArrayList<>();
				   list.add(current);
				   for(int i = 0; i < a.length; i++) {
					   if(isAnagram(a[i], current)) {
						   list.add(a[i]);
						   a[i] = "";
						   N--;
					   }
						  
				   }
				   grouped.add(list);
				   
			   }
			   
			   
		   }
		   
	   }
	   
	   return grouped;
   }

	
}
