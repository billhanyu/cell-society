package Sugarscape;

import javafx.scene.paint.Color;
import cell.State;

public class PatchState extends State{
	
	private int howFull;

	public PatchState(Color c, String n, int howFull) {
		super(c, n);
		this.howFull = howFull;
	}
	
	public int getHowFull(){
		return howFull;
	}

}
