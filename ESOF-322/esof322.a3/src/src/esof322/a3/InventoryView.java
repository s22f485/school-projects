package src.esof322.a3;

import javax.swing.*;

import BreezySwing.*;
public class InventoryView extends GBFrame{
	private JButton grabItem= addButton("Pick Up", 1, 4, 1, 1);
	private JButton dropItem = addButton("Drop", 1, 5, 1, 1);
	private JButton useItem = addButton("Use", 1, 6, 1, 1);
	public InventoryView(){
		
	}
}
