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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        char[] input = readIn("The quick br foxt"); 

        
        makeQueue(mapFrequency(input));
        makeHuffTree(); 
    }
    
    public static char[] readIn(String text){
        text = text.toLowerCase(); 
        char[] symbols = new char[text.length()];
        for(int i = 0; i < text.length(); i++)
            symbols[i] = text.charAt(i);
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
            if(!freQ.isEmpty()){
            freQ.insert(newNode);
            }
        }
        huffTree.root = root;
        huffTree.displayTree();

    }
}
