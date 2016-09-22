package grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cell.Cell;

public abstract class Runner {
	
	protected List<Cell> cells;
	protected Map<Cell, CellGraphic> cellGrid;
	
	public List<Cell> getGrid(){
		return cells;
	}
		
	private void updateAllCellStates(){
		for(Cell c : cells)
			c.checkChangeState();
		for(Cell c : cells)
			c.updateState();
	}
	
	private void updateCellGrid(){
		for(Cell c: cells){
			CellGraphic cg = cellGrid.get(c);
			cg.setColor(c.getCurrState().getColor());
			cellGrid.put(c, cg);
		}
	}
	
	public void step(){
		updateAllCellStates();
		updateCellGrid();
	}
	
	private void addNeighbors(){
		// TODO this
	}
}