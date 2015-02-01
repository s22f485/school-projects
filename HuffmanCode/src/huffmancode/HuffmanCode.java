package huffmancode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lisapeters and lizzyherman
 */
public class HuffmanCode {

    static Map<String, Integer> frequencyTable;

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

        System.out.println(mapFrequency(input));
        PriorityQ freQ = new PriorityQ(input.length); 
        
        freQ.insert(4);
        freQ.insert(1);
        freQ.insert(0);
        freQ.insert(2);
        System.out.println(freQ.peekMin());
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
}
