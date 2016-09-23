package grid;

import java.util.List;
import java.util.Map;

import cell.Cell;

public abstract class Runner {

	protected List<Cell> cells;
	protected Map<Cell, CellGraphic> cellGrid;

	public Runner(List<Cell> cells, Map<Cell, CellGraphic> cellGrid) {
		this.cells = cells;
		this.cellGrid = cellGrid;
	}

	public List<Cell> getCells(){
		return cells;
	}

	public Map<Cell, CellGraphic> getGraphics() {
		return cellGrid;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	public void setCellGrid(Map<Cell, CellGraphic> list){
		cellGrid = list;
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
	
	public void start(){
		// TODO update animations
	}
	
	// TODO get animation working
	
	// TODO move animation stuff from Main to here
	
	// TODO method to set step
	
	// TODO start, step, stop, reset
}