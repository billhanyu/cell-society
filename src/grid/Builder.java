package grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cell.Cell;
import cell.GridPosition;
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
		cellGrid = new HashMap<Cell, CellGraphic>();
		return initRunner();
	};

	protected abstract Runner initRunner();

	protected abstract void initCells();

	protected SimulationPane getSimulationPane() {
		SimulationPane pane = new SimulationPane(width, height);
		for (CellGraphic graphic: cellGrid.values()) {
			pane.addShape(graphic.getGraphic());
		}
		return pane;
	};

	protected abstract void addAllNeighbors(Cell c);

	protected void addSidesAsNeighbors(Cell c){
		// LEFT
		if(c.getGridPosition().getRow() > 0)
			for(Cell other : cells){
				if(other.getGridPosition().equals(new GridPosition(c.getGridPosition().getRow() - 1, c.getGridPosition().getCol())))
					c.addNeighbor(other);
			}
		// RIGHT
		if(c.getGridPosition().getRow() < numRows)
			for(Cell other : cells){
				if(other.getGridPosition().equals(new GridPosition(c.getGridPosition().getRow() + 1, c.getGridPosition().getCol())))
					c.addNeighbor(other);
			}
		// UP
		if(c.getGridPosition().getCol() > 0)
			for(Cell other : cells){
				if(other.getGridPosition().equals(new GridPosition(c.getGridPosition().getRow(), c.getGridPosition().getCol() - 1)))
					c.addNeighbor(other);
			}
		// DOWN
		if(c.getGridPosition().getCol() < numCols)
			for(Cell other : cells){
				if(other.getGridPosition().equals(new GridPosition(c.getGridPosition().getRow(), c.getGridPosition().getCol() + 1)))
					c.addNeighbor(other);
			}
	}

	protected void addSidesAcrossBoardAsNeighbors(Cell c){
		// LEFT
		if(c.getGridPosition().getRow() == 0)
			for(Cell other : cells){
				if(other.getGridPosition().equals(new GridPosition(numRows - 1, c.getGridPosition().getCol())))
					c.addNeighbor(other);
			}
		// RIGHT
		if(c.getGridPosition().getRow() == numRows - 1)
			for(Cell other : cells){
				if(other.getGridPosition().equals(new GridPosition(0, c.getGridPosition().getCol())))
					c.addNeighbor(other);
			}
		// UP
		if(c.getGridPosition().getCol() == 0)
			for(Cell other : cells){
				if(other.getGridPosition().equals(new GridPosition(c.getGridPosition().getRow(), numCols - 1)))
					c.addNeighbor(other);
			}
		// DOWN
		if(c.getGridPosition().getCol() == numCols - 1)
			for(Cell other : cells){
				if(other.getGridPosition().equals(new GridPosition(c.getGridPosition().getRow(), 0)))
					c.addNeighbor(other);
			}
	}

	protected void addCornersAsNeighbors(Cell c){
		// UPPER LEFT
		if(c.getGridPosition().getRow() > 0 && c.getGridPosition().getCol() > 0)
			for(Cell other : cells){
				if(other.getGridPosition().equals(new GridPosition(c.getGridPosition().getRow() - 1, c.getGridPosition().getCol() - 1)))
					c.addNeighbor(other);
			}
		// UPPER RIGHT
		if(c.getGridPosition().getRow() > 0 && c.getGridPosition().getCol() < numCols - 1)
			for(Cell other : cells){
				if(other.getGridPosition().equals(new GridPosition(c.getGridPosition().getRow() - 1, c.getGridPosition().getCol() + 1)))
					c.addNeighbor(other);
			}
		// LOWER LEFT
		if(c.getGridPosition().getRow() < numRows - 1 && c.getGridPosition().getCol() > numCols - 1)
			for(Cell other : cells){
				if(other.getGridPosition().equals(new GridPosition(c.getGridPosition().getRow() + 1, c.getGridPosition().getCol() + 1)))
					c.addNeighbor(other);
			}
		// LOWER RIGHT
		if(c.getGridPosition().getRow() < numRows - 1 && c.getGridPosition().getCol() > 0)
			for(Cell other : cells){
				if(other.getGridPosition().equals(new GridPosition(c.getGridPosition().getRow() + 1, c.getGridPosition().getCol() - 1)))
					c.addNeighbor(other);
			}

	}

	protected void addCornersAcrossBoardAsNeighbors(Cell c){
		// TODO
	}

}
