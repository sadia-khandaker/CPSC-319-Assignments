/**
 * The Linked List Class
 * Code adapted from Dr. Manzara's lecture note on D2L:
 *
 */

public class LinkedList {
    public Node head; // Head of the List

    public LinkedList() {
        this.head = null;
    } // Constructor

    public LinkedList(Node newNode) {
        this.head = newNode;
    } //Constructor


    public void add(String data) {  // Appends at the end
        Node newNode = new Node (data, head);
        head = newNode;
    }


    public void insertAfter(String data, Node previous) { // Inserts a new node after given previous node
        Node newNode = new Node (data, previous.next);
        previous.next = newNode;
    }

}

