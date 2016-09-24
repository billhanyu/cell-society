package schelling;

import javafx.scene.paint.Color;
import cell.Cell;
import cell.GridPosition;
import cell.State;

public class SchellingCell extends Cell{

	private double idealRatio; // between 0 and 1
	public static State personX = new State(Color.RED, "X");
	public static State personO = new State(Color.BLUE, "O");
	public static State vacant = new State(Color.GRAY, "VACANT");

	
	public SchellingCell(GridPosition gridPosition, State s) {
		super(gridPosition, s);
	}

	@Override
	public void checkChangeState() {
		if(!checkSatisfied())
			moveToEmptyCell();
	}

	private boolean checkSatisfied(){
		int numSimilar = 0;
		for(Cell neighbor : getNeighbors())
			if (neighbor.getCurrState().equals(this.getCurrState()))
				numSimilar++;
		return ( (double) numSimilar / (double) getNeighbors().size() > idealRatio);
	}

	private void moveToEmptyCell(){
		// TODO replace getNeighbors with a way to loop through all cells, not just neighbors
		for(Cell option : getNeighbors())
			if(option.getFutureState().equals(vacant)){
				option.setFutureState(this.getCurrState());
				this.setFutureState(vacant);
			}
	}
}