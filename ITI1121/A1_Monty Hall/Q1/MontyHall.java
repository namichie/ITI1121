import java.util.Random;
/**
 * The class <b>MontyHall</b> simulates one game. Is uses three <b>Door</b> objects
 * to simulate the three doors. One game consists of the following steps
 * <ol>
 * <li>Resets all three doors</li>
 * <li>Simulates the selection of one of the doors by the player</li>
 * <li>Simulates opening of an empty door by the host</li>
 * <li> provide the outcome for switching and not switching door</li>
 * </ol>
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

// Authors: NamChi Nguyen & Zhengguo Wang    
// Student number: 7236760 & 7278242  
// Course: ITI 1121-A
// Assignment: 1
// Question: 1

public class MontyHall {

  //Instance variables   
  private Door door1;
  private Door door2;
  private Door door3;
  private Door prizeDoor;
  
 /** 
     * Initializes the three doors.
     */
  public MontyHall(){
    door1 = new Door("A");
    door2 = new Door("B");
    door3 = new Door("C");
    
  }
 
 /** 
     * Simulates one Monty Hall game.  
     * <ol>
     * <li>Resets all three doors</li>
     * <li>Simulates the selection of one of the doors by the player</li>
     * <li>Simulates opening of an empty door by the host</li>
     * <li>prints the outcome for switching and not switching door to standard output</li>
     * </ol>
     */
  public void oneGame(){

   //Resets the 3 doors
   door1.reset();
   door2.reset(); 
   door3.reset(); 
   
   //Sets a prize randomly behind a door
   prizeDoor = pickADoor();
   prizeDoor.setPrize();
   System.out.println("The prize was behind door " + prizeDoor.getName()); 
   
   //Player randomly selects a door
   Door playerDoor = pickADoor();
   System.out.println("The player selected door " + playerDoor.getName());
   
   //Host selects an empty door
   Door hostDoor = openOtherDoor(prizeDoor, playerDoor);
   System.out.println("The host opened door " + hostDoor.getName()); 
   
   //Prints the outcome for switching and not switching the door
   if(prizeDoor.getName().equals(playerDoor.getName())){
   System.out.println("Switching strategy would have lost");
  } else{
   System.out.println("Switching strategy would have won"); 
  } 
 } 

 /** 
     * Simulates a random selection of one of the three doors.
     * @return the door randomly selected  
     */
  private Door pickADoor(){
    Random rand = new Random();  
    float choice = rand.nextFloat();
    if (choice <=0.33) {
      return door1;
    } else if (choice <=0.67) {
      return door2;
    } else {
      return door3;
    } 
 } 
 
/** 
     * Simulates the opening of one of the other doors once the player selected one.
     * It should open a door chosen randomly among the ones that don't have the prize and
     * that are not selected by the player
     * 
     *   @param prizeDoor the door that hides the prize
     *   @param selectedDoor the door that was selected by the player
     *   @return the door opened
     */
  private Door openOtherDoor(Door prizeDoor, Door selectedDoor){

    if (! door1.getName().equals(prizeDoor.getName()) && ! door1.getName().equals(selectedDoor.getName())) {
      return door1;
    } else if (! door2.getName().equals(prizeDoor.getName()) && ! door2.getName().equals(selectedDoor.getName())) {
        return door2;
    } else {
      return door3;
    }
 }
 
  /**
     * The main method of this program. Examples of the execution of the program
     * from the command line:
     * <pre>
     * > java MontyHall
     * The prize was behind door B
     * The player selected door B
     * The host opened door C
     * Switching strategy would have lost
     * </pre>
     * <pre>
     * > java MontyHall
     * The prize was behind door B
     * The player selected door A
     * The host opened door C
     * Switching strategy would have won
     * </pre>
     * @param args ignored for now
  */
 public static void main(String[] args) {
  MontyHall montyHall;
  StudentInfo.display();
  montyHall = new MontyHall();  
  montyHall.oneGame();
 }

}
