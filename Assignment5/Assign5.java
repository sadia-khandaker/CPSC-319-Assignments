import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * Assign5 class is the main class to run the program.
 *
 * @author Sadia Khandaker
 * Description: Main runner of the program. This program creates a hash table that stores words from an input file
 * until the hash table is at least 70% full. The implemented hash function provides at least 50% hash efficiency .
 * In addition, the program calculates and prints statistics about the average number of reads per record,
 * the load factor, the hashing efficiency, and the size of the longest chain when searching.
 */
public class Assign5 {
    public static void main(String[] args) throws IOException {
        // Checks for correct number of arguments
        if (args.length != 2) {
            System.out.println ("Invalid number of arguments. Please enter two arguments, the first being the input file and the second being the output file.");
            System.exit (1);
        }
        // Checks for valid file names
        if (!args[0].endsWith (".txt") || !args[1].endsWith (".txt")) {
            System.out.println ("Input and output files must be .txt files.");
            System.exit (1);
        }

        String input = args[0]; // Input file name
        String output = args[1]; // Output file name

        final int RECORDS = 11393; // Number of records in the input file
        final int TABLE_SIZE = 16301; // Size of the hash table
        
        Scanner inFile = new Scanner (new FileReader (input)); // Scanner for input file
        PrintWriter outFile = new PrintWriter (output); // PrintWriter for output file

        HashTable h = new HashTable (TABLE_SIZE, RECORDS); // Creates a new hash table

        while (inFile.hasNextLine ()) {
            h.insert (inFile.nextLine ());
        }
        inFile = new Scanner (new FileReader (input));
        int longestChain = 0; // Initializes longest chain to 0
        while (inFile.hasNextLine ()) {
            int[] results = h.search (inFile.nextLine ());
            if (results[1] > longestChain) {
                longestChain = results[1]; // Sets longest chain to the longest chain found
            }
        }
        NumberFormat formatter = new DecimalFormat ("#0.00");
        // Prints statistics to output file
        outFile.println ("Average number of reads per record: " + formatter.format (h.averageReadsPerRecord ()));
        outFile.println ("Load factor: " + formatter.format (h.loadFactor ()));
        outFile.println ("Hashing efficiency: " + formatter.format (h.hashEfficiency ()));
        outFile.println ("Longest chain length: " + longestChain);
        outFile.println ("Number of collisions: " + h.getNumberOfCollisions ());
        outFile.close ();
        inFile.close ();
    }
}
