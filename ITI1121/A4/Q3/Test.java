//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 4

public class Test {
  public static void main(String[] args) {
    StudentInfo.display();
    
    // General case
    System.out.println("General case:");
    LinkedList<String> elems = new LinkedList<String>();
    
    elems.addLast("A");
    elems.addLast("B");
    elems.addLast("A");
    elems.addLast("A");
    elems.addLast("C");
    elems.addLast("D");
    elems.addLast("A");
    
    System.out.println(elems);
    System.out.println(ListUtil.indexOfAll(elems, "A"));
    
    System.out.println();
    System.out.println("Special cases:");
    
    // a) Empty list
    System.out.println("a) Empty list");
    elems = new LinkedList<String>();
    System.out.println(elems);
    System.out.println("Trying indexOfAll(elems,'A')");
    try {
      ListUtil.indexOfAll(elems, "A");
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
    
    
    // b)
    System.out.println();
    System.out.println("b) obj is null");
    System.out.println("Trying indexOfAll(elems, null)");
    try {
      ListUtil.indexOfAll(elems, null);
    } catch (NullPointerException e) {
      System.out.println("Error: " + e.getMessage());
    }
    
    // c)
    System.out.println();
    System.out.println("c) obj is not in the list");
    
    elems.addLast("A");
    System.out.println(elems);
    
    System.out.println("Trying indexOfAll(elems, 'E')");
    ListUtil.indexOfAll(elems, "E");
    System.out.println(ListUtil.indexOfAll(elems, "E"));
    
    
  }
  
}
