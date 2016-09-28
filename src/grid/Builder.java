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
import ui.SimulationPane;

public abstract class Builder {
	protected double width;
	protected double height;
	protected double squareUnit;
	protected double triangleUnit;
	protected double hexagonUnit;
	protected int numRows;
	protected int numCols;
	protected Parameters param;

	private int top = 0;
	private int bottom;// = numRows - 1;
	private int left = 0;
	private int right;// = numCols - 1;
	protected GraphicType graphicType;

	protected List<Cell> cells;
	protected Map<Cell, CellGraphic> cellGrid;

	private Cell[][] neighborGrid;
	private State[][] copy;
	
	private NeighborAdder neighborAdder;
	private GraphicBuilder graphicBuilder;

	private static final double sqrt3 = Math.pow(3, 0.5);

	public Builder(Parameters param) {
		initHolders();
		this.param = param;
		this.graphicType = param.getGraphicType();
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
		neighborAdder = new NeighborAdder(neighborGrid, top, right, left, bottom);
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
	
	protected NeighborAdder getNeighborAdder() {
		return neighborAdder;
	}

	protected abstract Runner initRunner();

	protected abstract void readParameters();

	protected abstract void prepareForInitCells();

	protected void initCells() {
		graphicBuilder = new GraphicBuilder(squareUnit, triangleUnit, hexagonUnit);
		// initialize grid of cells
		for(int r = 0; r < numRows; r++) {
			for(int c = 0; c < numCols; c++) {
				GridPosition gp = new GridPosition(r, c);
				Cell cell = initCell(gp);
				CellGraphic g = initCellGraphic(cell, gp);
				cells.add(cell);
				cellGrid.put(cell, g);
			}
		}
	};

	protected abstract Cell initCell(GridPosition gp);

	private CellGraphic initCellGraphic(Cell cell, GridPosition gp) {
		switch (graphicType) {
		case Rectangle:
			return graphicBuilder.initRectGraphic(cell, gp);
		case Triangle:
			return graphicBuilder.initTriangleGraphic(cell, gp);
		case Hexagon:
			return graphicBuilder.initHexagonGraphic(cell, gp);
		default:
			return null;
		}
	};

	protected abstract void addAllNeighbors(Cell c);

	private void initHolders() {
		cells = new ArrayList<Cell>();
		cellGrid = new HashMap<Cell, CellGraphic>();
	}

	private void readGridSize() {
		numRows = param.getRows();
		numCols = param.getCols();
		switch (graphicType) {
		case Rectangle:
			squareUnit = (double)width / numCols;
			break;
		case Triangle:
			triangleUnit = (double)height / numRows / sqrt3;
			numCols = (int) (numRows * sqrt3);
			break;
		case Hexagon:
			hexagonUnit = (double)width / (numCols + 0.5) / sqrt3;
			numRows = (int) ((height + hexagonUnit) / (1.5 * hexagonUnit) - 0.5);
		default:
			break;
		}
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