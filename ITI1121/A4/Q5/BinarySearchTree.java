//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 4

public class BinarySearchTree<E extends Comparable<E>> {
  
  private static class Node<T> {
    
    private final T value;
    private Node<T> left;
    private Node<T> right;
    
    private Node(T value) {
      this.value = value;
    }
  }
  
  private Node<E> root;
  
  
  /**
   * Inserts an object into this BinarySearchTree.
   *
   * @param obj item to be added
   * @return true if the object has been added and false otherwise
   */
  
  public boolean add(E obj) {
    
    // pre-condition:
    if (obj == null) {
      throw new NullPointerException("Illegal Argument");
    }
    
    // special case:
    if (root == null) {
      root = new Node<E>(obj);
      return true;
    }
    
    // general case:
    return add(obj, root);
  }
  //Private recursive method
  private boolean add(E obj, Node<E> current) {
    
    boolean result;
    int test = obj.compareTo(current.value);
    
    if (test == 0) {
      result = false; // already exists, not added
    } else if (test < 0) {
      if (current.left == null) {
        current.left = new Node<E>(obj);
        result = true;
      } else {
        result = add(obj, current.left);
      }
    } else {
      if (current.right == null) {
        current.right = new Node<E>(obj);
        result = true;
      } else {
        result = add(obj, current.right);
      }
    }
    return result;
  }
  
  
  /**
   * Counts the number of elements in the tree that are greater than or equal to low 
   * and smaller than or equal to high 
   * @param low - lowest value in tree
   * @param high - highest value in tree
   * @return the number of elems >= low or <= high
   */
  public int count(E low, E high) {
    
    if (low == null || high == null) {
      throw new IllegalArgumentException("null");
    }
    
    return countRecursive(root, low, high);   
  }
  
  
  private int countRecursive(Node<E> current, E low, E high) {
    
    int count = 0;
    
    // Base case: tree is empty
    if (current == null) {
      return count;
      
    } else {
      
      if (low.compareTo(current.value) <= -1 && high.compareTo(current.value) >= 1) {
        count++;
      }
      
      if (low.compareTo(current.value) == 0 || high.compareTo(current.value) == 0) {
        count++;
      }
    }
    count = count + countRecursive(current.left, low, high) + countRecursive(current.right, low, high);
    return count;
  }
  
  
  
  @Override
  public String toString() {
    return toString(root);
  }
  
  private String toString(Node<E> p) {
    if (p == null) {
      return "null";
    } else {
      return "(" + toString(p.left) + "," + p.value + "," + toString(p.right) + ")";
    }
  }
}
