/**
 * Queue Class
 * --------------------------------------------------------------------------------------------------------------------
 * Code Adapted From:
 * --------------------------------------------------------------------------------------------------------------------
 * Title: LinkedListQueue.java
 * Author: Mobin Vahdati
 * --------------------------------------------------------------------------------------------------------------------
 * @param <T>
 */

public class Queue<T> {
    private SinglyLinkedList<T> queue;
    private int size;

    /**
     * Constructor
     */
    public Queue() {
        queue = new SinglyLinkedList<T>();
        this.size = 0;
    }

    /**
     * Inserts an element at the back of the queue
     * @param data
     */
    public void enqueue(T data) {
        size++;
        queue.addLast (data);
    }
    /**
     * Removes and returns the first element in the queue
     * @return
     */
    public T dequeue() {
        size--;
       return queue.remove (0);
    }
    /**
     * Checks if the queue is empty or not
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Returns the elements in the queue in string format.
     */
    @Override
    public String toString() {
        return queue.toString();
    }
}
