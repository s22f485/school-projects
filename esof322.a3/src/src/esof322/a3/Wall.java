// Team Workiva adapted and modified this file.
// Team Workiva is MacKenzie O'Bleness, Lisa Peters & Larry Lynn. 
package src.esof322.a3;

/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 * 
 * To compile: javac AdventureGame.java To run: java AdventureGame
 * 
 * The main routine is AdventureGame.main
 * 
 **/

// class Wall

public class Wall implements CaveSite {

	public String enter(Player p) {
		return Constants.HIT_WALL;
	}

}
