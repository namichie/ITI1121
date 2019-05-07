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
// Question: 3

public class Statistics {
  //Instance variables
  protected int numberOfGames;
  protected int numberOfSwitchWins;
  protected int numberOfStayWins;
  private int[] prizeDoor;
  private int[] chosenDoor;
  private int[] openDoor;
  
  /**
   * Initializes the statistics.
   */
  public Statistics() {
    this.numberOfGames = 0;
    this.numberOfSwitchWins = 0;
    this.numberOfStayWins = 0;
    this.openDoor = new int[3];
    this.chosenDoor = new int[3];
    this.prizeDoor = new int[3];
  }
  
  /**
   * Updates statistics after one game.
   * 
   * @param door1
   *            the first door in the game
   * @param door2
   *            the second door in the game
   * @param door3
   *            the third door in the game
   */
  public void updateStatistics(Door door1, Door door2, Door door3) {
    oneDoor(door1, 0);
    oneDoor(door2, 1);
    oneDoor(door3, 2);
  }
  
  /**
   * Updates statistics for one single door.
   * 
   * @param door
   *            the door for which statistics are updated
   * @param index
   *            index of that door (0, 1 or 2)
   */
  private void oneDoor(Door door, int index) {
    if (door.hasPrize()) {
      prizeDoor[index]++;
    }
    if (door.isChosen()) {
      chosenDoor[index]++;
    }
    if (door.isOpen()) {
      openDoor[index]++;
    }
  }
  
  /** 
   *  @return Returns the complete statistics information
   *  The variable statsInfo contains the statistics of the Monty Hall game
   */
  public String toString(){
    int a = 100*numberOfStayWins/numberOfGames;
    int b = 100*numberOfSwitchWins/numberOfGames;
    
    String statsInfo = "Number of games played: "  + numberOfGames + 
      "\nStaying strategy won " + numberOfStayWins + " games ("+ a+" %)" +
      "\nSwitching strategy won " + numberOfSwitchWins + " games ("+ b+" %)" +
      "\nSelected doors:";
    for (int i=0;i<3;i++){
      int c=100*chosenDoor[i]/numberOfGames;
      int d=i+1;
      statsInfo+="\n door "+d+ " : "+chosenDoor[i]+" ("+ c+" %)";
    }
    statsInfo+="\nWinning doors:";
    for (int i=0;i<3;i++){
      int e=100*prizeDoor[i]/numberOfGames;
      int f=i+1;
      statsInfo+="\n door "+f+ " : "+prizeDoor[i]+" ("+ e+" %)"; 
    }
    statsInfo+="\nOpen doors:";
    for (int i=0;i<3;i++){
      int g=100*openDoor[i]/numberOfGames;
      int h=i+1;
      statsInfo+="\n door "+h+ " : "+openDoor[i]+" ("+ g+" %)";
    }
    return statsInfo;
  }
  
  /**
   * @return Returns the complete statistics information in CSV format
   * The variable statsInfoCSV returns the statistics in CSV format
   */
  public String toCSV() {
    String statsInfoCSV = "Number of games," + numberOfGames + "\nNumber of doors,3" + "\n,Win,Loss" + "\nStaying strategy,"
      + numberOfStayWins + "," + numberOfSwitchWins + "\nSwitching strategy," + numberOfSwitchWins + ","
      + numberOfStayWins + "\n,Selected doors,Winning doors,Open doors";
    for (int i = 0; i < 3; i++) {
      int b = i + 1;
      statsInfoCSV += "\nDoor " + b + "," + chosenDoor[i] + "," + prizeDoor[i] + "," + openDoor[i];
    }
    return statsInfoCSV;
  }
}