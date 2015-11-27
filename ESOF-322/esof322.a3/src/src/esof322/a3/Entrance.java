package src.esof322.a3;

public class Entrance extends Room{

	private Item treasure; 
	
	public String enter(Player p){
		p.setLoc(this);
		if(p.haveItem(treasure)){
			return "You've made it out with the treasure!";
		}
		else
			return "i dunno";
		
	}
	
	public void setTreasure(Item treasure){
		this.treasure = treasure; 
	}
}
