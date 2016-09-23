package WaTor;

import javafx.scene.paint.Color;
import cell.State;

public class WaTorState extends State{
	
	protected int chrononsSinceReproduction;

	public WaTorState(Color c, String n, int chrononsSinceReproduction) {
		super(c, n);
		this.chrononsSinceReproduction = chrononsSinceReproduction;
	}
	
	public void incrementChrononsSinceReproduction(){
		chrononsSinceReproduction++;
	}
	
	public void zeroChrononsSinceReproduction(){
		chrononsSinceReproduction = 0;
	}
	
	public int getChrononsSinceReproduction(){
		return chrononsSinceReproduction;
	}


}
