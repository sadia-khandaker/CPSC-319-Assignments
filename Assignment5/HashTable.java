import java.lang.*;

/**
 * HashTable class
 *
 * @author Sadia Khandaker
 * @version 1.5
 * @since 2022-04-08
 */

public class HashTable {
    int numberOfRecords; // number of records in the text file
    int tableSize; //size of the hash table
    String[] hashTable; //hash table
    int numberOfInsertions; //number of insertions
    int numberOfCollisions; //number of collisions

    /**
     * Constructor for the hash table
     *
     * @param tableSize       - the size of the hash table
     * @param numberOfRecords - the number of records in the text file
     */
    public HashTable(int tableSize, int numberOfRecords) {
        this.tableSize = tableSize;
        this.numberOfRecords = numberOfRecords;
        this.hashTable = new String[tableSize];
        this.numberOfInsertions = 0;
        this.numberOfCollisions = 0;

    }

    /**
     * Inserts a word into the hash table
     *
     * @param word - the word to be inserted
     *             Code adapted from: https://www.geeksforgeeks.org/implementing-hash-table-open-addressing-linear-probing-cpp/
     */
    public void insert(String word) {
        int index = hash (word);
        while (hashTable[index] != null) {
            if (hashTable[index].equals (word)) {
                return;
            }
            index = (index + 1) % tableSize;
            numberOfCollisions++;
        }
        hashTable[index] = word;
        numberOfInsertions++;
    }


    /**
     * Hash function that uses the sum of the ASCII values of the characters in the word and modulo the table size.
     *
     * @param word - the word to be hashed
     * @return the index of the word in the hash table
     * Code adapted from: https://cs.gmu.edu/~kauffman/cs310/w06-1.pdf
     */
    public int hash(String word) {
        int hash = 0;
        for (int i = 0; i < word.length (); i++) {
            hash = (hash * 31 + word.charAt (i)) % tableSize;
        }
        return hash;
    }

    /**
     * Calculates the average reads per record
     *
     * @return -the average reads per record
     */
    public double averageReadsPerRecord() {
        return (double) numberOfCollisions / numberOfRecords;
    }

    /**
     * Calculates the load factor
     *
     * @return - the load factor
     */
    public double loadFactor() {
        return (double) numberOfRecords / (double) tableSize;
    }

    /**
     * Calculates the hash efficiency
     *
     * @return - the hash efficiency
     */
    public double hashEfficiency() {
        return loadFactor () / averageReadsPerRecord ();
    }

    /**
     * Searches for a word in the hash table
     *
     * @param word - the word to be searched for
     * @return Code adapted from: http://www.mathcs.emory.edu/~cheung/Courses/253/Syllabus/Map/hash-impl.html
     */
    public int[] search(String word) {
        int[] result = new int[2];
        int index = hash (word);
        int count = 0;
        while (hashTable[index] != null) {
            if (hashTable[index].equals (word)) {
                result[0] = index;
                result[1] = count;
                return result;
            }
            index = (index + 1) % tableSize;
            count++;
        }
        return result;
    }

    public int getNumberOfCollisions() {
        return numberOfCollisions;
    }
}
