import java.text.DecimalFormat;

public class PlayerRecord {
    // Attributes corresponding to the columns in nhlstats.txt
    private String name;
    private String position;
    private String team;
    private int gamesPlayed;
    private int goals;
    private int assists;
    private int penaltyMinutes;
    private int shotsOnGoal;
    private int gameWinningGoals;

    // Decimal format for output
    private static final DecimalFormat numberFormat = new DecimalFormat("0");

    // Constructor
    public PlayerRecord(String name, String position, String team, int gamesPlayed, int goals, int assists,
                        int penaltyMinutes, int shotsOnGoal, int gameWinningGoals) {
        this.name = name;
        this.position = position;
        this.team = team;
        this.gamesPlayed = gamesPlayed;
        this.goals = goals;
        this.assists = assists;
        this.penaltyMinutes = penaltyMinutes;
        this.shotsOnGoal = shotsOnGoal;
        this.gameWinningGoals = gameWinningGoals;
    }

    // Getters for attributes
    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getTeam() {
        return team;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getPenaltyMinutes() {
        return penaltyMinutes;
    }

    public int getShotsOnGoal() {
        return shotsOnGoal;
    }

    public int getGameWinningGoals() {
        return gameWinningGoals;
    }

    // Calculate total points (goals + assists)
    public int getTotalPoints() {
        return goals + assists;
    }

    // Override toString method for formatted output
    @Override
    public String toString() {
        return "Name: " + name + ", Position: " + position + ", Team: " + team +
                ", Games Played: " + numberFormat.format(gamesPlayed) +
                ", Goals: " + numberFormat.format(goals) +
                ", Assists: " + numberFormat.format(assists) +
                ", Penalty Minutes: " + numberFormat.format(penaltyMinutes) +
                ", Shots on Goal: " + numberFormat.format(shotsOnGoal) +
                ", Game Winning Goals: " + numberFormat.format(gameWinningGoals);
    }

    // Equals method to compare player records
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PlayerRecord other = (PlayerRecord) obj;
        return name.equals(other.name) && position.equals(other.position) && team.equals(other.team);
    }
}
