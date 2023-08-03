package exercises;

import java.util.HashMap;
import java.util.Map;

/*
 * write a program that gets the second most repeated string in a sequence of string characters
 */

public class SecondMostRepeatedCharacter {
	
	public static void main(String[] args) {
		
		
	}
	
	private static String secondMostRepeated(String[] characters) {
		
		Map<String, Integer> characterMap = new HashMap<>();
		for(String character: characters)
			characterMap.put(character, characterMap.getOrDefault(character, 0) + 1);
		
		String mostFrequentCharacter  = "";
		String secondMostFrequentCharacter = "";
		int mostFrequencyCount = 0;
		int secondMostFrequencyCount = 0;
		for(String key : characterMap.keySet()) {
			
			int frequency = characterMap.get(key);
			if(frequency > mostFrequencyCount) {
			secondMostFrequencyCount = mostFrequencyCount;
			secondMostFrequentCharacter = mostFrequentCharacter;
			
			mostFrequencyCount = frequency;
			mostFrequentCharacter = key;
			}else if(frequency > secondMostFrequencyCount && frequency < mostFrequencyCount) {
				secondMostFrequencyCount = frequency;
				mostFrequentCharacter = key;
			}
		}
		
		return secondMostFrequentCharacter;
		
		
	}

}
