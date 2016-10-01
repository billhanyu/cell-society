package langton;

import javafx.scene.paint.Color;
import cell.State;

public class LangtonState extends State{
	
	Orientation pointing;
	Instruction instruction;

	// TODO is this how you use enum types?
	public LangtonState(Color c, String n, Orientation o) {
		super(c, n);
		this.pointing = o;
	}

}
