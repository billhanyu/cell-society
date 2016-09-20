package cell;

import java.util.ArrayList;

public abstract class Cell {
	
	protected ArrayList<Cell> neighbors;
	protected State currState;
	protected State futureState;
	protected GridPosition gridPos;
	
	public Cell(){
		
	}
	
	public Cell(State s){
		currState = s;
		futureState = s;
	}
	
	public Cell(GridPosition gp){
		gridPos = gp;
	}
	
	public ArrayList<Cell> getNeighbors(){
		return neighbors;
	}
	
	public State getCurrState(){
		return currState;
	}
	public State getFutureState(){
		return futureState;
	}
	public void setFutureState(State f){
		futureState = f;
	}
	
	public void updateState(){
		currState = futureState;
	}
	public GridPosition getGridPosition(){
		return gridPos;
	}
	
	public abstract void checkChangeState();

	
}
