package gameOfLife;

import javafx.scene.paint.Color;
import cell.Cell;
import cell.GridPosition;
import cell.State;

public class GameOfLifeCell extends Cell{

	public static State alive = new State(Color.WHITE, "ALIVE");
	public static State dead = new State(Color.BLACK, "DEAD");
	private final int underPopulation = 1;
	private final int overPopulation = 4;
	private final int reproduction = 3;



	public GameOfLifeCell(GridPosition gp, State s) {
		super(gp, s);
	}

	@Override
	public void checkChangeState() {
		int numLivingNeighbors = 0;
		for (Cell n : getNeighbors())
			if (n.getCurrState().equals(alive))
				numLivingNeighbors ++;
		if(this.currState.equals(alive)){
			if (numLivingNeighbors <= underPopulation)
				this.setFutureState(dead);
			if (numLivingNeighbors >= overPopulation)
				this.setFutureState(dead);
		}
		if(this.currState.equals(dead)){
			if (numLivingNeighbors == reproduction){
				this.setFutureState(alive);
			}
		}
	}
}
