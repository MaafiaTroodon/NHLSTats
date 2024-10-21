import java.io.*;
import java.util.Scanner;

public class NHLStatsDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        NHLStats nhlStats = new NHLStats();

        // Ask the user to input the filename
        System.out.print("Enter the filename to read from: ");
        String filename = input.nextLine();

        // Check if the file exists
        File file = new File(filename);
        if (file.exists() && !file.isDirectory()) {
            try {
                // Read player records from the file
                nhlStats.readFromFile(filename);

                // Prepare to write results to output file
                String outputFilename = "nhlstatsoutput.txt";
                File outputFile = new File(outputFilename);

                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
                writer.write("NHL Results Summary\n");

                // Players with the highest points
                writer.write("Players with highest points and their teams:\n");
                writer.write(nhlStats.findPlayerWithMostPoints());

                // Most aggressive players (most penalty minutes)
                writer.write("Most aggressive players, their teams and their positions:\n");
                writer.write(nhlStats.findMostAggressivePlayer());

                // MVP players (most game-winning goals)
                writer.write("Most valuable players and their teams:\n");
                writer.write(nhlStats.findMVP());

                // Most promising players (most shots on goal)
                writer.write("Most promising players and their teams:\n");
                writer.write(nhlStats.findMostPromisingPlayer());

                // Teams with the most penalty minutes
                writer.write("Teams that had the most number of penalty minutes:\n");
                writer.write(nhlStats.findTeamWithMostPenaltyMinutes());

                // Teams with the most game-winning goals
                writer.write("Teams that had the most number of game-winning goals:\n");
                writer.write(nhlStats.findTeamWithMostGameWinningGoals());

                writer.close();
                System.out.println("Results successfully written to " + outputFilename);

            } catch (IOException e) {
                System.out.println("Error reading from or writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("File not found or is a directory: " + filename);
        }

        input.close();
    }
}
