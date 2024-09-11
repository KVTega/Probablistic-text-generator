package view_controller;

import java.util.Scanner;

import model.ProbableText;

public class Main {

  public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("Enter book name: ");
    String bookName = scanner.nextLine();
    
    System.out.print("Enter ngram length: ");
    int ngramLength = scanner.nextInt();
    
    System.out.print("How many characters? ");
    int numChars = scanner.nextInt();
    
    ProbableText textGenerator = new ProbableText();
    textGenerator.buildMap(bookName, ngramLength);
    
    String generatedText = textGenerator.generateText(numChars);
    System.out.println(generatedText);
    scanner.close();
  }

}
