import java.io.*;
import java.util.*;

public class Assignment_3 {
	
	public static void main(String[] args) {

		final File LYRICS_FILE =  new File("lyrics.text");
		HashMap<String, Integer> wordMap = new HashMap<>();

		// Special characters that are either unacceptable or to be ignored
		String[] unacceptableChars = { ",", ";", ":" };

		try {

			Scanner sc = new Scanner (LYRICS_FILE);

			while (sc.hasNext()) {

				// Reads next word in lyrics file
				String currentWord = sc.next();

				// Parses out all unacceptable characters
				for (String s: unacceptableChars) {
					currentWord = currentWord.replace(s, "");
				}

				// Brings word to lower case format for matching purposes
				currentWord = currentWord.toLowerCase();
				
				// Check the map if the word has been entered already
				if (!wordMap.containsKey(currentWord)) {

					// Add new word entry
					wordMap.put(currentWord, 1);

				} else {

					// Increment existing word
					wordMap.put(currentWord, wordMap.get(currentWord) + 1);

				}

			}

		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe);
		}

		wordMap = sortWordMap(wordMap);
	}

	public static HashMap<String, Integer> sortWordMap(HashMap<String, Integer> wordMap) {

		HashMap<String, Integer> sortedWordMap = new HashMap<>();
		ArrayList<Integer> wordCount = new ArrayList<>();
		ArrayList<String> wordKey = new ArrayList<>();

		for (String s: wordMap.keySet()) {
			wordCount.add(wordMap.get(s));
			wordKey.add(s);
		}

		// Just a bubble sort, but swaps both the count and key.
		for (int i = 0; i < wordCount.size(); i++) {
			
			for (int j = 0; j < wordCount.size(); j++) {
				
				if (wordCount.get(i) > wordCount.get(j)) {

					int count_item = wordCount.get(i);
					String key_item = wordKey.get(i);

					wordCount.set(i, wordCount.get(j));
					wordKey.set(i, wordKey.get(j));

					wordCount.set(j, count_item);
					wordKey.set(j, key_item);

				}
			}
		}

		for (int k = 0; k < wordKey.size(); k++) {
			System.out.println(wordCount.get(k) + ": " + wordKey.get(k));
			sortedWordMap.put(wordKey.get(k), wordCount.get(k));
		}

		return sortedWordMap;
	}
}