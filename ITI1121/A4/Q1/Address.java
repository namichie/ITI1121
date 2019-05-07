//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 4

public class Address {
  
  //Instance variables
  private String street;
  private int number;
  private String postal;
  
  //Constructor
  public Address(String street, int number, String postal) {
    this.street = street;
    this.number = number;
    this.postal = postal;
  }
  
  /**
   * Getter method of street
   * @return street name
   */
  public String getStreet() {
    return street;
  }
  
  /**
   * Getter method of number
   * @return the number
   */
  public int getNumber() {
    return number;
  }
  
  /**
   * Getter method of postal code
   * @return postal code
   */
  public String getPostal() {
    return postal;
  }
  
}