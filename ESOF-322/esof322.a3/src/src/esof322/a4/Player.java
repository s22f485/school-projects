// Team Workiva adapted and modified this file.
// Team Workiva is MacKenzie O'Bleness, Lisa Peters & Larry Lynn. 
/* Summary of Changes to Player:
 * getMyItems and dropItem methods added to support getItemsInInventory() in AGMF
 */
package src.esof322.a4;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 * 
 * To compile: javac AdventureGame.java To run: java AdventureGame
 * 
 * The main routine is AdventureGame.main
 * 
 **/

public class Player implements Serializable {

	private Room myLoc;
	private ArrayList<Item> myThings = new ArrayList<Item>();
	private int itemCount = 0;
	private int maxItems = 3;

	public void setRoom(Room r) {
		myLoc = r;
	}

	public String look() {
		return myLoc.getDesc();
	}

	public String go(int direction) {
		return myLoc.exit(direction, this);
	}

	public void pickUp(Item item) {
		if (myThings.size() < maxItems) {
			myThings.add(item);
			itemCount++;
			myLoc.removeItem(item);
		}
	}

	public boolean haveItem(Item itemToFind) {
		for (Item item : myThings)
			if (item == itemToFind)
				return true;
		return false;
	}

	// Method added to provide later functionality of game
	// where player can have more than two items
	public ArrayList getMyItems() {
		ArrayList<Item> inventory = new ArrayList<Item>();
		inventory = (ArrayList<Item>) myThings.clone();
		return inventory;

	}

	public void dropItem(Item itemToDrop) {
		if (myThings.size() > -1) {
			myThings.remove(itemToDrop);
			itemCount--;
			myLoc.addItem(itemToDrop);

		}
	}

	// Method's functionality no longer works left in to fix later
	public void drop(int itemNum) {
		// if (itemNum > 0 & itemNum <= itemCount) {
		// switch (itemNum) {
		// case 1: {
		// myLoc.addItem(myThings[0]);
		// myThings[0] = myThings[1];
		// itemCount--;
		// break;
		// }
		// case 2: {
		// myLoc.addItem(myThings[1]);
		// itemCount--;
		// break;
		// }
		// }
		// }
	}

	public void setLoc(Room r) {
		myLoc = r;
	}

	public Room getLoc() {
		return myLoc;
	}

	public String showMyThings() {
		if (myThings.size() == 0) {
			return Constants.NO_PLAYER_ITEMS;
		}
		String outString = "";
		for (int n = 0; n < myThings.size(); n++)
			outString = outString + Integer.toString(n + 1) + ": "
					+ myThings.get(n).getDesc() + "\n";
		return outString;
	}

	public boolean handsFull() {
		return itemCount == maxItems;
	}

	public boolean handsEmpty() {
		return itemCount == 0;
	}

	public int numItemsCarried() {
		return itemCount;
	}

}
