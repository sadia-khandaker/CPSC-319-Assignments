import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
/**
 * Implementation of Assignment #4
 * Description: <The program reads both input file and query file, then performs DFS and BFS, and prints its path to output text files >
 * @author <Sadia Khandaker>
 * @version <1.0>
 */

public class Assign4 {
    public static void main(String[] args) {
        /* Checks for command-line arguments */
        if (args[0].length () == 0 || args[1].length () == 0 || args[2].length () == 0 || args[3].length () == 0) {
            System.out.println ("Invalid Command Line Arguments!");
            System.exit (0);
        }
        /* Initializing to read and write files */
        String input = args[0];
        String query = args[1];
        String output1 = args[2];
        String output2 = args[3];
        Scanner scanInput = null;
        Scanner scanQuery = null;
        PrintWriter writeOutput1 = null;
        PrintWriter writeOutput2 = null;
        try {
            scanInput = new Scanner (new FileReader(input));
        } catch (IOException e) {
            System.err.println("Scanner could not be opened");
            System.exit(0);
        }
        try {
            scanQuery = new Scanner(new FileReader(query));
        } catch (IOException e) {
            System.err.println("Scanner could not be opened");
            System.exit(0);
        }
        try {
            writeOutput1 = new PrintWriter(output1);
        } catch (IOException e) {
            System.err.println("PrintWriter could not be opened");
            System.exit(0);
        }
        try {
            writeOutput2 = new PrintWriter(output2);
        } catch (IOException e) {
            System.err.println("PrintWriter could not be opened");
            System.exit(0);
        }
        Graph directedGraph;

        /* Reads the input file and stores it in Graph object */
        String temp = scanInput.nextLine();
        String[] tempArray = temp.split("\t");
        directedGraph = new Graph(tempArray.length);
        directedGraph.addNode (temp, 0);
        for (int i = 1; scanInput.hasNextLine(); i++) {
            directedGraph.addNode (scanInput.nextLine(), i);
        }

        /*
        Reads query file to perform depth-first search, and breadth-search first, and prints their corresponding path
         */
        while (scanQuery.hasNextLine()) {
            temp = scanQuery.nextLine();
            tempArray = temp.split("\t");
            directedGraph.printDepthFirstSearch (Integer.parseInt(tempArray[0]), Integer.parseInt(tempArray[1]), writeOutput1);
            directedGraph.printBreadthFirstSearch (Integer.parseInt(tempArray[0]), Integer.parseInt(tempArray[1]), writeOutput2);
        }
        writeOutput1.close();
        writeOutput2.close();
        scanInput.close();
        scanQuery.close();

    }
}

/**
 *  Adjacency-Lists based Graph Implementation
 */
class Graph {
    int vertex; // number of vertices in this directed graph
    Node[] adjList; // adj[v] = adjacency list for vertex v
    /* Constructs an adjacent list with size V */
    public Graph(int V) {
        this.vertex = V;
        adjList = new Node[V];
    }


    /**
     * Method to add nodes to the adjacency  list
     *
     * Adapted From:
     * -------------------
     * Title: Computer Science: An Interdisciplinary Approach
     * Author: Robert Sedgewick and Kevin Wayne
     * Date: 2016
     * Type: Source Code
     * Availability: https://introcs.cs.princeton.edu/java/45graph/Graph.java
     *
     * @param data data to add
     * @param row specified row to add
     */
    public void addNode(String data, int row) {
        String[] split = data.split ("\t");
        int i = 0;
        while (i < vertex) {
            if (!Objects.equals (split[i], "0")) {
                adjList[row] = new Node (null, i, Integer.parseInt (split[i]));
                break;
            }
            i++;
        }
        i++;
        Node current = adjList[row];
        while (i < vertex) {
            if (!Objects.equals (split[i], "0")) {
                current.setNext (new Node (null, i, Integer.parseInt (split[i])));
                current = current.next;
            }
            i++;
        }
    }

    /**
     * Method to perform depth first search and prints its path
     *
     * Adapted From:
     *  -------------------
     *  Title: Depth First Search or DFS for a Graph
     *  Type: Source Code
     *  Availability: https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
     *
     * @param source source vertex
     * @param destination destination vertex
     * @param output1 the file used to the print all the depth-first search path
     */
    public void printDepthFirstSearch(int source, int destination, PrintWriter output1) {
        boolean[] visited = new boolean[vertex];
        String result = depthFirstSearch (source, destination, visited);
        output1.println (Objects.requireNonNullElseGet (result, () -> source + " -> -1 -> " + destination));
    }

    /**
     * Method used by printDepthFirstSearch for every vertex to do depth-first search
     *
     * Adapted From:
     *  -------------------
     *  Title: Depth First Search or DFS for a Graph
     *  Type: Source Code
     *  Availability: https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
     *
     * @param source source vertex
     * @param destination destination vertex
     * @param isVisited  a boolean visited array
     * @return
     */
    public String depthFirstSearch(int source, int destination, boolean[] isVisited) {
        if (source != destination) {
            isVisited[source] = true;
            Node current = adjList[source];
            while (current != null) {
                if (!isVisited[current.data]) {
                    String temp = depthFirstSearch (current.data, destination, isVisited);
                    if (temp != null) {
                        String temp2 = String.valueOf (source);
                        temp2 += (" -> " + temp);
                        return temp2;
                    }
                }
                current = current.next;
            }
            return null;
        } else {
            return String.valueOf (source);
        }
    }

    /**
     * Method to perform breath first search and prints its path
     *
     * Adapted From:
     *  -------------------
     *  Title: Breadth First Search or BFS for a Graph
     *  Type: Source Code
     *  Availability: https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
     *
     * @param source source vertex
     * @param destination destination vertex
     * @param output2 the file used to the print all the breadth-first search path
     */
    public void printBreadthFirstSearch(int source, int destination, PrintWriter output2) {
        boolean[] isVisited = new boolean[vertex]; // initializes a boolean array to mark which vertices has been visited
        int[] previous = IntStream.range (0, vertex).map (i -> -1).toArray ();
        Queue queue = new Queue ();
        isVisited[source] = true;
        queue.enqueue (source);
        while (!queue.isEmpty ()) {
            int dequeuedVertex = queue.dequeue ();
            if (dequeuedVertex == destination) {
                break;
            }
            Node current = adjList[dequeuedVertex];
            while (current != null) {
                if (!isVisited[current.data]) {
                    isVisited[current.data] = true;
                    previous[current.data] = dequeuedVertex;
                    queue.enqueue (current.data);
                }
                current = current.next;
            }
        }
        if (previous[destination] == -1) {
            output2.println (source + " -> -1 -> " + destination);
        } else {
            int temp = destination;
            String temp2 = String.valueOf (destination);
            while (previous[temp] != -1) {
                temp2 = previous[temp] + " -> " + temp2;
                temp = previous[temp];
            }
            output2.println (temp2);
        }
    }
}



/**
 * Node Class
 *
 * Adapted From:
 * -------------------
 * Title: Data Structures and Algorithms in Java
 * Author: Michael T. Goodrich and Roberto Tamassia
 * Date: 2015
 * Type: Source Code
 * Availability: http://bedford-computing.co.uk/learning/wp-content/uploads/2016/08/Data-Structures-and-Algorithms-in-Java-6th-Edition.pdf
 */
    class Node {
        Node next; // reference to the next node in the list
        int data; // the data the node holds
        private int distance; // distance between the row in which the node is located and the row to which this node points.

        public Node(Node next, int data, int distance) {
            this.next = next;
            this.data = data;
            this.distance = distance;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


/**
 * Array-Based Queue Implementation
 *
 * Adapted From:
 * -------------------
 * Title: Data Structures and Algorithms in Java
 * Author: Michael T. Goodrich and Roberto Tamassia
 * Date: 2015
 * Type: Source Code
 * Availability: http://bedford-computing.co.uk/learning/wp-content/uploads/2016/08/Data-Structures-and-Algorithms-in-Java-6th-Edition.pdf
 */
class Queue {
        private int[] array; // integer array used for storage
        int front; // front of the queue
        int rear; // back of the queue

    /*Constructor*/
        public Queue() {
            this.front = 0;
            this.rear = 0;
            array = new int[1000]; //constructs queue with default capacity of 1000

        }

        /* Tests whether the queue is full. */
        public boolean isFull() {
            return rear + 1 % 1000 == front;
        }

        /* Tests whether the queue is empty. */
        public boolean isEmpty() {
            return rear == front;
        }

        /* Inserts an element at the rear of the queue. */
        public void enqueue(int element) {
            if (isFull ()) {
                throw new IllegalStateException ("Cannot add to full queue");
            }
            array[rear] = element;
            rear = (rear + 1) % array.length;

        }

    /*Removes and returns the first element of the queue*/
        public int dequeue() {
            int temp;
            if (isEmpty ()) {
                throw new NoSuchElementException ("Cannot remove from empty queue");
            } else {
                temp = array[front];
                front = front + 1 % 1000;
            }
            return temp;
        }
    }
