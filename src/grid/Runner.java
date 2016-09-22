package grid;

import java.util.ArrayList;
import java.util.List;

import cell.Cell;

public abstract class Runner {
	
	protected List<Cell> cells;
	protected List<CellGraphic> cellGrid;
	
	public List<Cell> getGrid(){
		return cells;
	}
	
	public void setCellGrid(List<CellGraphic>);
					
	public void step(){
		for(Cell c : cells){
			c.checkChangeState();
		}
	}
	
	private void addNeighbors(){
		// TODO this
	}
}