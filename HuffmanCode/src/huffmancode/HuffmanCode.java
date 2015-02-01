package huffmancode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author lisapeters and lizzyherman
 */
public class HuffmanCode {

    static Map<String, Integer> frequencyTable;
    static Tree freqTree; 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        char[] input = new char[5];
        input[0] = 'a';
        input[1] = 'b';
        input[2] = 'c';
        input[3] = 'a';
        input[4] = '.';

        PriorityQ freQ = new PriorityQ(input.length);

        makeQueue(mapFrequency(input));
    }

    public static Map mapFrequency(char[] input) {

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

        freqTree = new Tree();
        PriorityQ freQ = new PriorityQ(mapFrequency.size()); 
        Iterator<Map.Entry<String, Integer>> entries = mapFrequency.entrySet().iterator();
        
        while (entries.hasNext()) {
            Map.Entry<String, Integer> entry = entries.next();
            freqTree.insert(entry.getKey(), entry.getValue());
            freQ.insert(freqTree.find(entry.getKey()));
            
        }
        
        freqTree.displayTree();
        System.out.println(freQ.peekMin().nodeKey);

    }
}
