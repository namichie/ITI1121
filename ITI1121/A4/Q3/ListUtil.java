//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 4

public class ListUtil<E> {
  
  /**
   * Finds all the positions of the parameter obj in the linked listed list.
   * 
   * @param list - linked list
   * @param obj - elem to be found in the list
   * @return the position of the found elems in the list
   */
  public static <E> LinkedList<Integer> indexOfAll(LinkedList<E> list, E obj) {
    
    // Pre-conditions
    if (obj == null) {
      throw new NullPointerException("obj is null");
    }
    
    if (list.size() == 0) {
      throw new IllegalArgumentException("list is empty");
    }
    
    Iterator<E> i = list.iterator();
    
    LinkedList<Integer> position = new LinkedList<Integer>();
    int a = 0;
    
    while (i.hasNext()) {
      if (i.next() == obj) {
        position.addLast(a);
      }
      a++;
    }
    return position;
  }
}
