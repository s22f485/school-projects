package hashtable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author lisapeters
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        System.out.println("Enter size of hash table: ");
        int input = getInt();
        HashTable table = new HashTable(input);
        System.out.println("Enter initial number of items: ");
        input = getInt();
        table.fillArray(input);

        while (true) {
            System.out.println("Enter first letter of show, insert, find or quit");
            int choice = getChar();
            switch (choice) {
                case 's':
                    table.showTable();
                    break;
                case 'i':
                    System.out.println("Enter value to insert: ");
                    input = getInt();
                    table.insertNode(input);
                    break;
                case 'f':
                    System.out.println("Enter value to find: ");
                    input = getInt();
                    table.findNode(input);
                    break;
                case 'q':
                    System.out.println("Quitting");
                    return;
                default:
                    System.out.print("Invalid entry\n");
            }  // end switch
        }  // end while
    }

    // -------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    // -------------------------------------------------------------
    public static String getText() throws IOException {
        String outStr = "", str = "";
        while (true) {
            str = getString();
            if (str.equals("$")) {
                return outStr;
            }
            outStr = outStr + str + "\n";
        }
    }  // end getText()
    // -------------------------------------------------------------

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    //-------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
    // -------------------------------------------------------------

}
