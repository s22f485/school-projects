package src.esof322.a3;

import java.io.IOException;
import javax.swing.*;
import BreezySwing.*;

public class StartGameView extends GBFrame{


	private JButton levelOne = addButton("Level One", 2, 4, 1, 1);
	private JButton levelTwo = addButton("Level Two", 2, 5, 1, 1);
	private JButton load = addButton("Load", 2, 6, 1, 1);
	private static boolean loadLast; 
	private static int level;
	
	
	public static void main(String[] args) throws IOException {
		JFrame newGameView = new StartGameView(); 
		newGameView.setTitle("Choose a level or load last saved game");
		newGameView.setSize(350, 100); /* was 400, 250 */
		newGameView.setVisible(true);
		

	}
		public void buttonClicked(JButton buttonObj) {
			if (buttonObj == levelOne) {
				level = 1; 
				startGame(); 
			} else if (buttonObj == levelTwo) {
				level = 2;
				startGame(); 
			}
			else if (buttonObj == load) {
				level = 1; 
				loadLast = true; 
				startGame(); 
			}
			
		
	}
		public void startGame(){
			setVisible(false);
			dispose(); 
			JFrame view = new AdventureGameView(level, loadLast);
			view.setSize(800, 600); /* was 400, 250 */
			view.setVisible(true);
		}
	

}
