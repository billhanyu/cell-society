package cell;

import java.util.ArrayList;
import java.util.List;

public abstract class Cell {
	
	protected List<Cell> neighbors;
	protected State currState;
	protected State futureState;
	protected GridPosition gridPos;
	
	public Cell(GridPosition gp, State s){
		this(s);
		gridPos = gp;
	}
	
	public Cell(State s){
		currState = s;
		futureState = s;
		neighbors = new ArrayList<Cell>();
	}
	
	public Cell(GridPosition gp){
		gridPos = gp;
		neighbors = new ArrayList<Cell>();
	}
	
	public List<Cell> getNeighbors(){
		return neighbors;
	}
	
	public void addNeighbor(Cell c){
		neighbors.add(c);
	}
	
	public State getCurrState(){
		return currState;
	}
	
	public State getFutureState(){
		return futureState;
	}
	
	public void setCurrState(State f){
		currState = f;
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
