package huffmancode;

/**
 *
 * @author lisapeters
 */
// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp
import java.io.*;
import java.util.*; // for Stack class
////////////////////////////////////////////////////////////////
class Node
{
	public String nodeKey; // data item (key)
	public int nodeFreq; // data item
	public Node leftChild; // this node's left child
	public Node rightChild; // this node's right child
	public void displayNode() // display ourself
	{
		System.out.print('{');
		System.out.print(nodeKey);
		System.out.print(", ");
		System.out.print(nodeFreq);
		System.out.print("} ");
	}
} // end class Node
////////////////////////////////////////////////////////////////