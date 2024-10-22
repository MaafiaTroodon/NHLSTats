import java.io.*;
import java.util.StringTokenizer;

public class NHLStats {
    private LinkedList<PlayerRecord> players;

    public NHLStats() {
        players = new LinkedList<>();
    }

    public void add(PlayerRecord player) {
        players.add(player);
    }

    public void readFromFile(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
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

            PlayerRecord player = new PlayerRecord(name, position, team, gamesPlayed, goals, assists, penaltyMinutes, shotsOnGoal, gameWinningGoals);
            add(player);
        }

        reader.close();
    }

    // Method to find players with the highest points (goals + assists)
    public String findPlayerWithMostPoints() {
        Node<PlayerRecord> current = players.getFront();
        int maxPoints = 0;
        StringBuilder result = new StringBuilder();

        // Loop to find the highest points
        while (current != null) {
            PlayerRecord player = current.getData();
            int totalPoints = player.getTotalPoints();
            if (totalPoints > maxPoints) {
                maxPoints = totalPoints;
                result.setLength(0); // Reset result
                result.append(player.getName()).append(" (").append(player.getTeam()).append(")\n");
            } else if (totalPoints == maxPoints) {
                result.append(player.getName()).append(" (").append(player.getTeam()).append(")\n");
            }
            current = current.getNext();
        }

        return result.toString();
    }

    // Method to find the most aggressive player (highest penalty minutes)
    public String findMostAggressivePlayer() {
        Node<PlayerRecord> current = players.getFront();
        int maxPenaltyMinutes = 0;
        StringBuilder result = new StringBuilder();

        // Loop to find the player(s) with the most penalty minutes
        while (current != null) {
            PlayerRecord player = current.getData();
            int penaltyMinutes = player.getPenaltyMinutes();
            if (penaltyMinutes > maxPenaltyMinutes) {
                maxPenaltyMinutes = penaltyMinutes;
                result.setLength(0); // Reset result
                result.append(player.getName()).append(" (").append(player.getTeam()).append(", ").append(player.getPosition()).append(")\n");
            } else if (penaltyMinutes == maxPenaltyMinutes) {
                result.append(player.getName()).append(" (").append(player.getTeam()).append(", ").append(player.getPosition()).append(")\n");
            }
            current = current.getNext();
        }

        return result.toString();
    }

    // Method to find the MVP (player with the most game-winning goals)
    public String findMVP() {
        Node<PlayerRecord> current = players.getFront();
        int maxGameWinningGoals = 0;
        StringBuilder result = new StringBuilder();

        // Loop to find the player(s) with the most game-winning goals
        while (current != null) {
            PlayerRecord player = current.getData();
            int gameWinningGoals = player.getGameWinningGoals();
            if (gameWinningGoals > maxGameWinningGoals) {
                maxGameWinningGoals = gameWinningGoals;
                result.setLength(0); // Reset result
                result.append(player.getName()).append(" (").append(player.getTeam()).append(")\n");
            } else if (gameWinningGoals == maxGameWinningGoals) {
                result.append(player.getName()).append(" (").append(player.getTeam()).append(")\n");
            }
            current = current.getNext();
        }

        return result.toString();
    }

    // Method to find the most promising player (player with the most shots on goal)
    public String findMostPromisingPlayer() {
        Node<PlayerRecord> current = players.getFront();
        int maxShotsOnGoal = 0;
        StringBuilder result = new StringBuilder();

        // Loop to find the player(s) with the most shots on goal
        while (current != null) {
            PlayerRecord player = current.getData();
            int shotsOnGoal = player.getShotsOnGoal();
            if (shotsOnGoal > maxShotsOnGoal) {
                maxShotsOnGoal = shotsOnGoal;
                result.setLength(0); // Reset result
                result.append(player.getName()).append(" (").append(player.getTeam()).append(")\n");
            } else if (shotsOnGoal == maxShotsOnGoal) {
                result.append(player.getName()).append(" (").append(player.getTeam()).append(")\n");
            }
            current = current.getNext();
        }

        return result.toString();
    }

    // Method to find the team with the most penalty minutes
    public String findTeamWithMostPenaltyMinutes() {
        LinkedList<String> teams = new LinkedList<>();
        LinkedList<Integer> penaltyMinutesPerTeam = new LinkedList<>();
        Node<PlayerRecord> current = players.getFront();

        // Loop to calculate total penalty minutes for each team
        while (current != null) {
            PlayerRecord player = current.getData();
            String team = player.getTeam();
            int penaltyMinutes = player.getPenaltyMinutes();

            int index = findTeamIndex(teams, team);
            if (index == -1) {
                teams.add(team);
                penaltyMinutesPerTeam.add(penaltyMinutes);
            } else {
                penaltyMinutesPerTeam.setAt(penaltyMinutesPerTeam.getAt(index) + penaltyMinutes, index);
            }

            current = current.getNext();
        }

        return getTeamWithMaxStat(teams, penaltyMinutesPerTeam);
    }

    // Method to find the team with the most game-winning goals
    public String findTeamWithMostGameWinningGoals() {
        LinkedList<String> teams = new LinkedList<>();
        LinkedList<Integer> gameWinningGoalsPerTeam = new LinkedList<>();
        Node<PlayerRecord> current = players.getFront();

        // Loop to calculate total game-winning goals for each team
        while (current != null) {
            PlayerRecord player = current.getData();
            String team = player.getTeam();
            int gameWinningGoals = player.getGameWinningGoals();

            int index = findTeamIndex(teams, team);
            if (index == -1) {
                teams.add(team);
                gameWinningGoalsPerTeam.add(gameWinningGoals);
            } else {
                gameWinningGoalsPerTeam.setAt(gameWinningGoalsPerTeam.getAt(index) + gameWinningGoals, index);
            }

            current = current.getNext();
        }

        return getTeamWithMaxStat(teams, gameWinningGoalsPerTeam);
    }

    // Helper method to get the team with the maximum stat
    private String getTeamWithMaxStat(LinkedList<String> teams, LinkedList<Integer> stats) {
        int maxStat = 0;
        StringBuilder result = new StringBuilder();

        Node<Integer> statNode = stats.getFront();
        Node<String> teamNode = teams.getFront();

        while (statNode != null && teamNode != null) {
            int currentStat = statNode.getData();
            String currentTeam = teamNode.getData();

            if (currentStat > maxStat) {
                maxStat = currentStat;
                result.setLength(0); // Reset result
                result.append(currentTeam).append("\n");
            } else if (currentStat == maxStat) {
                result.append(currentTeam).append("\n");
            }

            statNode = statNode.getNext();
            teamNode = teamNode.getNext();
        }

        return result.toString();
    }

    // Helper method to find the index of a team in the list
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
