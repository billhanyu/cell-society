package grid;

import java.util.ArrayList;
import cell.Cell;

public abstract class Runner {
	
	protected ArrayList<Cell> grid;
	
	public ArrayList<Cell> getGrid(){
		return grid;
	}
				
	public abstract void init(int rows, int cols);
	
	public void step(){
		for(Cell c : grid){
			c.checkChangeState();
		}
	}
	
	private void addNeighbors(){
		// TODO this
	}

}
