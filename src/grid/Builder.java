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
	
	List<CellGraphic> visualGrid = new ArrayList<CellGraphic>();
	
	public Runner init(int rows, int cols) {
		//TODO: initialize CellGraphic corresponding to each Cell in cellAL passed by runner 
		return null;
	}
	
}
