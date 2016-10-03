package SpreadingFire;

import javafx.scene.paint.Color;
import cell.Cell;
import cell.GridPosition;
import cell.State;

/**
 * @author Addison Howenstine
 * 
 * Main cell implementation for SpreadingFire
 */

public class SpreadingFireCell extends Cell{

	private double probCatch;
	
	// DEFAULT STATES
	public static State burning = new State(Color.RED, "BURNING");
	public static State tree = new State(Color.GREEN, "TREE");
	public static State empty = new State(Color.YELLOW, "EMPTY");

	public SpreadingFireCell(GridPosition gp, State s) {
		super(gp, s);
	}

	/*
	 * checks whether cell should catch fire
	 * or be set to empty
	 */
	@Override
	public void checkChangeState() {
		if(this.getCurrState().equals(burning)) {
			this.setFutureState(empty);
		}
		else if (this.getCurrState().equals(tree)){
			for(Cell neighbor : getNeighbors()) {
				if (neighbor.getCurrState().equals(burning) && checkIfFlamable()) {
					this.setFutureState(burning);
				}
			}
		}
	}

	public void setProbCatch(double pc){
		probCatch = pc;
	}

	private boolean checkIfFlamable(){
		return Math.random() < probCatch;
	}
}