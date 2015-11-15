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

}
