/**
 * Singly Linked List Class
 * --------------------------------------------------------------------------------------------------------------------
 * Code Adapted From:
 * --------------------------------------------------------------------------------------------------------------------
 * Title: LinkedList.java and Node.java
 * Author: Xi Wang
 * --------------------------------------------------------------------------------------------------------------------
 *
 * @param <T>
 */

class ListNode<T> {
    T data; // Data stored in the node
    ListNode<T> next; // Reference to the next node in the list

    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }

    public ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;
    }
}
public class SinglyLinkedList<T> {
     ListNode<T> head; // Head of the list

    /**
     * Returns true if the list is empty
     *
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Adds the given element at the end of the list
     *
     * @param data
     */
    public void addLast(T data) {
        ListNode<T> node = new ListNode<T>(data);
        ListNode<T> current = head;

        if (isEmpty()) {
            head = new ListNode<T>(null, node);
            return;
        }

        while (current.next != null) {
            current = current.next;
        }

        current.next = node;
    }

    /**
     * Removes the element at the specified index
     * @param index
     */


    public T remove(int index) {
        ListNode<T> current = head;

        for (int i = 0; i < index && current != null; i++) {
            current = current.next;
        }

        if (current != null) {
            ListNode<T> element = current.next;
            current.next = current.next.next;
            if (head.next == null) {
                head = null;
            }
            return element.data;
        } else {
            throw new IndexOutOfBoundsException ();
        }
    }

    /**
     * Returns the elements in the list in string format.
     */
    @Override
    public String toString() {
        if (isEmpty ()) {
            return "";
        }

        String str = "";
        while (head.next != null) {
            str += head.next.data.toString () + ", ";
            head.next = head.next.next;
        }
        return str.substring (0, str.length () - 2);
    }




}
