import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main Runner of the Program
 * Created by: Sadia Khandaker
 */
public class Assign3 {
    public static void main(String[] args) {
        if (args[0].length () == 0 || args[1].length () == 0|| args[2].length () == 0){
            System.err.println("Invalid arguments.");
            System.exit (1);
        }

        BinarySearchTree<Student> studentRecord = new BinarySearchTree<> ();

        try {
            File input = new File(args[0]);
            Scanner scanner = new Scanner(input);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                char operationCode = data.charAt(0);
                String studentNumber = data.substring(1, 8);
                String studentLastName = data.substring(8, 33);
                String homeDepartment = data.substring(33, 37);
                String program = data.substring(37, 41);
                char year = data.charAt(41);

                Student student = new Student(studentNumber, studentLastName, homeDepartment, program, year);

                switch (operationCode) {
                    case 'I':
                        studentRecord.add (student);
                        break;
                    case 'D':
                        studentRecord.delete (student);
                        break;
                    default:
                        System.err.println ("Invalid operation code.");
                        System.exit (0);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try {

            FileWriter writer = new FileWriter(args[1]);
            writer.write(studentRecord.inOrder ());
            writer.close();

            writer = new FileWriter(args[2]);
            writer.write(studentRecord.levelOrder ());
            writer.close();

        } catch (IOException e) {
            System.out.println("IO exception occurred.");
            e.printStackTrace();
        }
    }
}
