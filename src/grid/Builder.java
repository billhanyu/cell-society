package grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import WaTor.WaTorState;
import cell.Cell;
import cell.GridPosition;
import cell.State;
import global.Initializer;
import grid.CellGraphic.GraphicType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.SimulationPane;

public abstract class Builder {
	protected double width;
	protected double height;
	protected double cellWidth;
	protected double cellHeight;
	protected int numRows;
	protected int numCols;
	protected Parameters param;

	private int top = 0;
	private int bottom;// = numRows - 1;
	private int left = 0;
	private int right;// = numCols - 1;

	protected List<Cell> cells;
	protected Map<Cell, CellGraphic> cellGrid;

	private Cell[][] neighborGrid;
	private State[][] copy;

	public Builder(Parameters param) {
		initHolders();
		this.param = param;
		readGridSize();
		width = Initializer.SCENE_HEIGHT - 20;
		height = width;
		//TODO change this height = width relationship later
	}

	public void setParameters(Parameters param) {
		this.param = param;
	}

	public Runner init() {
		readGridSize();
		initHolders();
		neighborGrid = new Cell[numRows][numCols];
		readParameters();
		prepareForInitCells();
		initCells();
		giveAllCellsNeighbors();
		keepCopy();
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

	public void reset() {
		for (Cell c: cells) {
			c.setCurrState(copy[c.getGridPosition().getRow()][c.getGridPosition().getCol()]);
			c.setFutureState(copy[c.getGridPosition().getRow()][c.getGridPosition().getCol()]);
		}
	}

	protected abstract Runner initRunner();

	protected abstract void readParameters();

	protected abstract void prepareForInitCells();
	
	protected void initCells() {
		// initialize grid of cells
		for(int r = 0; r < numRows; r++) {
			for(int c = 0; c < numCols; c++) {
				GridPosition gp = new GridPosition(r, c);
				Cell cell = initCell(gp);
				CellGraphic g = initCellGraphic(cell, gp, GraphicType.Rectangle);
				cells.add(cell);
				cellGrid.put(cell, g);
			}
		}
	};
	
	protected abstract Cell initCell(GridPosition gp);
	
	private CellGraphic initCellGraphic(Cell cell, GridPosition gp, GraphicType type) {
		switch (type) {
		case Rectangle:
			return initRectGraphic(cell, gp);
		case Triangle:
		case Hexagon:
		default:
			return null;
		}
	};
	
	private CellGraphic initRectGraphic(Cell cell, GridPosition gp) {
		int r = gp.getRow();
		int c = gp.getCol();
		Rectangle rect = new Rectangle(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
		CellGraphic g = new CellGraphic(gp);
		rect.setFill(cell.getCurrState().getColor());
		rect.setStroke(Color.BLACK);
		g.setGraphic(rect);
		return g;
	}

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
		// TOP ROW
		if (row == top) {
			// UPPER LEFT
			if(col == left)
				c.addNeighbor(neighborGrid[bottom][right]);
			else
				c.addNeighbor(neighborGrid[bottom][col - 1]);
			// UPPER RIGHT
			if(col == right)
				c.addNeighbor(neighborGrid[bottom][left]);
			else
				c.addNeighbor(neighborGrid[bottom][col + 1]);
		}
		// BOTTOM ROW
		if (row == bottom) {
			// BOTTOM LEFT
			if(col == left)
				c.addNeighbor(neighborGrid[top][right]);
			else
				c.addNeighbor(neighborGrid[top][col - 1]);
			// BOTTOM RIGHT
			if(col == right)
				c.addNeighbor(neighborGrid[top][left]);
			else
				c.addNeighbor(neighborGrid[top][col + 1]);
		}
		// LEFT COLUMN excluding 4 corners (grid corners already handled above)
		if (col == left) {
			// UPPER LEFT
			if(row != top)
				c.addNeighbor(neighborGrid[row - 1][right]);
			// BOTTOM LEFT
			if(row != bottom)
				c.addNeighbor(neighborGrid[row + 1][right]);
		}
		if (col == right) {
			// UPPER LEFT
			if(row != top)
				c.addNeighbor(neighborGrid[row - 1][left]);
			// BOTTOM LEFT
			if(row != bottom)
				c.addNeighbor(neighborGrid[row + 1][left]);
		}
	}

	private void initHolders() {
		cells = new ArrayList<Cell>();
		cellGrid = new HashMap<Cell, CellGraphic>();
	}

	private void readGridSize() {
		numRows = param.getRows();
		numCols = param.getCols();
		bottom = numRows - 1;
		right = numCols - 1;
	}

	private void keepCopy() {
		copy = new State[numRows][numCols];
		for (Cell c: cells) {
			State currState = c.getCurrState();
			if (currState instanceof WaTorState) {
				copy[c.getGridPosition().getRow()][c.getGridPosition().getCol()] = ((WaTorState) currState).copy();
			}
			else {
				copy[c.getGridPosition().getRow()][c.getGridPosition().getCol()] = currState;
			}
		}
	}
}