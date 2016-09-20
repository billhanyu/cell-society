package cell;

import java.util.ArrayList;

public abstract class Cell {
	private CellGraphic graphic;
	private ArrayList<Cell> neighbors;
	
	public Cell(CellGraphic graphic) {
		setGraphic(graphic);
		neighbors = new ArrayList<Cell>();
	}
	
	public void setGraphic(CellGraphic graphic) {
		this.graphic = graphic;
	}
	
	public CellGraphic getGraphic() {
		return graphic;
	}
	
	public void emptyNeighbors() {
		neighbors = new ArrayList<Cell>();
	}
	
	public void addNeighbor(Cell cell) {
		if (neighbors.contains(cell)) return;
		neighbors.add(cell);
	}
	
	public boolean checkChangeState() {
		//TODO: implement this
		return false;
	}
}
