package src.esof322.a3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable {

	public void saveGame(AdventureGameModelFacade model){
		System.out.println("Trying to save.");
		
		try{  // Catch errors in I/O if necessary.
		// Open a file to write to, named SavedObj.sav.
		FileOutputStream saveFile=new FileOutputStream("/Users/School/Desktop/SaveObj.sav");

		// Create an ObjectOutputStream to put objects into save file.
		ObjectOutputStream save = new ObjectOutputStream(saveFile);

		// Now we do the save.
		save.writeObject(model);

		// Close the file.
		save.close(); // This also closes saveFile.
		}
		catch(Exception exc){
		exc.printStackTrace(); // If there was an error, print the info.
		}
	}
	
	public AdventureGameModelFacade loadGame(){

		AdventureGameModelFacade model = new AdventureGameModelFacade(1); 

		// Wrap all in a try/catch block to trap I/O errors.
		try{
		// Open file to read from, named SavedObj.sav.
		FileInputStream saveFile = new FileInputStream("/Users/School/Desktop/SaveObj.sav");

		// Create an ObjectInputStream to get objects from save file.
		ObjectInputStream save = new ObjectInputStream(saveFile);
		model = (AdventureGameModelFacade) save.readObject();

		// Close the file.
		save.close(); // This also closes saveFile.
		
		}
		catch(Exception exc){
		exc.printStackTrace(); // If there was an error, print the info.
		}

		return model;

		}
		
}



