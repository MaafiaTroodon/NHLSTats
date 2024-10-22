# NHL Stats Assignment

## Author: Malhar Datta Mahajan

## Overview

This project analyzes NHL (National Hockey League) player statistics by processing a file (`nhlstats.txt`) that contains detailed player data.
The application calculates various key statistics, such as the players with the highest points, most aggressive players, MVPs, and teams with the most game-winning goals and penalty minutes.
The results are written to an output file (`nhlstatsoutput.txt`).

## How It Works

The program reads and processes player data from the input file. The data includes:

- Player Name
- Position (e.g., C for Center, RW for Right Wing)
- Team Name
- Games Played
- Goals Scored
- Assists
- Penalty Minutes
- Shots on Goal
- Game-Winning Goals

After reading this data, the program computes statistics and writes the findings to the output file in a structured format.

## Main Features

### **Players with the Highest Points:**
- Calculates the players with the highest combined points (goals + assists) and lists their names and teams.

### **Most Aggressive Players:**
- Finds players with the most penalty minutes, displaying their names, teams, and positions.

### **Most Valuable Players (MVPs):**
- Identifies players with the most game-winning goals, along with their team names.

### **Most Promising Players:**
- Shows the players who had the most shots on goal, listing their names and teams.

### **Teams with the Most Penalty Minutes:**
- Sums the penalty minutes of all players on each team to find the team(s) with the highest total penalty minutes.

### **Teams with the Most Game-Winning Goals:**
- Sums the game-winning goals across all players on each team to determine which team had the most.

## Input File Structure

The input file (`nhlstats.txt`) should be tab-separated and must contain the following columns for each player:

- **Player Name**
- **Position** (e.g., C for Center, RW for Right Wing)
- **Team Name**
- **Games Played**
- **Goals**
- **Assists**
- **Penalty Minutes**
- **Shots on Goal**
- **Game-Winning Goals**

## Output

The program outputs a summary of the statistics to `nhlstatsoutput.txt`. This includes:

- Players with the highest points
- Most aggressive players
- MVP players
- Most promising players
- Teams with the most penalty minutes
- Teams with the most game-winning goals

## How to Run the Program

1. Place the input file (`nhlstats.txt`) in the same directory as the program.
2. Compile and run the `NHLStatsDemo.java` file.
3. When prompted, enter the name of the input file (e.g., `nhlstats.txt`).
4. The program will generate the results and write them to `nhlstatsoutput.txt` in the same directory.

## How It Works (In Detail)

### Reading the Data:
The program reads each line of the input file using a `BufferedReader`. It splits the line using tabs (`\t`) to separate player attributes such as name, position, and stats. Each player is stored in a `PlayerRecord` object, and all players are added to a `LinkedList`.

### Processing Statistics:
To find the desired statistics, the program loops through the list of players, comparing and calculating totals like points, penalty minutes, and game-winning goals.

### Writing the Results:
After calculating all statistics, the results are written to an output file (`nhlstatsoutput.txt`) using a `BufferedWriter`. This includes all the required summaries in a structured, readable format.
