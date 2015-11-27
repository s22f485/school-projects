package src.esof322.a3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HiddenItemsRoom extends Room{
	
	private Map <Item, Item> hiddenItems = new HashMap(); 
	private String secondaryDescription; 
	private String secondaryDescriptionUpdated; 
	private boolean alreadyPickedUp = false; 
	
	public HiddenItemsRoom(String a, String b){
		this.secondaryDescription = a; 
		this.secondaryDescriptionUpdated = b; 
	}
	
	public void revealHidden(ArrayList<Item> playersItems){
		for(Item item: playersItems)
		if(hiddenItems.containsKey(item) && !alreadyPickedUp){
			super.addItem(hiddenItems.get(item)); 
			alreadyPickedUp = true; 
			secondaryDescriptionUpdate();
		}
	}
	
	public void addKeyValue(Item key, Item value){
		hiddenItems.put(key, value);
	}
	
	public void secondaryDescriptionUpdate(){
		secondaryDescription = secondaryDescriptionUpdated; 
		
	}
	
	public String getDesc(){
		return secondaryDescription + super.getDesc(); 
	}
		
	

}
