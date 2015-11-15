package src.esof322.a3;

import static org.junit.Assert.*;

import org.junit.Test;

import src.esof322.a3.Door;

public class testDoor {

	Room testRoom1 = new Room();
	Room testRoom2 = new Room();
	Key key = new Key(); 
	Player p = new Player(); 
	Door testDoor = new Door(testRoom1, testRoom2, key); 
	
	@Test
	public void testEnterKeyFailure() {
		String result = testDoor.enter(p); 
		assertEquals(result, Constants.KEY_FAILURE);
	}
	
	
	@Test
	public void testEnterSuccessOutSite(){
		p.setLoc(testRoom1);
		p.pickUp(key);
		String result = testDoor.enter(p);
		assertEquals(result, Constants.KEY_SUCCESS);
		
	}
	
	@Test
	public void testEnterSuccessInSite(){
		p.setLoc(testRoom2);
		p.pickUp(key);
		String result = testDoor.enter(p);
		assertEquals(result, Constants.KEY_SUCCESS);
		
	}
	
	@Test
	public void testEnterItemOtherThanKey() {
		p.setLoc(testRoom1);
		Item gold = new Item(); 
		p.pickUp(gold);
		String result = testDoor.enter(p); 
		assertEquals(result, Constants.KEY_FAILURE);
	}
	
	@Test
	public void testEnterNeitherInNorOutsite() {
		Room testRoom3 = new Room();
		p.setLoc(testRoom3);
		Item key = new Item(); 
		p.pickUp(key);
		String result = testDoor.enter(p); 
		assertEquals(result, Constants.KEY_FAILURE);
	}
	

}
