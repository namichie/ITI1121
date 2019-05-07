//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 4

/** ITI 1121/1521. Introduction to Computer Science II
  * Assignment/Devoir 4
  *
  * Marcel Turcotte
  */

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {
  
  private static class Node<T> {
    private T value;
    private Node<T> next;
    
    private Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }
  }
  
  // Instance variables
  
  private Node<E> head;
  
  /**
   * Reference to the size of the list
   */
  private int size;
  
  /**
   * Constructor
   * Initializes the size of the linked list
   */
  public SinglyLinkedList() {
    size = 0;
  }
  
  // ----------------------------------------------------------
  // SinglyLinkedList methods
  // ----------------------------------------------------------
  
  public void addFirst(E item) {
    if (item == null) {
      throw new NullPointerException("Illegal argument");
    }
    
    head = new Node<E>(item, head);
    size++;
  }
  
  public boolean isEmpty() {
    return head == null;
  }
  
  /**
   * Gets the size of the list
   */
  public int getSize() {
    return size;
  }
  
  /**
   * Recursive instance method
   * 
   * @param n - number of elements in the list
   * @return a new linked list containing the first n elements
   */
  // Public method
  public SinglyLinkedList<E> take(int n) { 
    
    // Pre-conditions
    if (n < 0) {
      throw new IllegalArgumentException("Invalid input: the integer must be positive");
    }
    
    if (n > getSize()) {
      throw new NoSuchElementException("The number of elems to take are greater than the list size");
    }
    
    return takeRecursive(head, n); // initiates the first recursive call
  }
  
  // Private recursive method
  private SinglyLinkedList<E> takeRecursive(Node<E> p, int n) { 
    
    SinglyLinkedList<E> lst = new SinglyLinkedList<E>();
    
    // Base case - taking no elems
    if (n == 0) {
      return lst;
    }
    
    // Recursive function
    lst = takeRecursive(p.next, n - 1); // since index starts at 0
    lst.addFirst(p.value);
    
    return lst;
  }
  
  // ----------------------------------------------------------
  // Other instance method
  // ----------------------------------------------------------
  
  @Override
  public String toString() {
    return "{" + toString(head) + "}";
  }
  
  private String toString(Node<E> p) {
    
    String result = "";
    
    if (p != null) {
      result = p.value.toString();
      if (p.next != null) {
        result = result + "," + toString(p.next);
      }
    }
    
    return result;
  }
}
