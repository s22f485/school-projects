package src.esof322.a3;

public class FantasyAdventure implements Adventure {

	public FantasyAdventure() {
	}

	private Room entrance;

	public Room createAdventure() {
		// The outside:
		Entrance outside = new Entrance();
		Treasure theTreasure = new Treasure();
		outside.setTreasure(theTreasure);
		outside.setDesc("You've found it! This is the cave rumored to contain Archmage Sedkarn's exceedingly valuable grimoire."
				+ " As a fledgeling mage yourself, you warily eye the steep downwards slope into the dark.");

		// Room 1:
		Room r1 = new Room();
		r1.setDesc("The darkness is pierced by a bright light overhead.\n"
				+ "There is a narrow, dark passage to the east (r1).");

		// Connect the outside to Room 1:
		outside.setSide(5, r1);
		r1.setSide(4, outside);
		Item lightRock = new Item();
		r1.addItem(lightRock);
		lightRock.setDesc("A light-rune enscribed rock.");
		entrance = outside;

		// Room 2:
		Room r2 = new Room();
		r2.setDesc("You are in a gloomy oval shaped room with grey walls.\n"
				+ "There is a dim light to the west, and a narrow\n"
				+ "dark hole to the east only about 18 inches high (r2).");

		// Room 3:
		String secondary = "You recognize the glyph on the wall to be the symbol for light, but that's all you can tell about the room itself because of how dark it is. ";
		String secondSecondary = "The glyphs between wall and rock have reacted, and the room brightens until you can see into every corner. ";
		HiddenItemsRoom r3 = new HiddenItemsRoom(secondary, secondSecondary);

		r3.setDesc("There is a wide passage that quickly narrows"
				+ "to the west, a bright opening to the east,"
				+ "and a deep hole that appears to have no bottom"
				+ "in the middle of the room (r3).");

		Item goldCoin = new Item();
		goldCoin.setDesc("A gold coin.");
		r3.addKeyValue(lightRock, goldCoin);
		// Connect Rooms 1, 2, & 3:
		r1.setSide(2, r2);
		r2.setSide(3, r1);
		r2.setSide(2, r3);
		r3.setSide(3, r2);

		// Room 4:
		Room r4 = new Room();
		r4.setDesc("This was clearly a labratory of some sort at one point in time. Old papers, glassware, and tools litter a rotting desk and a disappointingly empty bookcase leans against a wall."
				+ "  A passage leads to the west,\n"
				+ "another one to the north, and a slippery route\n"
				+ "goes down steeply. You can hear the shrieks of bats (r4).");

		// Room 5:
		Room r5 = new Room();
		r5.setDesc("There is a dim light from above and the shrieks\n"
				+ "are clearly coming from a passageway to the east (r5).");

		// Room 6:
		Room r6 = new Room();
		r6.setDesc("The ceiling is full of bats and by the footprints in the floor, somebody has been here recently.");
		Item discardedWand = new Item();
		discardedWand.setDesc("An old wand in need of repair.");
		r6.addItem(discardedWand);
		// Room 7:
		Room r7 = new Room();
		r7.setDesc("This room is very damp. There are puddles on the floor\n"
				+ "and a steady dripping from above but no other exits (r7).");

		// Connect rooms 3, 4, 5, 6, & 7.
		r3.setSide(2, r4);
		r3.setSide(5, r5);
		r4.setSide(3, r3);
		r4.setSide(5, r7);
		r5.setSide(4, r3);
		r5.setSide(2, r6);
		r6.setSide(3, r5);
		r7.setSide(4, r4);

		// Room 8:
		Room r8 = new Room();
		r8.setDesc("A lizard scampers past you, or is it a snake?\n"
				+ "a narrow passage runs to the east and an evin narrower one\n"
				+ "runs to the west (r8).");

		// Room 9:
		Room r9 = new Room();
		r9.setDesc("There is what appears to be the beginning of a carving of a river and cliff on one wall, but it is not finished (r9)."
				+ "Wait, is that a hidden passage behind the rotting tapestry hanging on the north wall?");

		r9.setSide(Constants.N, outside);

		// Room 10:
		Room r10 = new Room();
		r10.setDesc("There is a trapdoor in the middle of the floor with writing on it and an exit to west. You could move closer to examine it.(r10).");

		// Room 11:
		Room r11 = new Room();
		r11.setDesc("This room is very dark. You can just barely see the bookstand in the middle of the room (r11).");
		theTreasure.setDesc("Sarkan's Grimoire.");
		r11.addItem(theTreasure);

		// Lets connect them:
		r4.setSide(0, r8);
		r8.setSide(1, r4);
		r8.setSide(3, r9);
		r8.setSide(2, r10);
		r9.setSide(2, r8);
		r10.setSide(3, r8);

		// We add a door between r10 and r11:
		RiddleDoor theDoor = new RiddleDoor(r10, r11);
		String riddle = "I have a tail and I have a head but I have no body. What am I?";
		String[] options = { "A snake", "A coin", "A river", "Nevermind" };
		String correctAnswer = "A coin";
		theDoor.setRiddle(riddle, options, correctAnswer);
		r10.setRiddleDoor(theDoor);
		r11.setRiddleDoor(theDoor);

		r10.setSide(5, theDoor);
		r11.setSide(4, theDoor);

		entrance = outside;
		return entrance;

	}

}
