package grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import WaTor.WaTorState;
import cell.Cell;
import cell.GridPosition;
import cell.State;
import init.Initializer;
import ui.SimulationPane;

public abstract class Builder {
	private double width;
	private double height;
	private double squareUnit;
	private double triangleUnit;
	private double hexagonUnit;
	private int numRows;
	private int numCols;
	
	private Parameters param;
	private ResourceBundle myResource;

	private int top = 0;
	private int bottom;// = numRows - 1;
	private int left = 0;
	private int right;// = numCols - 1;
	private String graphicType;

	private List<Cell> cells;
	private Map<Cell, CellGraphic> cellGrid;

	private Cell[][] neighborGrid;
	private State[][] copy;

	private NeighborAdder neighborAdder;
	private GraphicBuilder graphicBuilder;

	private static final double sqrt3 = Math.pow(3, 0.5);

	public Builder(Parameters param) {
		initHolders();
		this.setParam(param);
		this.graphicType = param.getGraphicType();
		readGridSize();
		width = Initializer.SCENE_HEIGHT - 20;
		height = width;
		//TODO change this height = width relationship later
	}
	
	public Builder(Parameters param, ResourceBundle myResource) {
		this(param);
		this.myResource = myResource;
	}

	public void setParameters(Parameters param) {
		this.setParam(param);
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
		for (CellGraphic graphic: getCellGrid().values()) {
			pane.addShape(graphic.getGraphic());
		}
		return pane;
	};

	public void giveAllCellsNeighbors(){
		for (Cell c: getCells()) {
			neighborGrid[c.getGridPosition().getRow()][c.getGridPosition().getCol()]
					= c;
		}
		neighborAdder = new NeighborAdder(neighborGrid, top, right, left, bottom);
		for (Cell c: getCells()) {
			switch (graphicType) {
			case "Square":
				addRectNeighbors(c);
				break;
			case "Triangle":
				this.getNeighborAdder().addTriangleNeighbors(c);
				break;
			case "Hexagon":
				this.getNeighborAdder().addHexagonNeighbors(c);
				break;
			}
		}
		neighborGrid = null;
	}

	public void reset() {
		for (Cell c: getCells()) {
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
				getCells().add(cell);
				getCellGrid().put(cell, g);
			}
		}
	};

	protected abstract Cell initCell(GridPosition gp);

	private CellGraphic initCellGraphic(Cell cell, GridPosition gp) {
		switch (graphicType) {
		case "Square":
			return graphicBuilder.initRectGraphic(cell, gp);
		case "Triangle":
			return graphicBuilder.initTriangleGraphic(cell, gp);
		case "Hexagon":
			return graphicBuilder.initHexagonGraphic(cell, gp);
		default:
			return null;
		}
	};

	protected abstract void addRectNeighbors(Cell c);

	private void initHolders() {
		setCells(new ArrayList<Cell>());
		setCellGrid(new HashMap<Cell, CellGraphic>());
	}

	private void readGridSize() {
		numRows = getParam().getRows();
		numCols = getParam().getCols();
		switch (graphicType) {
		case "Square":
			squareUnit = (double)width / numCols;
			break;
		case "Triangle":
			triangleUnit = (double)height / numRows / sqrt3;
			numCols = (int) (numRows * sqrt3);
			break;
		case "Hexagon":
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
		for (Cell c: getCells()) {
			State currState = c.getCurrState();
			if (currState instanceof WaTorState) {
				copy[c.getGridPosition().getRow()][c.getGridPosition().getCol()] = ((WaTorState) currState).copy();
			}
			else {
				copy[c.getGridPosition().getRow()][c.getGridPosition().getCol()] = currState;
			}
		}
	}

	protected int getNumRows() {
		return this.numRows;
	}

	protected int getNumCols() {
		return this.numCols;
	}

	protected List<Cell> getCells() {
		return cells;
	}

	protected void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	protected Map<Cell, CellGraphic> getCellGrid() {
		return cellGrid;
	}

	protected void setCellGrid(Map<Cell, CellGraphic> cellGrid) {
		this.cellGrid = cellGrid;
	}

	protected Parameters getParam() {
		return param;
	}

	protected void setParam(Parameters param) {
		this.param = param;
	}
	
	protected ResourceBundle getResource() {
		return this.myResource;
	}
}