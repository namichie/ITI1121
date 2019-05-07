/**
 * The class <b>Door</b> stores the information about one of the door: 
 * does it have the prize behind it? Is it open or closed? Was it 
 * selected by the player?
 * 
 * It provides other objects access to these information through some
 * <b>setters</b> and <b>getters</b>.
 * 
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

// Authors: NamChi Nguyen & Zhengguo Wang    
// Student number: 7236760 & 7278242  
// Course: ITI 1121-A
// Assignment: 1
// Question: 4

public class Door {

//Instance variables  
 private String name;
 private String prize;
 protected boolean closed;
 protected boolean selected;
 
 /** 
     * Creates an instance of the Door object.
     * Initially, the door is closed, doesn't have a prize behind it 
     * and has not been chosen by the player.
     * 
     * @param name identifier for that door
     */
 public Door(String name){
   this.name = name;
   this.prize = null;
   this.closed = true;
   this.selected = false;
 }

 /** 
     * Resets the door to its initial state: closed, without a prize behind it 
     * and not chosen by the player.
     */
 public void reset(){
   this.prize = null;
   this.closed = true;
   this.selected = false;
 }
 
 /** 
     * Sets this door open.
     */
 public void open(){
 this.closed = false;
 }
 
 /** 
     * Checks if the door is open.
     * @return true if the door is open
     */
 public boolean isOpen(){
   if (this.closed == true){
     return false;
   } else {
     return true;
   }
 }
 
 /** 
     * Puts the prize behind this door.
     */
 public void setPrize(){
  this.prize="prize";
   
 }
 
 /** 
     * Checks if the door has the prize.
     * @return true if the door has the prize
     */
 public boolean hasPrize(){
   if (this.prize!=null){
     return true;
   } else {
     return false; 
   }
 }
 
 /** 
     * Sets this door as selected by the player.
     */
 public void choose(){
   this.selected = true;
 }

 /** 
     * Checks if the door is selected by the player.
     * @return true if the door is selected by the player
     */
 public boolean isChosen(){
   if (this.selected == true) {
     return true;
   } else {
     return false;
   }
 }
 
 
 /** 
     * @return the door's identifier
     */
 public String getName(){
   return this.name;
 }
}
