/**
 * The Insertion Sort Class
 * Code adapted from T01, T02, and T04 tutorials on Linked List on D2L
 *
 */

public class InsertionSort {
    public LinkedList insertionSort(LinkedList linkedList)
    {
        if(linkedList.head.next == null) {
            return linkedList;
        }
        LinkedList tempList = new LinkedList();
        tempList.head = new Node(linkedList.head.data, null);
        Node tempNode = linkedList.head.next;
        tempNode = getNode (tempList, tempNode);
        while (tempNode != null) {
            tempNode = getNode (tempList, tempNode);
        }

        return tempList;
    }

    public Node getNode(LinkedList tempList, Node tempNode) {
        if ((tempNode.data).compareTo (tempList.head.data) <= 0) {
            tempList.add (tempNode.data);
        } else {
            Node temp = tempList.head;
            while (temp.next != null && (tempNode.data).compareTo (temp.next.data) > 0) {
                temp = temp.next;
            }
            tempList.insertAfter (tempNode.data, temp);
        }

        tempNode = tempNode.next;
        return tempNode;
    }


    public void sortChars(char [] charArray)
    {
        int i = 1, j;
        while (i < charArray.length) {
            char temp = charArray[i];
            j = i;
            while (j > 0 && temp < charArray[j - 1]) {
                charArray[j] = charArray[j - 1];
                j--;
            }
            charArray[j] = temp;
            i++;
        }
    }
}