//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 4

public class Test {
  public static void main(String[] args) {
    StudentInfo.display();
    String name = "Falcao";
    int hours = 40;
    double rate = 15.50;
    Address[] address = new Address[6];
    address[0] = new Address("Queen", 48, "K1P1N2");
    address[1] = new Address("King Edward", 800, "K1N6N5");
    
    Employee o1 = new Employee(name, hours, rate, address);
  }
}
