import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * Main Driver of the program
 * Created by: Sadia Khandaker
 */
public class Assign2 {


    public static void main(String[] args) {


        System.out.println ("Selected input file: "+ (args[0]));
        Vector vector = new Vector ();
        long start = System.nanoTime ();


        try {

            String inputFilePath = ("input/" + args[0]);
            BufferedReader reader = new BufferedReader (new FileReader (inputFilePath));
            String word;
            while ((word = reader.readLine ()) != null) {
                if (vector.size () != 0) {
                    boolean flag;
                    flag = true;

                    int i = 0;
                    while (i < vector.size () && flag) {
                        String tempWord = vector.storageData[i].head.data;
                        if (tempWord.length () == word.length ()) {
                            String firstWord = sortString (tempWord);
                            String secondWord = sortString (word);
                            if (Objects.equals (firstWord, secondWord)) {
                                vector.storageData[i].add (word);
                                flag = false;
                            }
                        }

                        i++;
                    }
                    if (flag) {
                        Node tempNewNode = new Node (word, null);
                        LinkedList tempList = new LinkedList (tempNewNode);
                        vector.add (tempList);
                    }
                } else {
                    Node tempNewNode = new Node (word, null);
                    LinkedList tempList = new LinkedList (tempNewNode);
                    vector.add (tempList);
                }

            }
            reader.close ();

        } catch (IOException e) {
            e.printStackTrace ();
            System.exit (1);

        }

        InsertionSort sortLinkedLists = new InsertionSort ();
        for (int i = 0; i < vector.size (); i++) {
            vector.setElementAt (sortLinkedLists.insertionSort (vector.storageData[i]), i);
        }

        QuickSort sort = new QuickSort ();
        sort.quickSort (vector);


        long end = System.nanoTime ();
        long duration = end - start;
        System.out.println ("Time taken (ns) to process input file, " + (args[0]) + " is " + (duration));
        System.out.println ("Time taken (ms) to process input file: " + (duration / 1E6));
        System.out.println ("Time taken (s) to process input file: "  + (duration / 1E9));
        System.out.println ("Created output file: "+ (args[1]));

        try {
            FileWriter writer = new FileWriter (args[1]);
            for (int i = 0; i < vector.size (); i++) {
                Node temp = vector.storageData[i].head;
                writer.write (String.format ("%s ", temp.data));
                temp = temp.next;

                while (temp != null) {
                    writer.write (String.format ("%s ", temp.data));
                    temp = temp.next;

                }
                writer.write ("\r\n");

            }
            writer.close ();

        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public static String sortString(String word) {
        if (word.length () == 1) {
            return word;
        }

        char[] characters = word.toCharArray ();
        InsertionSort sort = new InsertionSort ();
        sort.sortChars (characters);
        return new String (characters);
    }
}
