package src.esof322.a3;

public class BoringAdventureGameFactory extends AdventureGameFactory{
	
	public BoringAdventureGameFactory(){
		System.out.println("Player at level 0."); 
	}
	
	public Player createPlayer() {
		Player thePlayer = new Player(); 
		return thePlayer; 
	}
	
	public Adventure createAdventure() {
		Adventure theAdventure = new Adventure(); 
		return theAdventure; 
	}
	
	public Room createRoom(){
		Room theRoom = new Room();
		return theRoom; 
	}

	public Key createKey() {
		Key theKey = new Key();
		return theKey; 
	}
	
	public Door createDoor(Room r1, Room r2, Key key){
		Door theDoor = new Door(r1, r2, key); 
		return theDoor; 
	}
}
