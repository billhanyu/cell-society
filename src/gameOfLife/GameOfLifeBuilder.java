package gameOfLife;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import ui.ErrorPop;

public class GameOfLifeBuilder extends Builder{

	public GameOfLifeBuilder(Parameters param, ResourceBundle myResource) {
		super(param, myResource);
	}

	private GLParameters pars;
	private Set<GridPosition> predefinedAlives;
	private ResourceBundle myResource;

	@Override
	protected Runner initRunner() {
		return new Runner(this.getCells(), this.getCellGrid());
	}

	@Override
	protected void readParameters() {
		if (!(this.getParam() instanceof GLParameters)) {
			ErrorPop error = new ErrorPop(300, 200, myResource.getString("LifeError"), myResource);
			error.popup();
		}
		pars = (GLParameters) this.getParam();
	}
	
	/* 
	 * Sides, corners, and sides/corners wrapped
	 * around the board are added as neighbors
	 */
	@Override
	protected void addRectNeighbors(Cell c) {
		this.getNeighborAdder().addSidesAsNeighbors(c);
		this.getNeighborAdder().addCornersAsNeighbors(c);
		this.getNeighborAdder().addCornersAcrossBoardAsNeighbors(c);
		this.getNeighborAdder().addSidesAcrossBoardAsNeighbors(c);
	}

	@Override
	protected Cell initCell(GridPosition gp) {
		GameOfLifeCell glCell;
		if (pars.isSetByLocations()) {
			if (predefinedAlives.contains(gp)) {
				glCell = new GameOfLifeCell(gp, GameOfLifeCell.alive);
			}
			else {
				glCell = new GameOfLifeCell(gp, GameOfLifeCell.dead);
			}
		}
		else {
			double rnd = Math.random();
			if (rnd < pars.getRatioOfAlive()) {
				glCell = new GameOfLifeCell(gp, GameOfLifeCell.alive);
			}
			else {
				glCell = new GameOfLifeCell(gp, GameOfLifeCell.dead);
			}
		}
		return glCell;
	}

	@Override
	protected void prepareForInitCells() {
		if (pars.isSetByLocations()){
			predefinedAlives = new HashSet<GridPosition>(pars.getListOfAlive());
		}
	}
}
