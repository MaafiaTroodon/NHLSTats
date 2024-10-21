package FileReadWriteExamples.FileReadWriteExamples;//This program reads lines from a text file and displays each word on a separate line
//Input: a .txt file
import java.util.Scanner;
import java.io.*;

public class FileReadDemo2{
	public static void main(String[] args) throws IOException{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the name of the file to read from: ");
		String filename = keyboard.nextLine();
		File inFile = new File(filename);
		Scanner input = new Scanner(inFile);
		while (input.hasNext()){
			String line = input.next();
			System.out.println(line);
		}
		input.close();
	}
}
