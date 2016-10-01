package Sugarscape;

import javafx.scene.paint.Color;
import cell.State;

public class AgentState extends State{
	
	private int sugar; // between 5 and 25
	private int visibility; // between 1 and 6
	private int metabolism; // between 1 and 4
	
	public Color occupied = Color.RED;
	

	public AgentState(Color c, String n) {
		super(c, n);
		// TODO Auto-generated constructor stub
	}
	
	public int getVisibility(){
		return visibility;
	}
	
	public void addSugar(int s){
		sugar += s;
	}
	
	public void decreaseMetabolism(){
		sugar -= metabolism;
	}
	
	public int getSugar(){
		return sugar;
	}

}
