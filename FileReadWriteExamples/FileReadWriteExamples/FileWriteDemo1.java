package FileReadWriteExamples.FileReadWriteExamples;//Reads lines from the standard input and writes them into a .txt file
import java.io.*;
import java.util.Scanner;
public class FileWriteDemo1{
	public static void main(String[] args) throws IOException{
		String name;
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the name of the .txt file to write into: ");
		String filename = keyboard.nextLine();
		PrintWriter outputFile = new PrintWriter(new FileWriter(filename));
		System.out.println("Enter words, end with quit: ");
		name = keyboard.nextLine();
		while (!name.equals("quit")){
			outputFile.println(name);
			name = keyboard.nextLine();
		}
		outputFile.close();
		System.out.println("Data written into file " + filename);
	}
}

