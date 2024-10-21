package FileReadWriteExamples.FileReadWriteExamples;//This program reads lines from a text file and displays them
//Input: a .txt file
import java.util.Scanner;
import java.io.*;

public class FileReadDemo1{
	public static void main(String[] args) throws IOException{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the name of the file to read from: ");
		String filename = keyboard.nextLine();
		File inFile = new File(filename);
		Scanner input = new Scanner(inFile);
		while (input.hasNext()){
			String line = input.nextLine();
			System.out.println(line);
		}
		input.close();
	}
}
