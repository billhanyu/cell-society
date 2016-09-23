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
	
	public SimulationPane getSimulationPane() {
		SimulationPane pane = new SimulationPane(width, height);
		for (CellGraphic graphic: cellGrid.values()) {
			pane.addShape(graphic.getGraphic());
		}
		return pane;
	};
}
