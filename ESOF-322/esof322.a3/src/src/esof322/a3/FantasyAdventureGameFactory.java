package src.esof322.a3;

public class FantasyAdventureGameFactory extends AdventureGameFactory {

	public FantasyAdventureGameFactory() {
		System.out.println("Fantastical not yet implemented."); 
	}
	
	public Player createPlayer() {
		System.out.println("Player not yet implemented correctly.");
		Player thePlayer = new Player(); 
		return thePlayer; 
	}
	
	public Adventure createAdventure() {
		System.out.println("Adventure not yet implemented correctly.");
		FantasyAdventure theAdventure = new FantasyAdventure(); 
		return theAdventure; 
	}
	
	public Room createRoom() {
		System.out.println("Room not yet implemented correctly.");
		Room theRoom = new Room();
		return theRoom; 
	}
	
	public Key createKey() {
		System.out.println("Room not yet implemented correctly.");
		Key theKey = new Key();
		return theKey; 
	}
	
	public Door createDoor(Room r1, Room r2, Key key){
		System.out.println("Door not yet implemented correctly.");
		Door theDoor = new Door(r1, r2, key); 
		return theDoor; 
	}
}
