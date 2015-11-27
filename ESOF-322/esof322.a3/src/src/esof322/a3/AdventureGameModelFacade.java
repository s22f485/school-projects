// Team Workiva adapted and modified this file.
// Team Workiva is MacKenzie O'Bleness, Lisa Peters & Larry Lynn. 
/*
 * Summary of Changes to AdventureViewModelFacade:
 * direction methods implemented
 * getView, getItems methods implemented
 * startGUIQuest method created to initialize new adventure variables
 * pickupItemGUI, dropItemGui, getItemsInRoom, getItemsInInventory, canDropItems methods added
 * updateRoomDescription method added
 */
package src.esof322.a3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;



public class AdventureGameModelFacade implements Serializable {

	// some private fields to reference current location,
	// its description, what I'm carrying, etc.
	private Player thePlayer;
	private Adventure theCave;
	private Room startRm;

	private String roomDescription;
	private String statusMessage;
	private Save gameSave = new Save();
	private int level; 

	AdventureGameModelFacade(int level) { // we initialize
		this.level = level; 
		this.startGUIQuest();
		statusMessage = Constants.INTRODUCTION;
		updateRoomDescription();
	}
	/*
	 * startGUIQuest is a new method
	 */
	public void startGUIQuest() {
		thePlayer = new Player();
		theCave = null;
		AbstractFactory factory = FactoryProducer.getFactory("AdventureGame");

		switch (level) {
		case 1:
			theCave = factory.getAdventure("boring");
			break;
		case 2:
			theCave = factory.getAdventure("fantasy");
			break;
		default:
			System.out.println("Invalid choice");
		}		
		startRm = theCave.createAdventure();
		thePlayer.setRoom(startRm);
		


	}

	/* All direction methods were rewritten by Team Workiva.
	 * Where before they printed out a not yet implemented statement
	 * they now call the correct method to move the player and update
	 * the view accordingly. 
	 */ 
	public void goUp() {
		statusMessage = thePlayer.go(Constants.U);
		updateRoomDescription();
	}

	public void goDown() {
		statusMessage = thePlayer.go(Constants.D);
		updateRoomDescription();
	}

	public void goNorth() {
		statusMessage = thePlayer.go(Constants.N);
		updateRoomDescription();
	}

	public void goSouth() {
		statusMessage = thePlayer.go(Constants.S);
		updateRoomDescription();
	}

	public void goEast() {
		statusMessage = thePlayer.go(Constants.E);
		updateRoomDescription();
	}

	public void goWest() {
		statusMessage = thePlayer.go(Constants.W);
		updateRoomDescription();
	}

	public boolean canGetItem() {
		boolean itemGetable;
		if (thePlayer.handsFull()) {
			statusMessage = Constants.HANDS_FULL;
			itemGetable = false;
		} else if ((thePlayer.getLoc()).roomEmpty()) {
			statusMessage = Constants.ROOM_EMPTY;
			itemGetable = false;
		}
		// we can pick something up
		else {
			itemGetable = true;
		}
		return itemGetable;
	}

	// Method added to provide functionality for choosing a particular item
	// in the GUI game
	public void pickupItemGUI(Item toGrab) {
//		Item itemToInspect;
//		Item itemToGrab;
//		Item[] roomContents = (thePlayer.getLoc()).getRoomContents();
//		for (int i = 0; i < roomContents.length; ++i) {
//			itemToInspect = roomContents[i];
//			if (choice == Constants.ItemTypes.Key && itemToInspect instanceof Key) {
//				itemToGrab = itemToInspect;
//				thePlayer.pickUp(itemToGrab);
//				statusMessage = "You picked up: " + itemToGrab.getDesc();
//			} else if (choice == Constants.ItemTypes.Gold && itemToInspect instanceof Treasure) {
//				itemToGrab = itemToInspect;
//				thePlayer.pickUp(itemToGrab);
//				statusMessage = "You picked up: " + itemToGrab.getDesc();
//			}
//		}
		thePlayer.pickUp(toGrab);
		updateRoomDescription();
	}

	// Method added to provide functionality for dropping a particular item
	// in the GUI game
	public void dropItemGUI(Item toDrop) {

		thePlayer.dropItem(toDrop);
		updateRoomDescription();
	}

	// Method added to provide functionality for examining available items in room
	// in the GUI game
	public Item[] getItemsInRoomArray() {
		Item[] contentsArray;
		contentsArray = (thePlayer.getLoc()).getRoomContents();
		return contentsArray;
	}
	public ArrayList getItemsInRoomArrayList() {
		Item[] contentsArray;
		contentsArray = (thePlayer.getLoc()).getRoomContents();
		ArrayList<Item> contentsList = new ArrayList<Item>(Arrays.asList(contentsArray));
		return contentsList;
	}

	// Method added to provide functionality for dropping a particular item
	// in the GUI game
	public boolean canDropItem() {
		boolean itemDroppable;
		if (thePlayer.handsEmpty()) {
			statusMessage = Constants.HANDS_EMPTY;
			itemDroppable = false;
		} else {
			itemDroppable = true;
		}
		return itemDroppable;
	}

	// Method added to provide functionality for examining available items in a players inventory
	// in the GUI game
	public ArrayList getItemsInInventory() {
		ArrayList<Item> inventoryArray = new ArrayList<Item>();
		inventoryArray = thePlayer.getMyItems();
		return inventoryArray;
	}

	// Method altered to return status and player's current view of room
	public String getView() {
		return statusMessage + "\n\n" + roomDescription;
	}
	
	// Method altered to return string description of player's inventory
	public String getItems() {
		return thePlayer.showMyThings();
	}
	
	public void save(){
		gameSave.saveGame(this); 
	}
	
	public void load(){
		AdventureGameModelFacade loadModel = gameSave.loadGame();
		this.theCave = loadModel.theCave;
		this.thePlayer = loadModel.thePlayer;
		updateRoomDescription(); 
	}
	
	// Method added to update player's current view of room
	private void updateRoomDescription() {	
		Room location = thePlayer.getLoc(); 
		
		if(location instanceof HiddenItemsRoom){
			((HiddenItemsRoom) location).revealHidden(getItemsInInventory());
		}
		else if(statusMessage == "RiddleDoor"){
			startRiddle(); 
			location = thePlayer.getLoc(); 
		}
		roomDescription = location.getDesc();
		
	}
	
	private void startRiddle() {
		RiddleDoor door = thePlayer.getLoc().getRiddleDoor();
		if (!door.getAnswered()) {
			RiddleDoorPopup popup = new RiddleDoorPopup(door.getOptions(),
					door.getRiddle());

			if (popup.getInput() == door.getCorrect()) {
				statusMessage = door.actualEnter(thePlayer);
			} else {
				statusMessage = "That apparently wasn't the correct answer.";
			}
		}
		else{
			statusMessage = door.actualEnter(thePlayer);
		}
	}

}
