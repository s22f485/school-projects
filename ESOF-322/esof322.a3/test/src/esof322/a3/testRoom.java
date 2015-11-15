package src.esof322.a3;

import static org.junit.Assert.*;

import org.junit.Test;

public class testRoom {

	Room testRoom1 = new Room();
	Room testRoom2 = new Room(); 
	Player testPlayer = new Player(); 
	Item testItem1 = new Item();
	Item testItem2 = new Item();
	Key testKey = new Key();
	Item[] room1Contents = new Item[100]; 
	
	public boolean itemInRoom(Item itemToFind, Item[] roomContents) {
		for (int n = 0; n < roomContents.length; n++)
			if (roomContents[n] == itemToFind)
				return true;
		return false;
	}
	
	@Test
	public void testAddItemValid() {
		testRoom1.addItem(testItem1);
		room1Contents = testRoom1.getRoomContents();
		Boolean inRoom = itemInRoom(testItem1, room1Contents); 
		
		assertTrue(inRoom);
		assertEquals(room1Contents.length, 1);
	}
	
	@Test
	public void testRemoveItemValid() {
		testRoom1.addItem(testItem1);
		room1Contents = testRoom1.getRoomContents();
		Boolean inRoom = itemInRoom(testItem1, room1Contents); 
		assertTrue(inRoom);
		assertEquals(room1Contents.length, 1);
		
		testRoom1.removeItem(testItem1);
		
		room1Contents = testRoom1.getRoomContents();
		inRoom = itemInRoom(testItem1, room1Contents); 
		assertFalse(inRoom);
		assertEquals(room1Contents.length, 0);
		
	}
	
	@Test
	public void testRemoveItemNotInRoom() {
		testRoom1.addItem(testItem2);
		room1Contents = testRoom1.getRoomContents();
		
		Boolean itemOneInRoom = itemInRoom(testItem1, room1Contents); 
		assertFalse(itemOneInRoom);
		Boolean itemTwoInRoom = itemInRoom(testItem2, room1Contents); 
		assertTrue(itemTwoInRoom);
		assertEquals(room1Contents.length, 1);
		
		testRoom1.removeItem(testItem1);
		
		room1Contents = testRoom1.getRoomContents();
		itemOneInRoom = itemInRoom(testItem1, room1Contents); 
		assertFalse(itemOneInRoom);
		assertEquals(room1Contents.length, 1);
		
	}
	
	@Test
	public void testEnterValid(){
		testRoom1.setDesc("Here");
		String result = testRoom1.enter(testPlayer);
		
		assertEquals(result, Constants.ROOM_ENTER);
		assertEquals(testPlayer.getLoc(), testRoom1);
	}
	
	
	@Test
	public void testEnterSameLocation(){
		testRoom1.setDesc("Here");
		testPlayer.setLoc(testRoom1);
		String result = testRoom1.enter(testPlayer);
		
		assertEquals(result, Constants.ROOM_ENTER);
		assertEquals(testPlayer.getLoc(), testRoom1);
	}
	
	@Test(expected=NullPointerException.class)
	public void testEnterNullPlayer(){
		testRoom1.setDesc("Here");
		testPlayer = null; 
		String result = testRoom1.enter(testPlayer);
		
		assertEquals(result, Constants.ROOM_ENTER);
		assertEquals(testPlayer.getLoc(), testRoom1);
	}
	
	@Test(expected=NullPointerException.class)
	public void testEnterNullRoom(){
		testRoom1 = null;
		String result = testRoom1.enter(testPlayer);
		
		assertEquals(result, Constants.ROOM_ENTER);
		assertEquals(testPlayer.getLoc(), testRoom1);
	}
	
	@Test
	public void testExitValid(){
		testPlayer.setLoc(testRoom1);
		testRoom1.setSide(Constants.S, testRoom2);
		testRoom2.setSide(Constants.N, testRoom1);
		
		testRoom1.exit(Constants.S, testPlayer);
		
		assertEquals(testRoom2, testPlayer.getLoc());
	}
	
	@Test
	public void testExitInValidDirection(){
		testPlayer.setLoc(testRoom1);
		testRoom1.setSide(Constants.S, testRoom2);
		testRoom2.setSide(Constants.N, testRoom1);
		
		testRoom1.exit(Constants.N, testPlayer);
		
		assertEquals(testRoom1, testPlayer.getLoc());
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testExitLowerBound(){
		testPlayer.setLoc(testRoom1);
		testRoom1.setSide(Constants.S, testRoom2);
		testRoom2.setSide(Constants.N, testRoom1);
		
		testRoom1.exit(-10, testPlayer);
		
		assertEquals(testRoom1, testPlayer.getLoc());
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testExitUpperBound(){
		testPlayer.setLoc(testRoom1);
		testRoom1.setSide(Constants.S, testRoom2);
		testRoom2.setSide(Constants.N, testRoom1);
		
		testRoom1.exit(100, testPlayer);
		
		assertEquals(testRoom1, testPlayer.getLoc());
	}
}
