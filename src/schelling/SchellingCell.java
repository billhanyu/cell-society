package schelling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.paint.Color;
import cell.Cell;
import cell.GridPosition;
import cell.State;

public class SchellingCell extends Cell{

	private double idealRatio = 0.5; // between 0 and 1
	public static State personX = new State(Color.RED, "X");
	public static State personO = new State(Color.BLUE, "O");
	public static State vacant = new State(Color.GRAY, "VACANT");
	private List<Cell> cells;


	public SchellingCell(GridPosition gridPosition, State s) {
		super(gridPosition, s);
	}

	@Override
	public void checkChangeState() {
		if(!this.getCurrState().equals(vacant) && !checkSatisfied()){
			moveToEmptyCell();
			return;
		}
	}

	private boolean checkSatisfied(){
		int numSimilar = 0;
		int numDifferent = 0;
		for(Cell neighbor : getNeighbors()){
			if (neighbor.getCurrState().equals(this.getCurrState()))
				numSimilar++;
			if (!(neighbor.getCurrState().equals(vacant) || neighbor.getCurrState().equals(this.getCurrState())))
				numDifferent++;
		}
		int numNeighbors = numSimilar + numDifferent;
		return ( numNeighbors == 0 || ((double) numSimilar / (double) (numNeighbors)) > getIdealRatio());
	}

	private void moveToEmptyCell(){
		List<Cell> toShuffle = new ArrayList<Cell>(cells);
		Collections.shuffle(toShuffle);
		for(Cell option : toShuffle)
			if(option.getFutureState().equals(vacant)){
				option.setFutureState(this.getCurrState());
				this.setFutureState(vacant);
				return;
			}
	}

	public void setCellsPointer(List<Cell> cells){
		this.cells = cells;
	}

	public double getIdealRatio() {
		return idealRatio;
	}

	public void setIdealRatio(double idealRatio) {
		this.idealRatio = idealRatio;
	}

	@Override
	protected Cell getCopiedCell(GridPosition gp, State state) {
		return new SchellingCell(gp, state);
	}
}