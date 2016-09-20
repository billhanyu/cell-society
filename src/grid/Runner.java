package grid;

import java.util.ArrayList;
import cell.Cell;

public abstract class Runner {
	
	protected ArrayList<Cell> grid;
	
	public ArrayList<Cell> getGrid(){
		return grid;
	}
	
	//TODO: figure out how to use Collections.unmodifiableList(getValues());
	
	//TODO: adding neighbors
		
	public abstract void init(int rows, int cols);
	
	public void step(){
		for(Cell c : grid){
			c.checkChangeState();
		}
	}

}
