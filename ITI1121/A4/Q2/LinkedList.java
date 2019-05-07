//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 4

public class LinkedList<E> {
  
  // Doubly linked inner Node class
  private static class Node<T> {
    
    private T value;
    
    private Node<T> previous;
    private Node<T> next;
    
    private Node(T value, Node<T> previous, Node<T> next) {
      this.value = value;
      this.previous = previous;
      this.next = next;
    }
  }
  
  // Instance variables
  private Node<E> head;
  private int size;
  
  // Constructor
  public LinkedList() {
    // Creating a dummy node in an empty list
    head = new Node<E>(null, null, null); // value, prev, next
    head.next = head.previous = head;
    size = 0;
  }
  
  /**
   * Returns the size of this list.
   *
   * @return the size of this list
   */
  
  public int size() {
    return size;
  }
  
  /**
   * Adds an element at the end of the list. THIS IS A SAMPLE METHOD THAT
   * CANNOT BE USED BY InsertAfter !
   *
   * @param element
   *            - the element to be added.
   * @return true since duplicated values are allowed.
   * @throws NullPointerException
   *             if elem is null.
   */
  
  public boolean addLast(E element) {
    
    if (element == null) {
      throw new NullPointerException();
    }
    
    Node<E> before, after;
    
    before = head.previous;
    after = head;
    
    before.next = new Node<E>(element, before, after);
    after.previous = before.next;
    
    size++;
    
    return true;
  }
  
  
  /**
   * Inserts the content of other after the leftmost occurrence of obj in this
   * list, and the elements are removed from other
   * @param obj - the object of a generic type
   * @param other - linked list of a generic type
   * @throws NullPointerException if obj is null.
   * @throws IllegalArgumentException if obj is not found in the list.
   */
  public void insertAfter(E obj, LinkedList<E> other) {
    
    // Pre-condition
    if (obj == null) {
      throw new NullPointerException("obj is null");
    }
    
    // Assign a pointer to the 1st elem in the list (skips dummy node)
    Node<E> p = head.next;
    
    // Traverse the list
    while (p != head && ! p.value.equals(obj)) { // stopping condition
      p = p.next;
    }
    
    // Post-processing after traversing the list
    if (p == head) { 
      throw new IllegalArgumentException("obj is not found in the list");
    }
    
    Node<E> q = p.next; // head.next.next, will eventually point to last elem in given list
    Node<E> firstOther = other.head.next; // point to 1st elem (head is the dummy node)
    Node<E> lastOther = other.head.previous; // point to last elem
    
    // Checks that the 1st and last elem in list aren't the same
    if (firstOther != lastOther) {
      
      // Link the last elem of the given list to the 1st elem in other 
      p.next = firstOther; 
      firstOther.previous = p; //other.head.next.previous = head.next
      
      // Link the last elem in the other list to the last elem in the given list
      lastOther.next = q;
      q.previous = lastOther; 
      
      // Create an empty list (points to dummy node/itself)
      other.head.next = other.head;
      other.head.previous = other.head;
      
      // Increment the size of the given list
      size += other.size();
      
      // Set the other list to empty
      other.size = 0;
    }
  }
  
  
  /**
   * Prints the linked list in a nice format
   * @return a string representation of the linked list
   */
  @Override
  public String toString() {
    StringBuffer result = new StringBuffer("{");
    Node<E> p = head.next;
    while (p != head) {
      if (p != head.next) {
        result.append(",");
      }
      result.append(p.value);
      p = p.next;
    }
    result.append("}");
    return result.toString();
  }
  
}