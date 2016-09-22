package grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	protected Map<Cell, CellGraphic> cellGrid;
	
	public Builder() {
		cells = new ArrayList<Cell>();
		cellGrid = new HashMap<Cell, CellGraphic>();
	}
	
	public abstract Runner init(Parameters param);
	
}
