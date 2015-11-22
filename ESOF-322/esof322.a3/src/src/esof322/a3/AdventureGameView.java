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

	private JButton inventoryButton = addButton("Inventory", 3, 5, 1, 1);
	private JButton dropButton = addButton("Drop an item", 4, 5, 1, 1);
	private JButton northButton = addButton("North", 12, 2, 1, 1);
	private JButton southButton = addButton("South", 14, 2, 1, 1);
	private JButton eastButton = addButton("East", 13, 3, 1, 1);
	private JButton westButton = addButton("West", 13, 1, 1, 1);
	private JButton upButton = addButton("Up", 12, 4, 1, 1);
	private JButton downButton = addButton("Down", 14, 4, 1, 1);
	
	private JButton saveButton = addButton("Save current game", 11, 5, 1, 1); 
	
	private JButton grabKey = addButton("Key", 5, 5, 1, 1);
	private JButton grabGold = addButton("Gold", 6, 5, 1, 1);

	private JButton dropKey = addButton("Key", 8, 5, 1, 1);
	private JButton dropGold = addButton("Gold", 9, 5, 1, 1);

	public AdventureGameModelFacade model;


	// Constructor-----------------------------------------------

	public AdventureGameView(int level, boolean loadLast) {

		setTitle("Adventure Game");
		try {
			if(loadLast==true){
				model = new AdventureGameModelFacade(level);
				model.load(); 
			}
			else{
				model = new AdventureGameModelFacade(level);
			}
			
		} catch (Exception IOException) {
			System.out.print("Problem at AGV Constructor");
		}
		viewArea.setEditable(false);
		carryingArea.setEditable(false);
		hideModals();
		displayCurrentInfo();
	}

	// buttonClicked method--------------------------------------
	// Method altered to provide functionality for new buttons
	public void buttonClicked(JButton buttonObj) {
		if (buttonObj == upButton) {
			model.goUp();
			hideModals();
		} else if (buttonObj == downButton) {
			model.goDown();
			hideModals();
		} else if (buttonObj == northButton) {
			model.goNorth();
			hideModals();
		} else if (buttonObj == southButton) {
			model.goSouth();
			hideModals();
		} else if (buttonObj == eastButton) {
			model.goEast();
			hideModals();
		} else if (buttonObj == westButton) {
			model.goWest();
			hideModals();
		} else if (buttonObj == grabKey) {
			model.pickupItemGUI(Constants.ItemTypes.Key);
			hideModals();
		} else if (buttonObj == grabGold) {
			model.pickupItemGUI(Constants.ItemTypes.Gold);
			hideModals();
		} else if (buttonObj == dropKey) {
			model.dropItemGUI(Constants.ItemTypes.Key);
			hideModals();
		} else if (buttonObj == dropGold) {
			model.dropItemGUI(Constants.ItemTypes.Gold);
			hideModals();
		}

		else if (buttonObj == inventoryButton){
			JFrame view = new InventoryView();
			view.setSize(400, 400); /* was 400, 250 */
			view.setVisible(true);
		}

		else if (buttonObj == dropButton)
			this.drop();
		
		else if (buttonObj == saveButton)
			model.save();
		



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
		Item[] itemsInRoom;
		Item itemInRoom;
		itemsInRoom = model.getItemsInRoom();
		
		if (model.canGetItem() && itemsInRoom.length > 0) {
			for (int i = 0; i < itemsInRoom.length; i++) {
				itemInRoom = itemsInRoom[i];
				if (itemInRoom instanceof Key) {
					grabKey.setVisible(true);
				} else if (itemInRoom instanceof Treasure) {
					grabGold.setVisible(true);
				}
			}
		}
	}

	// Method added to hide particular buttons when appropriate
	private void hideModals() {
		grabKey.setVisible(false);
		grabGold.setVisible(false);
		dropKey.setVisible(false);
		dropGold.setVisible(false);
	}

	// Method implemented to communicate with the model to get information
	// about items in player's inventory and display that info in the view
	// then communicate a player's choice to drop back to the model
	private void drop() {
		// Set up a dialog to talk to the model and
		// determine what items to pick up.
		Item[] itemsInInventory;
		Item itemInInventory;
		if (model.canDropItem()) {
			itemsInInventory = model.getItemsInInventory();
			for (int i = 0; i < itemsInInventory.length; i++) {
				itemInInventory = itemsInInventory[i];
				if (itemInInventory instanceof Key) {
					dropKey.setVisible(true);
				} else if (itemInInventory instanceof Treasure) {
					dropGold.setVisible(true);
				}
			}
		}
	}


}
