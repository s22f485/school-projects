package src.esof322.a3;

import static org.junit.Assert.*;

import org.junit.Test;

public class testPlayer {

	Player testPlayer = new Player();
	Room testRoom1 = new Room();
	Room testRoom2 = new Room();
	Item testItem1 = new Item(); 
	Item testItem2 = new Item();
	Item testItem3 = new Item(); 


	@Test
	public void testGoValidDirection() {
		testRoom1.setSide(Constants.S, testRoom2);
		testRoom2.setSide(Constants.N, testRoom1);

		testPlayer.setLoc(testRoom1);
		testPlayer.go(Constants.S);
		Room result = testPlayer.getLoc();
		assertEquals(result, testRoom2);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testGoInValidDirectionPositive() {
		testRoom1.setSide(Constants.S, testRoom2);
		testRoom2.setSide(Constants.N, testRoom1);

		testPlayer.setLoc(testRoom1);
		testPlayer.go(10);
		Room result = testPlayer.getLoc();
		assertNotEquals(result, testRoom2);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testGoInValidDirectionNegative() {
		testRoom1.setSide(Constants.S, testRoom2);
		testRoom2.setSide(Constants.N, testRoom1);

		testPlayer.setLoc(testRoom1);
		testPlayer.go(-10);
		Room result = testPlayer.getLoc();
		assertNotEquals(result, testRoom2);
	}

	@Test(expected=NullPointerException.class)
	public void testGoPlayerHasNoLoc() {
		testRoom1.setSide(Constants.S, testRoom2);
		testRoom2.setSide(Constants.N, testRoom1);

		testPlayer.go(Constants.S);
		Room result = testPlayer.getLoc();
		assertNotEquals(result, testRoom2);
	}

	@Test
	public void testGoThroughDoorWithoutKey() {
		testRoom1.setSide(Constants.S, testRoom2);
		testRoom2.setSide(Constants.N, testRoom1);
		testRoom2.setDesc("room 2");
		Key testKey = new Key();
		Door testDoor = new Door(testRoom1, testRoom2, testKey);

		testRoom1.setSide(5, testDoor);
		testRoom2.setSide(4, testDoor);

		testPlayer.setLoc(testRoom1);
		testPlayer.go(Constants.S);
		Room result = testPlayer.getLoc();
		assertNotEquals(result, testRoom2);

	}

	@Test
	public void testGoThroughDoorWithKey() {
		testRoom1.setSide(Constants.S, testRoom2);
		testRoom2.setSide(Constants.N, testRoom1);
		testRoom2.setDesc("room 2");
		Key testKey = new Key();
		Door testDoor = new Door(testRoom1, testRoom2, testKey);
		testRoom1.setSide(5, testDoor);
		testRoom2.setSide(4, testDoor);

		testPlayer.setLoc(testRoom1);
		testPlayer.pickUp(testKey);
		testPlayer.go(Constants.S);

		Room result = testPlayer.getLoc();
		assertEquals(result, testRoom2);

	}
	
	@Test
	public void testPickUpValid(){
		testRoom1.addItem(testItem1);
		testPlayer.setLoc(testRoom1);
		testPlayer.pickUp(testItem1);
		
		Boolean haveItem = testPlayer.haveItem(testItem1);
		Item[] roomContents = testRoom1.getRoomContents();
		
		assertEquals(roomContents.length, 0);
		assertTrue(haveItem);
	}
	
	@Test
	public void testPickUpMoreThanCanCarry(){
		testRoom1.addItem(testItem1);
		testRoom1.addItem(testItem2);
		testRoom1.addItem(testItem3);
		
		testPlayer.setLoc(testRoom1);
		testPlayer.pickUp(testItem1);
		testPlayer.pickUp(testItem2);
		testPlayer.pickUp(testItem3);
		Item[] roomContents = testRoom1.getRoomContents();
		
		assertEquals(roomContents.length, 1);
		assertTrue(testPlayer.haveItem(testItem1));
		assertTrue(testPlayer.haveItem(testItem2));
		assertFalse(testPlayer.haveItem(testItem3));
	}
	
	@Test
	public void testPickUpItemNotInRoom(){
		
		testRoom1.addItem(testItem2);
		
		testPlayer.setLoc(testRoom1);
		testPlayer.pickUp(testItem1);
		
		assertFalse(testPlayer.haveItem(testItem1));
		
	}
	
	@Test
	public void testDropValidFirstItem(){
		testRoom1.addItem(testItem1);
		testPlayer.setLoc(testRoom1);
		testPlayer.pickUp(testItem1);
		
		
		testPlayer.setLoc(testRoom2);
		Item[] roomContents = testRoom2.getRoomContents();
		assertTrue(testPlayer.haveItem(testItem1));
		assertEquals(roomContents.length, 0);
		
		testPlayer.drop(1);
		roomContents = testRoom2.getRoomContents();
		
		assertFalse(testPlayer.haveItem(testItem1));
		assertEquals(roomContents.length, 1);
		
	}
	
	@Test
	public void testDropValidSecondItem(){
		testRoom1.addItem(testItem1);
		testRoom1.addItem(testItem2);
		testPlayer.setLoc(testRoom1);
		testPlayer.pickUp(testItem1);
		testPlayer.pickUp(testItem2);
		
		
		testPlayer.setLoc(testRoom2);
		Item[] roomContents = testRoom2.getRoomContents();
		assertTrue(testPlayer.haveItem(testItem2));
		assertEquals(roomContents.length, 0);
		
		testPlayer.drop(2);
		roomContents = testRoom2.getRoomContents();
		
		assertFalse(testPlayer.haveItem(testItem2));
		assertEquals(roomContents.length, 1);
		
	}
	
	@Test
	public void testDropWithoutItem(){
		testPlayer.setLoc(testRoom1);
		
		
		Item[] roomContents = testRoom1.getRoomContents();
		assertFalse(testPlayer.haveItem(testItem1));
		assertEquals(roomContents.length, 0);
		
		testPlayer.drop(1);
		roomContents = testRoom1.getRoomContents();
		
		assertFalse(testPlayer.haveItem(testItem1));
		assertEquals(roomContents.length, 0);
	}
	
	@Test
	public void testDropUpperBound(){
		testRoom1.addItem(testItem1);
		testPlayer.setLoc(testRoom1);
		testPlayer.pickUp(testItem1);
		
		
		testPlayer.setLoc(testRoom2);
		Item[] roomContents = testRoom2.getRoomContents();
		assertTrue(testPlayer.haveItem(testItem1));
		assertEquals(roomContents.length, 0);
		
		testPlayer.drop(2);
		
		
	}
	
	@Test
	public void testDropLowerBound(){
		testRoom1.addItem(testItem1);
		testPlayer.setLoc(testRoom1);
		testPlayer.pickUp(testItem1);
		
		
		testPlayer.setLoc(testRoom2);
		Item[] roomContents = testRoom2.getRoomContents();
		assertTrue(testPlayer.haveItem(testItem1));
		assertEquals(roomContents.length, 0);
		
		testPlayer.drop(-2);
		
		
	}
}
