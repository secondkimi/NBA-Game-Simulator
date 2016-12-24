package NBADemo;
import java.util.*;
/*
 * this class helps to update the game time string as the game processes.
 * my would put the latest string to the front and remove the oldest string
 *  basic simulation: 
 * 0     1     2    3
 * a --> b --> c    null    we add(d), we have:
 * 0     1     2     3
 * d --> a --> b --> c then we remove(), the oldest element, c will be removed. we have:
 * 0     1     2     3
 * d --> a --> b --> null
 * the capacity of the queue is fixed. the queue will not be able to remove when the size is 0, and when
 * the size is full and we call add(element e), we will automatically remove the oldest element and add
 * the element e to the front.
 * Therefore, the element at smaller index are newer than the element at greater index
 */

public class MyQueue<E extends Object> {

	private E[] queue;
	// default capacity
	private int capacity;

	private int size;

	private int front;

	private int rear;

	/** Constructor */
	public MyQueue() {
		// default capacity
		capacity = 10;
		queue = (E[]) new Object[capacity];
		front = 0;
		rear = 0;
		size = 0;
	}
	/** Constructor with one param */
	public MyQueue(int c) {
		capacity = c;
		queue = (E[]) new Object[capacity];
		front = 0;
		rear = 0;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean isFull() {
		return size() == capacity;
	}

	// add to the front
	public void add(E item) {
		if ( isFull() ) {
			remove();
		}

		if ( isEmpty() ) {
			queue[front] = item;
 		}
 		else {
 			// the size is neither 0 nor full
 			E[] newQueue = (E[]) new Object[capacity];
 			for (int i = 0; i < size(); i++) {
 				newQueue[i+1] = queue[i];
 			}
 			newQueue[0] = item;
			queue = newQueue;
			front = 0; 
			rear++;
		}
 		size++;
	}

	// remove from the rear
	public E remove() {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		E toRemove = queue[rear];
		queue[rear] = null;
		rear = (rear - 1);
		if ( rear < 0 ) {
			rear = 0;
		}

		size--;
		return toRemove;
	}

	// get the item at the index idx
	public E getItem(int idx) {
		if ( idx >= size() ) {
			return null;
		}
		return queue[idx];
	}

}