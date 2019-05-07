import javax.swing.JOptionPane;
import java.util.Random;

/**
 * The class <b>MontyHall</b> simulates one or several games. Is uses three
 * <b>Door</b> objects to simulate the three doors. One game consists of the
 * following steps
 * <ol>
 * <li>Resets all doors</li>
 * <li>Simulates the selection of one of the doors by the player</li>
 * <li>Simulates opening of all other (empty) door save one by the host</li>
 * <li>provide the outcome for switching and not switching door</li>
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

public class MontyHall {
  //Instance variable
  private Door[] doorArray;
  
  /**
   * Initializes the list of doors.
   * 
   * @param numberOfDoors
   *            number of door used in the simulation
   */
  public MontyHall(int numberOfDoors) {
    this.doorArray = new Door[numberOfDoors];
    for(int i=0;i<numberOfDoors;i++){
      doorArray[i] = new Door(Integer.toString(i+1));
    }
  }
  
  /**
   * Runs a series of Monty Hall games and displays the resulting statistics
   * in a message dialog or on the standard output
   * 
   * @param numberOfGames
   *            the number of games to simulate
   * @param commandLine
   *            if true, sends the results as CSV to standard output, else
   *            uses a message dialog
   */
  public void runGames(int numberOfGames, boolean commandLine) {
    
    Statistics stats = new Statistics(doorArray.length);
    stats.numberOfGames = numberOfGames;
    
    for (int i = 0; i < numberOfGames; i++) {
      this.oneGame(stats);
    }
    if (commandLine) {
      System.out.println(stats.toCSV(doorArray));
    } else {
      JOptionPane.showMessageDialog(null, stats.toString(doorArray), "Results", JOptionPane.INFORMATION_MESSAGE);
    }
  }
  
  /**
   * Simulates one Monty Hall game.
   * <ol>
   * @param stats contains the statistics of Monty Hall
   * <li>Resets all the doors</li>
   * <li>Simulates the selection of one of the doors by the player</li>
   * <li>Simulates opening of an empty door by the host</li>
   * <li>prints the outcome for switching and not switching door to standard output</li>
   * </ol>
   */
  public void oneGame(Statistics stats) {
    //Resets all doors
    for (int i = 0; i < doorArray.length; i++) {
      doorArray[i].reset();
    }
    // Sets a prize randomly behind a door
    Door prizeDoor = pickADoor(doorArray.length);
    prizeDoor.setPrize();
    
    // Player randomly selects a door 
    Door playerDoor = pickADoor(doorArray.length);
    playerDoor.selected = true;
   
    // Host selects an empty door
    openOtherDoors(prizeDoor, playerDoor);
    
    // Increments the number of times the switching strategy was successful and unsuccessful
    if (prizeDoor.getName().equals(playerDoor.getName())) {
      stats.numberOfStayWins++;
    } else {
      stats.numberOfSwitchWins++;
    }
    stats.updateStatistics(doorArray);
  }
  
  /**
   * Simulates a random selection of one of the doors.
   * @param numberOfDoors
   *            number of door used in the simulation
   * @return the door randomly selected, otherwise null if not found
   */
  private Door pickADoor(int numberOfDoors) {
    Random rand = new Random();
    int choice = rand.nextInt(numberOfDoors);
    for (int i = 0; i < doorArray.length; i++) {
      if (i == choice) {
        return doorArray[i];
      }
    }
    return null;
  }
  
  /**
   * Simulates the opening of numberOfDoors-2 doors once the player selected
   * one. It should open doors chosen randomly among the ones that don't have
   * the prize and that are not selected by the player
   * 
   * @param prizeDoor
   *            the door that hides the prize
   * @param selectedDoor
   *            the door that was selected by the player
   */
  private void openOtherDoors(Door prizeDoor, Door selectedDoor) {
    for (int i = 0; i < doorArray.length; i++) {
      if (!doorArray[i].getName().equals(prizeDoor.getName())
            && !doorArray[i].getName().equals(selectedDoor.getName())) {
        doorArray[i].open();
      }
    }
  }
  
  /**
   * The main method of this program. Examples of the execution of the program
   * from the command line:
   * <pre>
   * > java MontyHall 5 
   * 
   * Number of games played: 5
   * Staying strategy won 2 games (40%)
   * Switching strategy won 3 games (60%)
   * Selected doors:
   *  door 1: 1 (20%)
   *  door 2: 3 (60%)
   *  door 3: 1 (20%)
   * Winning doors:
   *  door 1: 1 (20%)
   *  door 2: 1 (20%)
   *  door 3: 3 (60%)
   * Open doors:
   *  door 1: 2 (40%)
   *  door 2: 2 (40%)
   *  door 3: 1 (20%)
   *
   * @param args (optional) the number of games to simulate, and the number of doors to use
   */
  public static void main(String[] args) {
    
    MontyHall montyHall;
    int numberOfGames;
    int numberOfDoors;
    boolean commandLine = true;
    
    StudentInfo.display();
    
    if (args.length == 2) {
      numberOfGames = Integer.parseInt(args[0]);
      numberOfDoors = Integer.parseInt(args[1]);
      commandLine = true;
    } else {
      numberOfGames = Integer.parseInt(JOptionPane.showInputDialog("Input the number of games to play", "1000"));
      numberOfDoors = Integer.parseInt(JOptionPane.showInputDialog("Input the number of doors", "3"));
    }
    
    montyHall = new MontyHall(numberOfDoors);
    montyHall.runGames(numberOfGames, commandLine);
  }
  
}
