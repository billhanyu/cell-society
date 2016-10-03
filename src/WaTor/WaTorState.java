package WaTor;

import javafx.scene.paint.Color;
import cell.State;

/**
 * @author Addison Howenstine
 * 
 * WaTor states also have chronons since 
 * reproducing, shark will have additional values
 */

public abstract class WaTorState extends State {
	
	protected int chrononsSinceReproduction;

	public WaTorState(Color c, String n, int chrononsSinceReproduction) {
		super(c, n);
		this.chrononsSinceReproduction = chrononsSinceReproduction;
	}
	
	public void incrementChrononsSinceReproduction() {
		chrononsSinceReproduction++;
	}
	
	public void zeroChrononsSinceReproduction() {
		chrononsSinceReproduction = 0;
	}
	
	public int getChrononsSinceReproduction() {
		return chrononsSinceReproduction;
	}
}
