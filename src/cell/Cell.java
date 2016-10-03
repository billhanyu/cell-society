package cell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Addison Howenstine
 * 
 * A Cell has a current state and a future state
 * Models will use both current and future states
 * to reason whether to make a change. A cell will never
 * move in position on the screen—a cell can be thought of
 * as a house that stays in one place while states that occupy
 * the house/cell may change
 */

public abstract class Cell {
	
	private List<Cell> neighbors;
	private State currState;
	private State futureState;
	private GridPosition gridPos;
	
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
