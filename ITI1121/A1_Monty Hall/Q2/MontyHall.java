import javax.swing.JOptionPane;
import java.util.Random;

/**
 * The class <b>MontyHall</b> simulates one or several games. Is uses three
 * <b>Door</b> objects to simulate the three doors. One game consists of the
 * following steps
 * <ol>
 * <li>Resets all three doors</li>
 * <li>Simulates the selection of one of the doors by the player</li>
 * <li>Simulates opening of an empty door by the host</li>
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
// Question: 2

public class MontyHall {
 // Instance variables
 private Door door1;
 private Door door2;
 private Door door3;

 /**
  * Initializes the three doors.
  */
 public MontyHall() {
  door1 = new Door("A");
  door2 = new Door("B");
  door3 = new Door("C");
 }

 /**
  * Runs a series of Monty Hall games and displays the resulting statistics
  * in a message dialog or on the standard output
  * 
  * @param numberOfGames
  *            the number of games to simulate
  * @param commandLine
  *            if true, sends the results to standard output, else uses a
  *            message dialog
  */
 public void runGames(int numberOfGames, boolean commandLine) {
  Statistics stats = new Statistics();

  stats.numberOfGames = numberOfGames;

  for (int i = 0; i < numberOfGames; i++) {
   this.oneGame(stats);
  }

  if (commandLine) {
   System.out.println(stats.toString());
  } else {
   JOptionPane.showMessageDialog(null, stats.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);
  }
 }

 /**
  * Simulates one Monty Hall game.
  * <ol>
  * @param stats contains the statistics of Monty Hall
  * <li>Resets all three doors</li>
  * <li>Simulates the selection of one of the doors by the player</li>
  * <li>Simulates opening of an empty door by the host</li>
  * <li>prints the outcome for switching and not switching door to standard
  * output</li>
  * </ol>
  */
 public void oneGame(Statistics stats) {
  // Resets all 3 doors
  door1.reset();
  door2.reset();
  door3.reset();

  // Sets a prize randomly behind a door
  Door prizeDoor = pickADoor();
  prizeDoor.setPrize();

  // Player randomly selects a door
  Door playerDoor = pickADoor();
  playerDoor.selected = true;

  // Host selects an empty door
  Door hostDoor = openOtherDoor(prizeDoor, playerDoor);
  hostDoor.closed = false;

  // Increments the number of times the switching strategy was successful
  // and unsuccessful
  if (prizeDoor.getName().equals(playerDoor.getName())) {
   stats.numberOfStayWins++;
  } else {
   stats.numberOfSwitchWins++;
  }
  stats.updateStatistics(door1, door2, door3);
 }

 /**
  * Simulates a random selection of one of the three doors.
  * 
  * @return the door randomly selected
  */
 private Door pickADoor() {
  Random rand = new Random();
  float choice = rand.nextFloat();
  if (choice <= 0.33) {
   return door1;
  } else if (choice <= 0.67) {
   return door2;
  } else {
   return door3;
  }
 }

 /**
  * Simulates the opening of one of the other doors once the player selected
  * one. It should open a door chosen randomly among the ones that don't have
  * the prize and that are not selected by the player
  * 
  * @param prizeDoor
  *            the door that hides the prize
  * @param selectedDoor
  *            the door that was selected by the player
  * @return the door opened
  */
 private Door openOtherDoor(Door prizeDoor, Door selectedDoor) {
  if (!door1.getName().equals(prizeDoor.getName()) && !door1.getName().equals(selectedDoor.getName())) {
   return door1;
  } else if (!door2.getName().equals(prizeDoor.getName()) && !door2.getName().equals(selectedDoor.getName())) {
   return door2;
  } else {
   return door3;
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
  boolean commandLine = true;
  StudentInfo.display();
  if (args.length == 1) {
   numberOfGames = Integer.parseInt(args[0]);
   commandLine = true;
  } else {
   numberOfGames = Integer.parseInt(JOptionPane.showInputDialog("Input the number of games to play", "1000"));
  }
  montyHall = new MontyHall();
  montyHall.runGames(numberOfGames, commandLine);
 }

}
