package src.esof322.a3;

public class AdventureGameFactory {
	private int level = 0; 
	
	Player createPlayer(){
		System.out.println("Abstract problems.");
		return null; 
	}
	Adventure createAdventure(){
		System.out.println("Abstract problems.");
		return null; 
	}

	Room createRoom(){
		System.out.println("Abstract problems.");
		return null;
	}
	
	public Key createKey() {
		System.out.println("Abstract problems.");
		return null;
	}
	
	public Door createDoor(Room r1, Room r2, Key key) {
		System.out.println("Abstract problems.");
		return null;
	}
}
