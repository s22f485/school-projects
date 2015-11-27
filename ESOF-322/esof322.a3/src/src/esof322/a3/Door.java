// Team Workiva adapted and modified this file.
// Team Workiva is MacKenzie O'Bleness, Lisa Peters & Larry Lynn. 
package src.esof322.a3;

import java.io.Serializable;

/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 * 
 * To compile: javac AdventureGame.java To run: java AdventureGame
 * 
 * The main routine is AdventureGame.main
 * 
 **/

// class Door

public class Door implements CaveSite, Serializable {
	/**
	 * In this implementation doors are always locked. A player must have the
	 * correct key to get through a door. Doors automatically lock after a
	 * player passes through.
	 */

	private Key myKey;

	/** The door's location. */
	private CaveSite outSite;
	private CaveSite inSite;

	public Door() {

	}

	/** We can construct a door at the site. */
	Door(CaveSite out, CaveSite in, Key k) {
		outSite = out;
		inSite = in;
		myKey = k;
	}

	/** A player will need the correct key to enter. */
	public String enter(Player p) {
		if (p.haveItem(myKey)) {
			if (p.getLoc() == outSite)
				inSite.enter(p);
			else if (p.getLoc() == inSite)
				outSite.enter(p);

			return Constants.KEY_SUCCESS;
		} else {
			return Constants.KEY_FAILURE;
		}
	}

}
