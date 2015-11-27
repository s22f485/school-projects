package src.esof322.a4;

public class AdventureGameFactory extends AbstractFactory {

	@Override
	Adventure getAdventure(String adventureChoice) {
		Adventure returnAdventure;
		if (adventureChoice == "fantasy")
			returnAdventure = new FantasyAdventure();
		else if (adventureChoice == "boring") {
			returnAdventure = new BoringAdventure();
		} else {
			System.out.println("Adventure Game Factory");
			returnAdventure = null;
		}
		return returnAdventure;
	}
}
