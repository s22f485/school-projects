// Team Workiva adapted and modified this file.
// Team Workiva is MacKenzie O'Bleness, Lisa Peters & Larry Lynn. 

/*Summary of changes for Constants:
 * A Constants class was added to hold such variables as direction-ints and string descriptions
 */
package src.esof322.a3;

import java.io.Serializable;

public class Constants implements Serializable {
	// String Constants
	public static String INTRODUCTION = "Welcome to the Adventure Game,\n"
			+ "which is inspired by an old game called the Colossal Cave Adventure.\n"
			+ "Java implementation Copyright (c) 1999 - 2012 by James M. Bieman";
	public static final String HANDS_FULL = "Your hands are full.";
	public static final String HANDS_EMPTY = "You have nothing to drop.";
	public static final String NO_PLAYER_ITEMS = "You don't have any items!";
	public static final String NO_ROOM_ITEMS = "There are no items in this room.";
	public static final String ROOM_EMPTY = "The room is empty.";
	public static final String KEY_SUCCESS = "Your key works! The door creaks open, \n and slams behind you after you pass through.";
	public static final String KEY_FAILURE = "You don't have the key for this door! \n Sorry.";
	public static final String ROOM_ENTER = "You enter the room.";
	public static final String HIT_WALL = "Ouch! That hurts.";
	

	// Int constants
	public static final int N = 0;
	public static final int S = 1;
	public static final int E = 2;
	public static final int W = 3;
	public static final int U = 4;
	public static final int D = 5;
	
	// Enums
	public enum ItemTypes {Key, Gold};
}
