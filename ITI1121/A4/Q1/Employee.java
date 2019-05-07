//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 4

public class Employee {
  // Instance variables
  private String name;
  private int hours;
  private double rate;
  private Address[] address;
  
  // Constructor
  public Employee(String name, int hours, double rate, Address[] address) {
    this.name = name;
    this.hours = hours;
    this.rate = rate;
    this.address = address;
  }
  
  /**
   * Getter method of name
   * 
   * @return name
   */
  public String getName() {
    return name;
  }
  
  /**
   * Getter method of hours
   * 
   * @return hours
   */
  public int getHours() {
    return hours;
  }
  
  /**
   * Getter method of rate
   * 
   * @return rate
   */
  public double getRate() {
    return rate;
  }
  
  /**
   * Getter method of address
   * 
   * @return array of addresses
   */
  public Address[] getAddress() {
    return address;
  }
}