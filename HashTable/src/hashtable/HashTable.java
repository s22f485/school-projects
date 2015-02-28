/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;

import java.util.Random;

/**
 *
 * @author lisapeters
 */
public class HashTable {

    private final int size;
    private Tree[] array;
    private Random r = new Random();

    public HashTable(int n) {
        size = n;
        array = new Tree[size];
    }

    public void fillArray(int initial) {
        //initially filling of array with random numbers
        for (int i = 0; i < initial; i++) {
            int rand = r.nextInt(100);
            insertNode(rand);
        }

    }

    public int getSize() {
        return size;
    }

    public void showTable() {
        /*
         Showing hashtable using preorder traversal of each non-null tree
         */
        for (int i = 0; i < size; i++) {
            if (array[i] != null) {
                System.out.print(i + ". ");
                array[i].preOrder(array[i].root);
                System.out.println();
            }
        }
    }

    public void insertNode(int d) {
        /*
         Inserting a node with key d
         */
        int hash = d % size;
        if (array[hash] == null) { //if chosed slot in array is null, new tree is needed
            array[hash] = new Tree();
            array[hash].insert(d); //d becomes root of tree
        } else //otherwise insert d into exisiting tree
        {
            array[hash].insert(d);
        }
    }

    public void findNode(int d) {
        /*
         Findng a node with key d using Tree class's find function
         */
        int hash = d % size;
        if (array[hash] != null) {
            if (array[hash].find(d) != null) {
                System.out.println("Found: " + d);
                return;
            }
        }

        System.out.println("Couldn't find: " + d);

    }
}
