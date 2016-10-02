package Sugarscape;

import javafx.scene.paint.Color;
import cell.State;

public class AgentState extends State {
	
	private int sugar; // between 5 and 25
	private int visibility; // between 1 and 6
	private int metabolism; // between 1 and 4
	
	public static Color myColor = Color.RED;
	public static String myName = "AGENT";
	
	public AgentState(int sugar, int visibility, int metabolism) {
		super(myColor, myName);
		this.sugar = sugar;
		this.visibility = visibility;
		this.metabolism = metabolism;
	}

	public AgentState(Color c, String n) {
		super(c, n);
	}
	
	public int getVisibility() {
		return visibility;
	}
	
	public void addSugar(int s) {
		sugar += s;
	}
	
	public void decreaseMetabolism() {
		sugar -= metabolism;
	}
	
	public int getSugar() {
		return sugar;
	}
	
	public AgentState clone() {
		return new AgentState(sugar, visibility, metabolism);
	}

}
