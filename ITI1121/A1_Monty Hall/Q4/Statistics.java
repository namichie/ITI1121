/**
 * The class <b>Statistics</b> accumulates information about a series of games:
 * <ol>
 * <li>Number of game played</li>
 * <li>Number of times the switching strategy was successful</li>
 * <li>Number of times the switching strategy was not successful</li>
 * <li>Number of times each door has the prize behind it</li>
 * <li>Number of times each door was chosen by the player</li>
 * <li>Number of times each door was open by the host</li>
 * </ol>
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

// Authors: NamChi Nguyen & Zhengguo Wang    
// Student number: 7236760 & 7278242  
// Course: ITI 1121-A
// Assignment: 1
// Question: 4

public class Statistics {
 protected int numberOfGames;
 protected int numberOfSwitchWins;
 protected int numberOfStayWins;
 private int[] prizeDoor;
 private int[] chosenDoor;
 private int[] openDoor;


/**
  * Initializes the statistics.
  * 
  * @param numberOfDoors
  *            the number of doors used
  */
 public Statistics(int numberOfDoors) {
  this.numberOfGames = 0;
  this.numberOfSwitchWins = 0;
  this.numberOfStayWins = 0;
  this.openDoor = new int[numberOfDoors];
  this.chosenDoor = new int[numberOfDoors];
  this.prizeDoor = new int[numberOfDoors];
 }

 /**
  * Updates statistics after one game.
  * 
  * @param doorArray
  *            the list of Doors used during the game
  */
 public void updateStatistics(Door[] doorArray) {
  for (int i = 0; i < doorArray.length; i++) {
   if (doorArray[i].hasPrize()) {
    prizeDoor[i]++;
   }
   if (doorArray[i].isChosen()) {
    chosenDoor[i]++;
   }
   if (doorArray[i].isOpen()) {
    openDoor[i]++;
   }
  }
 }

/**
  * @return Returns the complete statistics information
  * @param doorArray
  *            the list of Doors used during the game
  * The variable statsInfo contains the statistics of the Monty Hall game
  */
 public String toString(Door[] doorArray) {
  int a = 100 * numberOfStayWins / numberOfGames;
  int b = 100 * numberOfSwitchWins / numberOfGames;

  String statsInfo = "Number of games played: " + numberOfGames + "\nStaying strategy won " + numberOfStayWins
    + " games (" + a + " %)" + "\nSwitching strategy won " + numberOfSwitchWins + " games (" + b + " %)"
    + "\nSelected doors:";
  for (int i = 0; i < doorArray.length; i++) {
   int c = 100 * chosenDoor[i] / numberOfGames;
   int d = i + 1;
   statsInfo += "\n door " + d + " : " + chosenDoor[i] + " (" + c + " %)";
  }
  statsInfo += "\nWinning doors:";
  for (int i = 0; i < doorArray.length; i++) {
   int e = 100 * prizeDoor[i] / numberOfGames;
   int f = i + 1;
   statsInfo += "\n door " + f + " : " + prizeDoor[i] + " (" + e + " %)";
  }
  statsInfo += "\nOpen doors:";
  for (int i = 0; i < doorArray.length; i++) {
   int g = 100 * prizeDoor[i] / numberOfGames;
   int h = i + 1;
   statsInfo += "\n door " + h + " : " + openDoor[i] + " (" + g + " %)";
  }
  return statsInfo;
 }

 /**
  * @return Returns the complete statistics information in CSV format
  * @param doorArray
  *            the list of Doors used during the game
  * The variable statsInfoCSV returns the statistics in CSV format
  */
 public String toCSV(Door[] doorArray) {
  String statsInfoCSV = "Number of games," + numberOfGames + "\nNumber of doors," + doorArray.length + "\n,Win,Loss" + "\nStaying strategy,"
    + numberOfStayWins + "," + numberOfSwitchWins + "\nSwitching strategy," + numberOfSwitchWins + ","
    + numberOfStayWins + "\n,Selected doors,Winning doors,Open doors";
  for (int i = 0; i < doorArray.length; i++) {
   int b = i + 1;
   statsInfoCSV += "\nDoor " + b + "," + chosenDoor[i] + "," + prizeDoor[i] + "," + openDoor[i];
  }
  return statsInfoCSV;
 }
}
