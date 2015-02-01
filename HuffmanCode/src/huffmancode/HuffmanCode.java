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


        Node aone = new Node(); 
        aone.nodeFreq = 1; 
        aone.nodeKey = "a"; 
        Node czero = new Node(); 
        czero.nodeFreq = 1; 
        czero.nodeKey = "c";
        Node dfour = new Node(); 
        dfour.nodeFreq = 4; 
        dfour.nodeKey = "d"; 
        
        
        
        freQ.insert(aone);
        freQ.insert(czero);
        freQ.insert(dfour);
        Node temp1 = freQ.remove();
        System.out.println("temp1 => " + temp1.nodeKey + ":" + temp1.nodeFreq);
        Node temp2 = freQ.remove();
        System.out.println("temp2 => " + temp2.nodeKey + ":" + temp2.nodeFreq);
        Node aAndc = new Node(); 
        aAndc.nodeFreq = temp1.nodeFreq + temp2.nodeFreq; 
        aAndc.nodeKey = temp1.nodeKey + temp2.nodeKey; 
        freQ.insert(aAndc);
        System.out.println(freQ.peekMin().nodeKey + ";" + freQ.peekMin().nodeFreq);
        
       
        
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
