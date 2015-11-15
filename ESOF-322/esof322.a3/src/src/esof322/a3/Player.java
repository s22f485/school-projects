// Team Workiva adapted and modified this file.
// Team Workiva is MacKenzie O'Bleness, Lisa Peters & Larry Lynn. 
/* Summary of Changes to Player:
 * getMyItems and dropItem methods added to support getItemsInInventory() in AGMF
 */
package src.esof322.a3;
/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 * 
 * To compile: javac AdventureGame.java To run: java AdventureGame
 * 
 * The main routine is AdventureGame.main
 * 
 **/

public class Player {

	private Room myLoc;
	private Item[] myThings = new Item[2];
	private int itemCount = 0;
	public void setRoom(Room r) {
		myLoc = r;
	}

	public String look() {
		return myLoc.getDesc();
	}

	public String go(int direction) {
		return myLoc.exit(direction, this);
	}

	public void pickUp(Item i) {
		if (itemCount < 2) {
			myThings[itemCount] = i;
			itemCount++;
			myLoc.removeItem(i);
		}
	}

	public boolean haveItem(Item itemToFind) {
		for (int n = 0; n < itemCount; n++)
			if (myThings[n] == itemToFind)
				return true;
		return false;
	}

	// Method added to provide later functionality of game
	// where player can have more than two items
	public Item[] getMyItems() {
		Item[] inventory;
		inventory = new Item[2];
		System.arraycopy(myThings, 0, inventory, 0, myThings.length);
		return inventory;

	}

	// Method added to provide later functionality of game
	// where player can have more than two items
	public void dropItem(Item itemToDrop) {
		if (itemCount > 0) {
			if (myThings[0].equals(itemToDrop)) {
				// the item to drop is myThings[0]
				myThings[0] = myThings[1];
				myThings[1] = null;
			} else {
				// the item to drop is myThings[1]
				myThings[1] = null;
			}
			itemCount--;
			myLoc.addItem(itemToDrop);
		}
	}

	public void drop(int itemNum) {
		if (itemNum > 0 & itemNum <= itemCount) {
			switch (itemNum) {
			case 1: {
				myLoc.addItem(myThings[0]);
				myThings[0] = myThings[1];
				itemCount--;
				break;
			}
			case 2: {
				myLoc.addItem(myThings[1]);
				itemCount--;
				break;
			}
			}
		}
	}

	public void setLoc(Room r) {
		myLoc = r;
	}

	public Room getLoc() {
		return myLoc;
	}

	public String showMyThings() {
		if (itemCount == 0) {
			return Constants.NO_PLAYER_ITEMS;
		}
		String outString = "";
		for (int n = 0; n < itemCount; n++)
			outString = outString + Integer.toString(n + 1) + ": " + myThings[n].getDesc() + "\n";
		return outString;
	}

	public boolean handsFull() {
		return itemCount == 2;
	}

	public boolean handsEmpty() {
		return itemCount == 0;
	}

	public int numItemsCarried() {
		return itemCount;
	}

}
