package src.esof322.a3;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RiddleDoorPopup {
	private int input;
	private String[] options;
	private String riddle;

	public RiddleDoorPopup(String[] options, String riddle) {
		JFrame frame = new JFrame();
		Icon blueIcon = new ImageIcon("yourFile.gif");
		this.options = options;
		this.riddle = riddle;
		input = JOptionPane.showOptionDialog(null, riddle, "Confirmation",
				JOptionPane.PLAIN_MESSAGE, 0, null, options, options[3]);

	}

	public String getInput() {
		return options[input];
	}

	public void printOptions(Object[] stringArray) {
		for (Object s : stringArray) {
			System.out.println(s);
		}
	}
}