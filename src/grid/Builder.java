package grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cell.Cell;
import global.Initializer;
import ui.SimulationPane;

public abstract class Builder {
	protected double width;
	protected double height;
	protected double cellWidth;
	protected double cellHeight;
	protected int numRows;
	protected int numCols;
	protected Parameters param;

	protected List<Cell> cells;
	protected Map<Cell, CellGraphic> cellGrid;
	private Cell[][] neighborGrid;

	public Builder(Parameters param) {
		cells = new ArrayList<Cell>();
		cellGrid = new HashMap<Cell, CellGraphic>();
		this.param = param;
		numRows = param.getRows();
		numCols = param.getCols();
		width = Initializer.SCENE_HEIGHT - 20;
		height = width;
		//TODO change this height = width relationship later
	}

	public void setParameters(Parameters param) {
		this.param = param;
	}

	public Runner init() {
		numRows = param.getRows();
		numCols = param.getCols();
		cells = new ArrayList<Cell>();
		cellGrid = new HashMap<Cell, CellGraphic>();
		neighborGrid = new Cell[numRows][numCols];
		readParameters();
		initCells();
		giveAllCellsNeighbors();
		return initRunner();
	};
	
	public SimulationPane getSimulationPane() {
		SimulationPane pane = new SimulationPane(width, height);
		for (CellGraphic graphic: cellGrid.values()) {
			pane.addShape(graphic.getGraphic());
		}
		return pane;
	};
	
	public void giveAllCellsNeighbors(){
		for (Cell c: cells) {
			neighborGrid[c.getGridPosition().getRow()][c.getGridPosition().getCol()]
					= c;
		}
		for (Cell c: cells) {
			addAllNeighbors(c);
		}
		neighborGrid = null;
	}

	protected abstract Runner initRunner();
	
	protected abstract void readParameters();

	protected abstract void initCells();

	protected abstract void addAllNeighbors(Cell c);

	protected void addSidesAsNeighbors(Cell c){
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		if (row > 0) {
			c.addNeighbor(neighborGrid[row - 1][col]);
		}
		if (row < numRows - 1) {
			c.addNeighbor(neighborGrid[row + 1][col]);
		}
		if (col > 0) {
			c.addNeighbor(neighborGrid[row][col - 1]);
		}
		if (col < numCols - 1) {
			c.addNeighbor(neighborGrid[row][col + 1]);
		}
	}

	protected void addSidesAcrossBoardAsNeighbors(Cell c){
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		if (row == 0) {
			c.addNeighbor(neighborGrid[numRows - 1][col]);
		}
		if (row == numRows - 1) {
			c.addNeighbor(neighborGrid[0][col]);
		}
		if (col == 0) {
			c.addNeighbor(neighborGrid[row][numCols - 1]);
		}
		if (col == numCols - 1) {
			c.addNeighbor(neighborGrid[row][0]);
		}
	}

	protected void addCornersAsNeighbors(Cell c){
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		if (row > 0 && col > 0) {
			c.addNeighbor(neighborGrid[row - 1][col - 1]);
		}
		if (row < numRows - 1 && col < numCols - 1) {
			c.addNeighbor(neighborGrid[row + 1][col + 1]);
		}
		if (col > 0 && row < numRows - 1) {
			c.addNeighbor(neighborGrid[row + 1][col - 1]);
		}
		if (col < numCols - 1 && row > 0) {
			c.addNeighbor(neighborGrid[row - 1][col + 1]);
		}
	}

	protected void addCornersAcrossBoardAsNeighbors(Cell c){
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		if (row == 0 && col == 0) {
			c.addNeighbor(neighborGrid[numRows - 1][numCols - 1]);
		}
		if (row == 0 && col == numCols - 1) {
			c.addNeighbor(neighborGrid[numRows - 1][0]);
		}
		if (row == numRows - 1 && col == 0) {
			c.addNeighbor(neighborGrid[0][numCols - 1]);
		}
		if (row == numRows - 1 && col == numCols - 1) {
			c.addNeighbor(neighborGrid[0][0]);
		}
	}
}