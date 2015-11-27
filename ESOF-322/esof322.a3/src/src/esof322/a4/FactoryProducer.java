package src.esof322.a4;

public class FactoryProducer {

	public static AbstractFactory getFactory(String choice) {

		if (choice == "AdventureGame") {
			return new AdventureGameFactory();
		} else {
			return null;
		}

	}
}
