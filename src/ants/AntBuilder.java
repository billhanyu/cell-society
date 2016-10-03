package ants;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import ui.ErrorPop;
import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;

public class AntBuilder extends Builder {

	public AntBuilder(Parameters param, ResourceBundle myResource) {
		super(param, myResource);
	}

	private AntParameters params;
	private Set<GridPosition> predefinedNests;
	private Set<GridPosition> predefinedFoods;
	private Set<GridPosition> predefinedObstacles;
	private ResourceBundle myResource;

	@Override
	protected Runner initRunner() {
		return new Runner(this.getCells(), this.getCellGrid());
	}

	@Override
	protected void readParameters() {
		if (!(this.getParam() instanceof AntParameters)) {
			// add AntError
			ErrorPop error = new ErrorPop(300, 200, myResource.getString("AntError"), myResource);
			error.popup();
		}
		params = (AntParameters) this.getParam();
	}

	@Override
	protected void prepareForInitCells() {
		predefinedNests = new HashSet<GridPosition>(params.getListOfNest());
		predefinedFoods = new HashSet<GridPosition>(params.getListOfFood());
		predefinedObstacles = new HashSet<GridPosition>(params.getListOfObstacle());
	}

	@Override
	protected Cell initCell(GridPosition gp) {
		AntCell acell;
		if(predefinedNests.contains(gp))
			acell = new AntCell(gp, AntCell.nest, params);
		else if(predefinedFoods.contains(gp))
			acell = new AntCell(gp, AntCell.food, params);
		else if(predefinedObstacles.contains(gp))
			acell = new AntCell(gp, AntCell.obstacle, params);
		else
			acell = new AntCell(gp, AntCell.empty, params);
		return acell;
	}

	@Override
	protected void addRectNeighbors(Cell c) {
		this.getNeighborAdder().addSidesAsNeighbors(c);
		this.getNeighborAdder().addCornersAsNeighbors(c);
	}



}
