package huffmancode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author lisapeters and lizzyherman
 */
public class HuffmanCode {

    static PriorityQ freQ;
    static Tree huffTree;
    static Map<Character, String> codeTable = new HashMap();
    static String encodeMessage = "suzysaysitiseasy.";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        char[] input = readIn("suzysaysitiseasy.");

        makeQueue(mapFrequency(input));
        makeHuffTree();

        String code = "";
        makeCodeTable(huffTree.root, code);
        System.out.println(codeTable);

        System.out.println(encode(encodeMessage));
        decode("11101001000011100001101110110011111011110011000101");
    }

    public static char[] readIn(String text) {
        text = text.toLowerCase();
        char[] symbols = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            symbols[i] = text.charAt(i);
        }
        return symbols;
    }

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
            frequencyTable.put("\\", temp);
        }
        return frequencyTable;
    }

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

    private static void makeHuffTree() {
        /*pop two min from freQ
         create new node from two min (key and freq added together)
         insert new node into freQ
         repeat until freQ is empty
         display tree*/

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
        huffTree.displayTree();

    }

    static private void makeCodeTable(Node current, String bc) {
        if (current.nodeKey.length() != 1) // not a leaf node
        {
            makeCodeTable(current.leftChild, bc + "0");  // call ourself
            makeCodeTable(current.rightChild, bc + "1"); // recursively
        } else // leaf node, so put
        {                               //    in code table
            codeTable.put(current.nodeKey.charAt(0), bc);
        }
    }  // end makeCodeTable()

    static private String encode(String encodeMessage) {
        int j = 0;
        String code = "";
        while (j < encodeMessage.length()) {
            char c = encodeMessage.charAt(j);
            code += codeTable.get(encodeMessage.charAt(j));
            j++;
        }

        return code;
    }

    private static void decode(String suz) {

        String decodedMsg = "";
        int cmLength = suz.length();
        int j = 0;
        while (j < cmLength) {
            Node theNode = huffTree.root;  // start at root
            while (theNode.nodeKey.length() > 1) // until leaf,
            {
                if (suz.charAt(j++) == '0') // if '0'
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
