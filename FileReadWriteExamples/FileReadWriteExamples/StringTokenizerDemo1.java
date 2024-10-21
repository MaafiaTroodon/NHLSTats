package FileReadWriteExamples.FileReadWriteExamples;

import java.util.Scanner;
import java.util.StringTokenizer;

public class StringTokenizerDemo1 {
    public static void main(String[] args) {
        // Create a Scanner object to accept user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user for input sentence
        System.out.print("Enter a sentence to tokenize: ");
        String sentence = scanner.nextLine();

        // Ask the user for custom delimiters
        System.out.print("Enter the delimiters (e.g., space, comma, etc.): ");
        String delimiters = scanner.nextLine();

        // Create a StringTokenizer object with custom delimiters
        StringTokenizer tokenizer = new StringTokenizer(sentence, delimiters);

        // Display the total number of tokens
        System.out.println("Total number of tokens: " + tokenizer.countTokens());

        // Process and display each token
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            System.out.println(token);
        }

        // Close the scanner
        scanner.close();
    }
}

