//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 4

public class Test {
  
  public static void main(String[] args) {
    StudentInfo.display();
    
    // General case
    System.out.println("General case:");
    BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>();
    
    bt.add(1);
    bt.add(2);
    bt.add(3);
    bt.add(4);
    bt.add(5);
    bt.add(6);
    bt.add(7);
    bt.add(8);
    
    System.out.println("tree: " + bt);
    System.out.println("count: " + bt.count(3, 6));
    
    System.out.println("Special cases:");
    System.out.println("a) Empty tree:");
    // Empty tree
    BinarySearchTree<Integer> empty = new BinarySearchTree<Integer>();
    System.out.println("tree: " + empty);
    System.out.println("count: " + empty.count(3, 6));
    
    System.out.println("b) Negative numbers:");
    // Negative number
    BinarySearchTree<Integer> negativeNum = new BinarySearchTree<Integer>();
    negativeNum.add(-4);
    negativeNum.add(1);
    negativeNum.add(-7);
    negativeNum.add(5);
    negativeNum.add(2);
    System.out.println("tree: " + negativeNum);
    System.out.println("count: " + negativeNum.count(3, 6));
    
    System.out.println("c) Same numbers:");
    // Two numbers are the same
    BinarySearchTree<Integer> sameNum = new BinarySearchTree<Integer>();
    sameNum.add(5);
    sameNum.add(1);
    sameNum.add(1);
    sameNum.add(4);
    sameNum.add(2);
    System.out.println("tree: " + sameNum);
    System.out.println("count: " + sameNum.count(0, 2));
    
    System.out.println("d)low and high are null:");
    bt = new BinarySearchTree<Integer>();
    
    bt.add(1);
    bt.add(2);
    try {
      bt.count(null, null);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
      
    }
  }
}
