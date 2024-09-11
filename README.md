ProbableText: Text Generation with N-grams
Project Overview
This project implements an N-gram-based text generator using custom data structures in Java. The program processes an input text file, builds a map of N-grams (subsequences of N characters), and generates new text based on the probability of characters following each N-gram.

Features
N-gram Map: The program reads a text file and builds a map of N-grams, where each N-gram is mapped to a list of possible following characters.
Custom Data Structures: The map is implemented using a custom OurHashMap class, which relies on separate chaining via a custom OurLinkedList class to handle hash collisions.
Text Generation: Once the N-gram map is built, the program can generate random text based on the N-grams and the characters that are likely to follow them.
Classes Overview

1. ProbableText

The ProbableText class handles the core logic of building the N-gram map and generating text based on that map.

Attributes:

  ngramMap: An instance of OurHashMap<String, ArrayList<Character>> that stores the N-grams as keys and the characters that follow them as values.
  rng: A Random instance for selecting random N-grams and characters.
  keys: A StringBuilder containing all N-grams for random selection.
  Methods:

buildMap(String bookName, int n): Builds the N-gram map from a text file, where n is the size of the N-gram.

generateText(int numChars): Generates a random sequence of text with numChars characters based on the N-gram map.

loadTextFromFile(String bookName): Loads text from the specified file.

pickRandomNgram(): Randomly selects an N-gram from the keys.

2. OurHashMap<K, V>
A custom hash map class that stores key-value pairs using separate chaining (via linked lists) to handle collisions.

Attributes:
  lists: An array of OurLinkedList instances to store key-value pairs.
  size: Tracks the number of key-value pairs stored.
  Methods:
  put(K key, V value): Adds a key-value pair to the map.
  get(K key): Retrieves the value associated with a key.
  containsKey(K key): Checks if the map contains a given key.
  size(): Returns the number of key-value pairs in the map.
3. OurLinkedList<Type>
A custom linked list implementation to store elements in the hash map.

Attributes:

front: A reference to the first node in the list.
n: The number of elements in the list.
Methods:

size(): Returns the number of elements in the list.
get(int index): Retrieves the element at the given index.
addFront(Type element): Adds an element to the front of the list.
Usage
1. Building the N-gram Map
To build the N-gram map from a text file, call the buildMap method in ProbableText. The method requires the name of the text file (without the extension) and the size of the N-gram.

ProbableText pt = new ProbableText();
pt.buildMap("myBook", 4); // Build a 4-gram map from the file './books/myBook.txt'
2. Generating Text
Once the map is built, you can generate random text using the generateText method. Specify the number of characters you want to generate.


String randomText = pt.generateText(1000); // Generate 1000 characters of random text
System.out.println(randomText);

3. File Location
The text files should be stored in a folder named ./books/. For example, if your file is myBook.txt, it should be located in ./books/myBook.txt.

Example
public class Main {
    public static void main(String[] args) {
        ProbableText probableText = new ProbableText();
        
        // Build a 4-gram map from 'exampleBook.txt'
        probableText.buildMap("exampleBook", 4);
        
        // Generate 500 characters of random text
        String generatedText = probableText.generateText(500);
        
        System.out.println("Generated Text: \n" + generatedText);
    }
}
Dependencies
No external libraries are required.
Custom data structures OurHashMap and OurLinkedList are implemented from scratch.
Directory Structure

/project-root
    /src
        /model
            OurHashMap.java
            OurLinkedList.java
            ProbableText.java
            OurMap.java
            OurList.java
    /books
        exampleBook.txt
Author
Developed by Kyle Vega
