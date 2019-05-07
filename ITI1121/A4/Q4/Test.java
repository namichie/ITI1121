//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 4

import java.util.NoSuchElementException;

public class Test {
  
  public static void main(String[] args) {
    StudentInfo.display();
    
    // General case
    System.out.println("1) General case (Singleton part of general case):");
    SinglyLinkedList<Integer> xs = new SinglyLinkedList<Integer>();
    SinglyLinkedList<Integer> ys = new SinglyLinkedList<Integer>();
    
    for (int i = 1; i < 5; i++) {
      xs.addFirst(i);
    }
    System.out.println("xs: " + xs);
    ys = xs.take(2);
    System.out.println("New list after take(2): " + ys);
    
    
    // Special cases
    System.out.println();
    System.out.println("2) Special cases:");
    
    // Take no elems
    System.out.println("a) n = 0:");
    xs = new SinglyLinkedList<Integer>();
    System.out.println("Taking 0 elems from the list:");
    for (int i = 1; i < 5; i++) {
      xs.addFirst(i);
    }
    System.out.println("xs: " + xs);
    ys = xs.take(0);
    System.out.println("New list after take(0): " + ys);
    
    
    // b) Elem not in the list 
    System.out.println();
    System.out.println("b) n > size (empty list is part of this case):");
    xs = new SinglyLinkedList<Integer>();
    
    for (int i = 1; i < 5; i++) {
      xs.addFirst(i);
    }
    System.out.println("xs: " + xs);
    System.out.println("Trying take(10)");
    try {
      xs.take(10);
    } catch (NoSuchElementException e) {
      System.out.println("Error: " + e.getMessage());
    }
    
    // c) n is negative
    System.out.println();
    System.out.println("c) n < 0:");
    xs = new SinglyLinkedList<Integer>();
    
    for (int i = 1; i < 5; i++) {
      xs.addFirst(i);
    }
    System.out.println("xs: " + xs);
    System.out.println("Trying take(-1)");
    try {
      xs.take(-1);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
