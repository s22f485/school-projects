// Team Workiva adapted and modified this file.
// Team Workiva is MacKenzie O'Bleness, Lisa Peters & Larry Lynn. 

/* Summary of changes to AdventureGameView
 * Added buttons for picking up and dropping the gold and key items
 * buttonClicked method modified for new buttons
 * hideModals method added to hide new buttons when appropriate 
 */

package src.esof322.a3;

// java imports
import java.io.*;
import java.util.ArrayList;


// library imports
import javax.swing.*;

import BreezySwing.*;

public class AdventureGameView extends GBFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Window objects --------------------------------------
	private JLabel welcomeLabel = addLabel(
			"Welcome to the Adventure Game "
					+ "(inspired by an old game called the Colossal Cave Adventure)."
					+ " Java implementation Copyright (c) 1999-2012 by James M. Bieman",
			1, 1, 5, 1);

	private JLabel viewLable = addLabel("Your View: ", 2, 1, 1, 1);
	JTextArea viewArea = addTextArea("Start", 3, 1, 4, 3);

	private JLabel carryingLable = addLabel("You are carying: ", 6, 1, 1, 1);
	private JTextArea carryingArea = addTextArea("Nothing", 7, 1, 4, 3);

	private JLabel separator1 = addLabel(
			"-----------------------------------------------------------------",
			10, 1, 4, 1);

	private JLabel choiceLabel = addLabel(
			"Choose a direction, pick-up, or drop an item", 11, 1, 5, 1);

	private JButton grabButton = addButton("Pick up an item", 3, 5, 1, 1);
	private JComboBox itemsInRoom = addComboBox(4, 5, 1, 1);
	private JButton dropButton = addButton("Drop an item", 5, 5, 1, 1);
	private JComboBox itemsInInventory = addComboBox(6, 5, 1, 1);
	private JButton northButton = addButton("North", 12, 2, 1, 1);
	private JButton southButton = addButton("South", 14, 2, 1, 1);
	private JButton eastButton = addButton("East", 13, 3, 1, 1);
	private JButton westButton = addButton("West", 13, 1, 1, 1);
	private JButton upButton = addButton("Up", 12, 4, 1, 1);
	private JButton downButton = addButton("Down", 14, 4, 1, 1);

	private JButton saveButton = addButton("Save current game", 11, 5, 1, 1);

	private ArrayList<Item> itemsInRoomList;
	private ArrayList<Item> itemsInInventoryList;

	public AdventureGameModelFacade model;

	// Constructor-----------------------------------------------

	public AdventureGameView(int level, boolean loadLast) {
		setTitle("Adventure Game");
		viewArea.setEditable(false);
		viewArea.setLineWrap(true);
		viewArea.setWrapStyleWord(true);
		carryingArea.setEditable(false);
		pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			if (loadLast == true) {
				model = new AdventureGameModelFacade(level);
				model.load();
			} else {
				model = new AdventureGameModelFacade(level);
			}

		} catch (Exception IOException) {
			System.out.print("Problem at AGV Constructor");
		}
		
		updateAllItems();
		displayCurrentInfo();
		
	}

	// buttonClicked method--------------------------------------
	// Method altered to provide functionality for new buttons
	public void buttonClicked(JButton buttonObj) {
		if (buttonObj == upButton) {
			model.goUp();
		} else if (buttonObj == downButton) {
			model.goDown();
		} else if (buttonObj == northButton) {
			model.goNorth();
		} else if (buttonObj == southButton) {
			model.goSouth();
		} else if (buttonObj == eastButton) {
			model.goEast();
		} else if (buttonObj == westButton) {
			model.goWest();
		} else if (buttonObj == grabButton) {
			grab();
		} else if (buttonObj == dropButton) {
			drop();
		} else if (buttonObj == saveButton)
			model.save();

		updateAllItems();
		displayCurrentInfo();
	}

	// Private methods-------------------------------------------
	// Method implemented to get view info from model, then display it
	private void displayCurrentInfo() {
		viewArea.setText(model.getView());
		carryingArea.setText(model.getItems());
	}

	// Method implemented to communicate with the model to get information
	// about items in room and display that info in the view
	// then communicate the player's choice to grab back to the model
	private void grab() {
		// Set up a dialog to talk to the model and
		// determine what items to pick up.
		itemsInRoomList = model.getItemsInRoomArrayList();
		Item toGrab;
		if (model.canGetItem() && itemsInRoomList.size() > 0) {
			toGrab = (Item) itemsInRoom.getSelectedItem();
			model.pickupItemGUI(toGrab);
			updateAllItems();
		}
	}

	// Method implemented to communicate with the model to get information
	// about items in player's inventory and display that info in the view
	// then communicate a player's choice to drop back to the model
	private void drop() {
		// Set up a dialog to talk to the model and
		// determine what items to pick up.
		ArrayList<Item> itemsInInventoryList = model.getItemsInInventory();
		Item toDrop;
		if (model.canDropItem() && itemsInInventoryList.size() > 0) {
			toDrop = (Item) itemsInInventory.getSelectedItem();
			model.dropItemGUI(toDrop);
			updateAllItems();
		}
	}

	public void updateAllItems() {
		itemsInRoomList = model.getItemsInRoomArrayList();
		itemsInRoom.removeAllItems();
		if (itemsInRoomList.size() == 0) {
			itemsInRoom.addItem("No items in room");
		} else {
			for (Item item : itemsInRoomList) {
				itemsInRoom.addItem(item);
			}
		}

		itemsInInventoryList = model.getItemsInInventory();
		itemsInInventory.removeAllItems();
		if (itemsInInventoryList.size() == 0) {
			itemsInInventory.addItem("You have no items");
		} else {
			for (Item item : itemsInInventoryList) {
				itemsInInventory.addItem(item);
			}
		}

	}

}
