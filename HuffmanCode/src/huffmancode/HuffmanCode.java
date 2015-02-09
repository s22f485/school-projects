package huffmancode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author lisapeters
 */
public class HuffmanCode {

    static PriorityQ freQ;
    static Tree huffTree;
    static Map<Character, String> codeTable = new HashMap();
    static String bicode = "";
    static String code = "";
    static String encodedMessage = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("enter, show, code, or decode: ");
            int choice = getChar();
            switch (choice) {
                case 'e':
                    System.out.println(
                            "Enter text lines, terminate with $");
                    code = getText();
                    code = code.toLowerCase(); 
                    makeHuffTree(code);
                    break;
                case 's':
                    huffTree.displayTree();
                    break;
                case 'c':
                    makeCodeTable(huffTree.root, bicode);
                    if (codeTable.size() == 1) {
                        System.out.println("Why would you make a code table with only one letter?");
                        break;
                    }
                    System.out.println(codeTable);
                    encodedMessage = encode(code);
                    System.out.println("Binary code:\n" + encodedMessage);
                    break;
                case 'd':
                    decode(encodedMessage);
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }
        }
    }

    /*
     Teacher's code
     */
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    /*
     Teacher's code
     */
    public static String getText() throws IOException {
        String outStr = "", str = "";
        while (true) {
            str = getString();
            if (str.equals("$")) {
                return outStr;
            }
            outStr = outStr + str + "\n";
        }
    }
    
    /*
     Teacher's code
     */
    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    /*
     Take in String and return char[]
     */
    public static char[] readIn(String text) {
        text = text.toLowerCase();
        char[] symbols = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            symbols[i] = text.charAt(i);
        }
        return symbols;
    }

    /*
     Make a hashtable map of the frequency of chars
     Helper method for makeHuffTree
     */
    public static Map mapFrequency(char[] input) {
        Map<String, Integer> frequencyTable;
        frequencyTable = new HashMap<String, Integer>();
        for (int i = 0; i < input.length; i++) {
            String c = String.valueOf(input[i]);
            if (frequencyTable.get(c) != null) {
                int val = frequencyTable.get(c);
                frequencyTable.put(c, val += 1);
            } else {
                frequencyTable.put(c, 1);
            }

        }
        int temp = 0;
        if (frequencyTable.containsKey(" ")) {
            temp = frequencyTable.get(" ");
            frequencyTable.remove(" ");
            frequencyTable.put("[", temp);
        }
        if (frequencyTable.containsKey("\n")) {
            temp = frequencyTable.get("\n");
            frequencyTable.remove("\n");
            frequencyTable.put("/", temp);
        }
        if (frequencyTable.containsKey("\r")) {
            temp = frequencyTable.get("\r");
            temp += frequencyTable.get("\n");
            frequencyTable.remove("\r");
            frequencyTable.put("/", temp);
        }
        return frequencyTable;
    }

    /*
     Make a priority queue of Nodes with the value of the frequencyTable
     Helper Method for makeHuffTree
     */
    private static void makeQueue(Map mapFrequency) {

        freQ = new PriorityQ(mapFrequency.size());
        Iterator<Map.Entry<String, Integer>> entries = mapFrequency.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<String, Integer> entry = entries.next();
            Node newNode = new Node();
            newNode.nodeFreq = entry.getValue();
            newNode.nodeKey = entry.getKey();
            freQ.insert(newNode);

        }
    }

    /*
     Make a new huffman tree
     calls helper methods makeQueue and mapFrequency
     */
    private static void makeHuffTree(String str) {

        char message[] = readIn(str);
        makeQueue(mapFrequency(message));
        huffTree = new Tree();
        Node root = null;
        while (!freQ.isEmpty()) {
            Node temp1 = freQ.remove();
            if (freQ.isEmpty()) {
                root = temp1;
                break;
            }
            Node temp2 = freQ.remove();
            Node newNode = new Node();
            newNode.leftChild = temp1;
            newNode.rightChild = temp2;
            newNode.nodeFreq = (temp1.nodeFreq + temp2.nodeFreq);
            newNode.nodeKey = (temp1.nodeKey + temp2.nodeKey);
            root = newNode;
            if (!freQ.isEmpty()) {
                freQ.insert(newNode);
            }
        }
        huffTree.root = root;
    }

    /*
     Teacher's code with modification 
     Makes a code table for given huffTree
     */
    static private void makeCodeTable(Node current, String bc) {
        if (current.nodeKey.length() != 1) // not a leaf node
        {
            makeCodeTable(current.leftChild, bc + "0");  // call ourself
            makeCodeTable(current.rightChild, bc + "1"); // recursively
        } else // leaf node, so put
        {                               //    in code table
            codeTable.put(current.nodeKey.charAt(0), bc);
        }
    }

    /*
     Encode's given message
     */
    static private String encode(String code) {
        String encodeMessage = "";
        int j = 0;
        while (j < code.length()) {
            char c = code.charAt(j);
            if (code.charAt(j) == '\n') { //linefeed
                encodeMessage += codeTable.get('/');
                j++;
            } else if (code.charAt(j) == ' ') {  //space
                encodeMessage += codeTable.get('[');
            } else {
                encodeMessage += codeTable.get(code.charAt(j));
            }
            j++;
        }

        return encodeMessage;
    }

    /*
     Decodes given binary message
     */
    private static void decode(String strg) {

        String decodedMsg = "";
        int cmLength = strg.length();
        int j = 0;
        while (j < cmLength) {
            Node theNode = huffTree.root;  // start at root
            while (theNode.nodeKey.length() > 1) // until leaf,
            {
                if (strg.charAt(j++) == '0') // if '0'
                {
                    theNode = theNode.leftChild;
                } else // if '1'
                {
                    theNode = theNode.rightChild; // go right
                }

            }
            decodedMsg = decodedMsg + theNode.nodeKey; // letter at
        }                                     // leaf node
        System.out.println("Decoded msg:\n" + decodedMsg);

    }
}
