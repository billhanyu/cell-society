package gameOfLife;

import javafx.scene.paint.Color;
import cell.Cell;
import cell.GridPosition;
import cell.State;

/**
 * @author Addison Howenstine
 * 
 * Main cell for GameOfLife
 */

public class GameOfLifeCell extends Cell{
	
	// DEFAULT STATES
	public static State alive = new State(Color.BLACK, "ALIVE");
	public static State dead = new State(Color.WHITE, "DEAD");
	private final int underPopulation = 2;
	private final int overPopulation = 3;
	private final int reproduction = 3;

	public GameOfLifeCell(GridPosition gp, State s) {
		super(gp, s);
	}

	/* 
	 * Set future state of cell to
	 * alive or dead based on number of living neighbors
	 */
	@Override
	public void checkChangeState() {
		int numLivingNeighbors = 0;
		for (Cell n : this.getNeighbors())
			if (n.getCurrState().equals(alive))
				numLivingNeighbors ++;
		if(this.getCurrState().equals(alive)){
			if (numLivingNeighbors < underPopulation)
				this.setFutureState(dead);
			if (numLivingNeighbors > overPopulation)
				this.setFutureState(dead);
		}
		if(this.getCurrState().equals(dead)){
			if (numLivingNeighbors == reproduction){
				this.setFutureState(alive);
			}
		}
	}
}
