//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 3

/**
 * The class <b>LinkedQueue<E><b> is a helper class. It implements the interface
 * Queue.
 */
public class LinkedQueue<E> implements Queue<E> {

	private static class Elem<T> {

		private T value;
		private Elem<T> next;

		private Elem(T value, Elem<T> next) {
			this.value = value;
			this.next = next;
		}
	}

	private Elem<E> front;
	private Elem<E> rear;

	public E peek() {
		// Precondition
		if (isEmpty()) {
			throw new EmptyQueueException();
		}

		return front.value;
	}

	public void enqueue(E o) {
		// Precondition
		if (o == null) {
			throw new IllegalArgumentException();
		}

		Elem<E> newElem;

		newElem = new Elem<E>(o, null);

		if (rear == null) {
			front = newElem;
			rear = newElem;
		} else {
			rear.next = newElem;
			rear = newElem;
		}
	}

	public E dequeue() {
		// Precondition
		if (isEmpty()) {
			throw new EmptyQueueException();
		}

		E result = front.value;

		if (front.next == null) {
			front = null;
			rear = null;
		} else {
			front = front.next;
		}
		return result;
	}

	public boolean isEmpty() {
		return front == null;
	}

}
