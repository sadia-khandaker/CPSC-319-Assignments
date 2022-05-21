/**
 * Code adapted from an article on GeeksforGeeks:
 *
 */

public class Vector {
    public LinkedList[] storageData;
    public int currentSize;
    public int totalCapacity;

    public Vector() {
        this.currentSize = 0;
        this.totalCapacity = 1;
        storageData = new LinkedList[totalCapacity];

    }

    public int size() {
        return currentSize;
    }


    public void add(LinkedList list) {  // Appends the specified Linked list to the end
        if (currentSize == totalCapacity) {
            LinkedList[] temp = new LinkedList[2 * totalCapacity];

            for (int i = 0; i < totalCapacity; i++) {
                temp[i] = storageData[i];
            }
            totalCapacity *= 2;
            storageData = temp;
        }
        storageData[currentSize] = list;
        currentSize++;
    }

    public void setElementAt(LinkedList list, int index) {
        storageData[index] = list;
    }


}