package Schilling;

import javafx.scene.paint.Color;
import cell.Cell;
import cell.State;

public class SchillingCell extends Cell{

	private double idealRatio; // between 0 and 1
	private State personX = new State(Color.RED, "X");
	private State personO = new State(Color.BLUE, "O");
	private State vacant = new State(Color.GRAY, "VACANT");

	
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
		for(Cell option : getNeighbors()) //this will put into first open cell, not random, will cause bunching at top?
			if(option.getFutureState().equals(vacant)){
				option.setFutureState(this.getCurrState());
				this.setFutureState(vacant);
			}
	}
}