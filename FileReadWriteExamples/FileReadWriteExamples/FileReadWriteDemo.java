//This program reads lines from a text file and writes them into another file
//Input: a .txt file to read from and another to write to
import java.util.Scanner;
import java.io.*;

public class FileReadWriteDemo {
    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);

        // Get the input file name
        System.out.print("Enter the name of the file to read from: ");
        String inputFilename = keyboard.nextLine();
        File inFile = new File(inputFilename);
        Scanner input = new Scanner(inFile);

        // Get the output file name
        System.out.print("Enter the name of the file to write to: ");
        String outputFilename = keyboard.nextLine();
        PrintWriter output = new PrintWriter(outputFilename);

        // Read from input file and write to output file
        while (input.hasNext()) {
            String line = input.nextLine();
            output.println(line); // Write to the output file
        }

        // Close both files
        input.close();
        output.close();

        System.out.println("Content has been written to " + outputFilename);
    }
}
