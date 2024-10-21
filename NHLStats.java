import java.io.*;
import java.util.StringTokenizer;

public class NHLStats {
    // LinkedList of PlayerRecord objects
    private LinkedList<PlayerRecord> players;

    // Constructor to create an empty list
    public NHLStats() {
        players = new LinkedList<>();
    }

    // Method to add a PlayerRecord to the list
    public void add(PlayerRecord player) {
        players.add(player);
    }

    // Method to read player records from a file
    public void readFromFile(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            // Tokenize the line based on tab delimiter
            StringTokenizer token = new StringTokenizer(line, "\t");
            String name = token.nextToken();
            String position = token.nextToken();
            String team = token.nextToken();
            int gamesPlayed = Integer.parseInt(token.nextToken());
            int goals = Integer.parseInt(token.nextToken());
            int assists = Integer.parseInt(token.nextToken());
            int penaltyMinutes = Integer.parseInt(token.nextToken());
            int shotsOnGoal = Integer.parseInt(token.nextToken());
            int gameWinningGoals = Integer.parseInt(token.nextToken());

            // Create a new PlayerRecord and add it to the list
            PlayerRecord player = new PlayerRecord(name, position, team, gamesPlayed, goals, assists, penaltyMinutes, shotsOnGoal, gameWinningGoals);
            add(player);
        }

        reader.close();
    }


    // Method to find the player(s) with the most points (goals + assists)
    public String findPlayerWithMostPoints() {
        Node<PlayerRecord> current = players.getFront();
        int maxPoints = 0;
        StringBuilder result = new StringBuilder();

        while (current != null) {
            PlayerRecord player = current.getData();
            int playerPoints = player.getTotalPoints();
            if (playerPoints > maxPoints) {
                maxPoints = playerPoints;
                result.setLength(0); // Clear previous results
                result.append(player.getName()).append(" (").append(player.getTeam()).append(")\n");
            } else if (playerPoints == maxPoints) {
                result.append(player.getName()).append(" (").append(player.getTeam()).append(")\n");
            }
            current = current.getNext();
        }

        return result.toString();
    }

    // Method to find the player(s) with the most penalty minutes (Most Aggressive)
    public String findMostAggressivePlayer() {
        Node<PlayerRecord> current = players.getFront();
        int maxPenaltyMinutes = 0;
        StringBuilder result = new StringBuilder();

        while (current != null) {
            PlayerRecord player = current.getData();
            int penaltyMinutes = player.getPenaltyMinutes();
            if (penaltyMinutes > maxPenaltyMinutes) {
                maxPenaltyMinutes = penaltyMinutes;
                result.setLength(0); // Clear previous results
                result.append(player.getName()).append(" (").append(player.getTeam()).append(", ").append(player.getPosition()).append(")\n");
            } else if (penaltyMinutes == maxPenaltyMinutes) {
                result.append(player.getName()).append(" (").append(player.getTeam()).append(", ").append(player.getPosition()).append(")\n");
            }
            current = current.getNext();
        }

        return result.toString();
    }




    // Method to find the player(s) with the most game-winning goals (MVP)
    public String findMVP() {
        Node<PlayerRecord> current = players.getFront();
        int maxGameWinningGoals = 0;
        StringBuilder result = new StringBuilder();

        while (current != null) {
            PlayerRecord player = current.getData();
            int gameWinningGoals = player.getGameWinningGoals();
            if (gameWinningGoals > maxGameWinningGoals) {
                maxGameWinningGoals = gameWinningGoals;
                result.setLength(0); // Clear previous results
                result.append(player.getName()).append(" (").append(player.getTeam()).append(")\n");
            } else if (gameWinningGoals == maxGameWinningGoals) {
                result.append(player.getName()).append(" (").append(player.getTeam()).append(")\n");
            }
            current = current.getNext();
        }

        return result.toString();
    }

    // Method to find the player(s) with the most shots on goal (Most Promising)
    public String findMostPromisingPlayer() {
        Node<PlayerRecord> current = players.getFront();
        int maxShotsOnGoal = 0;
        StringBuilder result = new StringBuilder();

        while (current != null) {
            PlayerRecord player = current.getData();
            int shotsOnGoal = player.getShotsOnGoal();
            if (shotsOnGoal > maxShotsOnGoal) {
                maxShotsOnGoal = shotsOnGoal;
                result.setLength(0); // Clear previous results
                result.append(player.getName()).append(" (").append(player.getTeam()).append(")\n");
            } else if (shotsOnGoal == maxShotsOnGoal) {
                result.append(player.getName()).append(" (").append(player.getTeam()).append(")\n");
            }
            current = current.getNext();
        }

        return result.toString();
    }

    // Method to find the team(s) with the most penalty minutes using linked list
    public String findTeamWithMostPenaltyMinutes() {
        LinkedList<String> teams = new LinkedList<>();
        LinkedList<Integer> penalties = new LinkedList<>();
        Node<PlayerRecord> current = players.getFront();

        // Calculate total penalty minutes per team
        while (current != null) {
            PlayerRecord player = current.getData();
            String team = player.getTeam();
            int penaltyMinutes = player.getPenaltyMinutes();

            int index = findTeamIndex(teams, team);
            if (index == -1) {
                teams.add(team);
                penalties.add(penaltyMinutes);
            } else {
                penalties.setAt(penalties.getAt(index) + penaltyMinutes, index);
            }
            current = current.getNext();
        }

        // Find team(s) with the most penalty minutes
        int maxPenaltyMinutes = 0;
        StringBuilder result = new StringBuilder();
        Node<Integer> penaltyNode = penalties.getFront();
        Node<String> teamNode = teams.getFront();

        while (penaltyNode != null && teamNode != null) {
            int penaltyMinutes = penaltyNode.getData();
            String team = teamNode.getData();
            if (penaltyMinutes > maxPenaltyMinutes) {
                maxPenaltyMinutes = penaltyMinutes;
                result.setLength(0); // Clear previous results
                result.append(team).append("\n");
            } else if (penaltyMinutes == maxPenaltyMinutes) {
                result.append(team).append("\n");
            }
            penaltyNode = penaltyNode.getNext();
            teamNode = teamNode.getNext();
        }

        return result.toString();
    }

    // Method to find the team(s) with the most game-winning goals using linked list
    public String findTeamWithMostGameWinningGoals() {
        LinkedList<String> teams = new LinkedList<>();
        LinkedList<Integer> goals = new LinkedList<>();
        Node<PlayerRecord> current = players.getFront();

        // Calculate total game-winning goals per team
        while (current != null) {
            PlayerRecord player = current.getData();
            String team = player.getTeam();
            int gameWinningGoals = player.getGameWinningGoals();

            int index = findTeamIndex(teams, team);
            if (index == -1) {
                teams.add(team);
                goals.add(gameWinningGoals);
            } else {
                goals.setAt(goals.getAt(index) + gameWinningGoals, index);
            }
            current = current.getNext();
        }

        // Find team(s) with the most game-winning goals
        int maxGameWinningGoals = 0;
        StringBuilder result = new StringBuilder();
        Node<Integer> goalsNode = goals.getFront();
        Node<String> teamNode = teams.getFront();

        while (goalsNode != null && teamNode != null) {
            int gameWinningGoals = goalsNode.getData();
            String team = teamNode.getData();
            if (gameWinningGoals > maxGameWinningGoals) {
                maxGameWinningGoals = gameWinningGoals;
                result.setLength(0); // Clear previous results
                result.append(team).append("\n");
            } else if (gameWinningGoals == maxGameWinningGoals) {
                result.append(team).append("\n");
            }
            goalsNode = goalsNode.getNext();
            teamNode = teamNode.getNext();
        }

        return result.toString();
    }

    // Helper method to find index of a team in the linked list
    private int findTeamIndex(LinkedList<String> teams, String team) {
        Node<String> current = teams.getFront();
        int index = 0;
        while (current != null) {
            if (current.getData().equals(team)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1; // Team not found
    }
}
