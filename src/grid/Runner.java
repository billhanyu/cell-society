package grid;

import java.util.List;

import cell.Cell;

public abstract class Runner {
	
	protected List<Cell> cells;
	protected List<CellGraphic> cellGrid;
	
	public Runner(List<Cell> cells, List<CellGraphic> cellGrid) {
		this.cells = cells;
		this.cellGrid = cellGrid;
	}
	
	public List<Cell> getCells(){
		return cells;
	}
	
	public List<CellGraphic> getGraphics() {
		return cellGrid;
	}
	
	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}
	
	public void setCellGrid(List<CellGraphic> list){
		cellGrid = list;
	}
					
	public void step(){
		for(Cell c : cells){
			c.checkChangeState();
		}
	}
	
	private void addNeighbors(){
		// TODO this
	}
}