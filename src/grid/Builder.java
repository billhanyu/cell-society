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
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import ui.SimulationPane;

public abstract class Builder {
	protected double width;
	protected double height;
	protected double cellWidth; //Rectangle cells
	protected double cellHeight;
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
		neighborAdder = new NeighborAdder(neighborGrid, numRows, numCols, 
				top, right, left, bottom);
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
			return initRectGraphic(cell, gp);
		case Triangle:
			return initTriangleGraphic(cell, gp);
		case Hexagon:
			return initHexagonGraphic(cell, gp);
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

	private CellGraphic initTriangleGraphic(Cell cell, GridPosition gp) {
		int r = gp.getRow();
		int c = gp.getCol();
		double x = triangleUnit * (c + 1);
		double y = triangleUnit * sqrt3 * r;
		CellGraphic g = new CellGraphic(gp);
		Polygon gon = new Polygon();
		if ((r + c) % 2 == 0) {
			gon.getPoints().addAll(new Double[]{
					x, y,
					x - triangleUnit, y + sqrt3 * triangleUnit,
					x + triangleUnit, y + sqrt3 * triangleUnit
			});
		}
		else {
			gon.getPoints().addAll(new Double[]{
					x, y + sqrt3 * triangleUnit,
					x - triangleUnit, y,
					x + triangleUnit, y
			});
		}
		gon.setFill(cell.getCurrState().getColor());
		gon.setStroke(Color.BLACK);
		g.setGraphic(gon);
		return g;
	}

	private CellGraphic initHexagonGraphic(Cell cell, GridPosition gp) {
		int r = gp.getRow();
		int c = gp.getCol();
		CellGraphic g = new CellGraphic(gp);
		double offset = r % 2 == 0 ? 0 : sqrt3/2*hexagonUnit;
		double x = sqrt3 * hexagonUnit * c;
		double y = 3 * hexagonUnit / 2 * r;
		double xUnit = sqrt3 * hexagonUnit / 2;
		double yUnit = hexagonUnit / 2;
		Polygon gon = new Polygon();
		gon.getPoints().addAll(new Double[]{
				x + xUnit + offset, y,
				x + offset, y + yUnit,
				x + offset, y + 3 * yUnit,
				x + xUnit + offset, y + 4 * yUnit,
				x + 2 * xUnit + offset, y + 3 * yUnit,
				x + 2 * xUnit + offset, y + yUnit
		});
		gon.setFill(cell.getCurrState().getColor());
		gon.setStroke(Color.BLACK);
		g.setGraphic(gon);
		return g;
	}

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
			cellWidth = (double)width / numCols;
			cellHeight = cellWidth;
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