package grid;

import java.util.ArrayList;
import java.util.List;

import cell.Cell;

public abstract class Builder {
	private double width;
	private double height;
	private double cellWidth;
	private double cellHeight;
	private double startX; // top left corner of the grid
	private double startY;
	private int numRows;
	private int numCols;
	
	protected List<Cell> cells;
	protected List<CellGraphic> cellGrid;
	
	public Builder() {
		cells = new ArrayList<Cell>();
		cellGrid = new ArrayList<CellGraphic>();
	}
	
	public abstract Runner init(Parameters param);
	
}
