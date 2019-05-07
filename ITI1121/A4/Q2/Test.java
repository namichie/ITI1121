//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 4

public class Test {
  
  public static void main(String[] args) {
    StudentInfo.display();
    
    // General case
    System.out.println("1) General case (Singleton part of general case):");
    LinkedList<String> xs = new LinkedList<String>();
    LinkedList<String> ys = new LinkedList<String>();
    
    xs.addLast("a");
    xs.addLast("b");
    xs.addLast("c");
    xs.addLast("f");
    System.out.println("xs: " + xs);
    
    ys.addLast("d");
    ys.addLast("e");
    System.out.println("ys: " + ys);
    
    System.out.println("Trying insertAfter('c', ys)");
    xs.insertAfter("c", ys);
    System.out.println("xs after insertAfter: " + xs);
    System.out.println("ys after insertAfter: " + ys);
    
    
    // Special cases
    // a) Empty list
    System.out.println();
    System.out.println("2) Special cases:");
    
    System.out.println("a) Empty list:");
    xs = new LinkedList<String>();
    ys = new LinkedList<String>();
    System.out.println("xs: " + xs);
    
    ys.addLast("d");
    ys.addLast("e");
    System.out.println("ys: " + ys);
    System.out.println("Trying insertAfter('c', ys)");
    try {
      xs.insertAfter("c", ys);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
    
    // b) Null obj
    System.out.println();
    System.out.println("b) Inserting after a null object");
    xs = new LinkedList<String>();
    ys = new LinkedList<String>();
    
    xs.addLast("a");
    System.out.println("xs: " + xs);
    
    ys.addLast("d");
    ys.addLast("e");
    System.out.println("ys: " + ys);
    System.out.println("Trying insertAfter(null, ys)");
    try {
      xs.insertAfter(null, ys);
    } catch (NullPointerException e) {
      System.out.println("Error: " + e.getMessage());
    }
    
    // c) Obj not in list
    System.out.println();
    System.out.println("c) When the object is not in the list:");
    
    xs = new LinkedList<String>();
    ys = new LinkedList<String>();
    
    xs.addLast("a");
    System.out.println("xs: " + xs);
    
    ys.addLast("d");
    ys.addLast("e");
    System.out.println("ys: " + ys);
    System.out.println("Trying insertAfter('g', ys)");
    try {
      xs.insertAfter("g", ys);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
    
  }
}
