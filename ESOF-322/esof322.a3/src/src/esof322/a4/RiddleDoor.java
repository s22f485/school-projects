package src.esof322.a4;

public class RiddleDoor extends Door {

	private String riddle;
	private String[] options;
	private String correctAnswer;
	private CaveSite outSite;
	private CaveSite inSite;
	private boolean alreadyAnswered = false;

	public RiddleDoor(CaveSite out, CaveSite in) {
		outSite = out;
		inSite = in;
	}

	public void setRiddle(String riddle, String[] options, String correctAnswer) {
		this.riddle = riddle;
		this.options = options;
		this.correctAnswer = correctAnswer;
	}

	public String enter(Player p) {
		return "RiddleDoor";
	}

	public String[] getOptions() {
		return options;
	}

	public String getRiddle() {
		return riddle;
	}

	public String getCorrect() {
		return correctAnswer;
	}

	public String actualEnter(Player p) {
		alreadyAnswered = true;
		if (p.getLoc() == outSite)
			inSite.enter(p);
		else if (p.getLoc() == inSite)
			outSite.enter(p);
		if (!alreadyAnswered)
			return "That was the correct answer!";
		else
			return "";
	}

	public Boolean getAnswered() {
		return alreadyAnswered;
	}
}
