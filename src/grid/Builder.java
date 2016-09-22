package grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cell.Cell;

public abstract class Builder {
	protected double width;
	protected double height;
	protected double cellWidth;
	protected double cellHeight;
	protected double startX; // top left corner of the grid
	protected double startY;
	protected int numRows;
	protected int numCols;
	protected Parameters param;
	
	protected List<Cell> cells;
	protected Map<Cell, CellGraphic> cellGrid;
	
	public Builder(Parameters param) {
		cells = new ArrayList<Cell>();
		cellGrid = new HashMap<Cell, CellGraphic>();
		this.param = param;
		numRows = param.getRows();
		numCols = param.getCols();
	}
	
	public abstract Runner init();
	
	protected abstract void initCells();
	
	protected abstract void initCellGrid();
	
}
