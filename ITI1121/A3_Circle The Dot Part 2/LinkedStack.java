//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 3

/** 
 * The class <b>LinkedStack<E><b> is a helper class.
 * It implements the interface Stack.
 */

public class LinkedStack<E> implements Stack<E> {

	private static class Elem<T> {
		private T info;
		private Elem<T> next;

		private Elem(T info, Elem<T> next) {
			this.info = info;
			this.next = next;
		}
	}

	private Elem<E> top; // instance variable

	public LinkedStack() {
		top = null;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public void push(E info) {
		// Precondition
		if (info == null) {
			throw new IllegalArgumentException();
		}

		top = new Elem<E>(info, top);
	}

	public E peek() {
		// Precondition
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		return top.info;
	}

	public E pop() {

		E savedInfo = peek();

		Elem<E> oldTop = top;
		Elem<E> newTop = top.next;

		top = newTop;

		oldTop.info = null; // scrubbing the memory
		oldTop.next = null; // scrubbing the memory

		return savedInfo;
	}
	
}
