//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 3

/** 
 * The interface <b>Queue<b> is an ADT.
 * It follows first-in-first-out (FIFO) protocol.
 * i.e. the first element that has been added onto
 * the Queue, is the first one to be removed.
 */
public interface Queue<E> {
	public boolean isEmpty();

	public void enqueue(E o);

	public E dequeue();
}
