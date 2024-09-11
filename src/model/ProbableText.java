package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ProbableText {

    // HashMap to store the n-grams as keys and the corresponding following characters as values.
	private OurHashMap<String, ArrayList<Character>> ngramMap;
	
    // Random number generator for selecting random n-grams and characters.
	private Random rng;

    // Constructor to initialize the map and the random number generator.
	public ProbableText() {
		ngramMap = new OurHashMap<>();
		rng = new Random();
	}

    // StringBuilder to store all the n-gram keys for easy access.
	public StringBuilder keys = new StringBuilder();

    /**
     * This method builds the n-gram map from the text file. It processes the text in chunks of 'n' characters
     * (n-grams) and maps them to the next character in the text. 
     * 
     * @param bookName The name of the text file (without extension).
     * @param n The size of the n-gram (number of characters in each chunk).
     */
	public void buildMap(String bookName, int n) {
		StringBuilder file = loadTextFromFile(bookName);
		for (int i = 0; i < file.length() - n; i++) {
			String ngram = file.substring(i, i + n); // Extract n-gram
			char nextChar = file.charAt(i + n); 

            // If the n-gram is not in the map, add it with an empty list
			if (!ngramMap.containsKey(ngram)) {
				ngramMap.put(ngram, new ArrayList<Character>());
			}

            // Add the next character to the list of possible following characters for this n-gram
			ngramMap.get(ngram).add(nextChar);
			
            // Append the n-gram to the 'keys' StringBuilder for later random selection
			keys.append(ngram + "@");
		}
	}

    /**
     * This method loads the contents of a text file and returns it as a StringBuilder.
     * It searches for the file in the "./books/" directory.
     * 
     * @param bookName The name of the text file (without extension).
     * @return The text file contents as a StringBuilder.
     */
	private StringBuilder loadTextFromFile(String bookName) {
		StringBuilder fileStr = new StringBuilder();
		Scanner input = new Scanner(System.in);

		try {
            // Locate the file in the './books/' directory.
			File file = new File("./books/" + bookName + ".txt");
			Scanner fileInput = new Scanner(file);

            // Read the file line by line and append each line to the StringBuilder
			while (fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				fileStr.append(line.toString() + " ");
			}

			fileInput.close();

		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file '" + bookName + "'");
			input.close();
			return null;
		}

		input.close();
		return fileStr;
	}

    /**
     * This method generates random text using the n-grams stored in the map. It selects random n-grams 
     * and their corresponding following characters, and builds the output text.
     * 
     * @param numChars The number of characters to generate.
     * @return A string containing the generated text.
     */
	public String generateText(int numChars) {
		StringBuilder sb = new StringBuilder();
		String ngram = ""; // Initialize with an empty n-gram
		for (int i = 0; i < numChars; i++) {

            // Insert a line break every 60 characters
			if (i != 0 && i % 60 == 0) {
                // If the n-gram doesn't exist, pick a random one
				if (!ngramMap.containsKey(ngram)) {
					ngram = pickRandomNgram();
				}

                // Get the list of possible next characters for the n-gram
				ArrayList<Character> possibleNext = ngramMap.get(ngram);
                // Pick a random character from the list
				char next = possibleNext.get(rng.nextInt(possibleNext.size()));
				sb.append(next + "\n");
				ngram = ngram.substring(1) + next;

			} else {
                
				if (!ngramMap.containsKey(ngram)) {
					ngram = pickRandomNgram();
				}
				ArrayList<Character> possibleNext = ngramMap.get(ngram);
				char next = possibleNext.get(rng.nextInt(possibleNext.size()));
				sb.append(next);
				ngram = ngram.substring(1) + next;
			}
		}
		return sb.toString();
	}

    /**
     * This method selects a random n-gram from the keys stored in the 'keys' StringBuilder.
     * 
     * @return A randomly selected n-gram.
     */
	private String pickRandomNgram() {
		int index = rng.nextInt(ngramMap.size());
		String[] keyList = keys.toString().split("@");
		return keyList[index];
	}

}
