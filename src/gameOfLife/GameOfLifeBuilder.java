package gameOfLife;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.CellGraphic;
import grid.Parameters;
import grid.Runner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.ErrorPop;

public class GameOfLifeBuilder extends Builder{

	private GLParameters pars;
	private Set<GridPosition> predefinedAlives;
	private ResourceBundle myResource;

	public GameOfLifeBuilder(Parameters param, ResourceBundle myResource) {
		super(param);
		this.myResource = myResource;
	}

	@Override
	protected Runner initRunner() {
		return new GameOfLifeRunner(cells, cellGrid);
	}

	@Override
	protected void readParameters() {
		if (!(param instanceof GLParameters)) {
			ErrorPop error = new ErrorPop(300, 200, myResource.getString("LifeError"), myResource);
			error.popup();
		}
		pars = (GLParameters) param;
		cellWidth = (double)width / numCols;
		cellHeight = cellWidth;
	}
	
	@Override
	protected void addAllNeighbors(Cell c) {
		this.addSidesAsNeighbors(c);
		this.addCornersAsNeighbors(c);
		this.addCornersAcrossBoardAsNeighbors(c);
		this.addSidesAcrossBoardAsNeighbors(c);
	}

	@Override
	protected Cell initCell(GridPosition gp) {
		GameOfLifeCell glCell;
		if (pars.isModifiedStart()) {
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
	protected CellGraphic initCellGraphic(Cell cell, GridPosition gp) {
		int r = gp.getRow();
		int c = gp.getCol();
		Rectangle rect = new Rectangle(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
		CellGraphic g = new CellGraphic(gp);
		rect.setFill(cell.getCurrState().getColor());
		rect.setStroke(Color.BLACK);
		g.setGraphic(rect);
		return g;
	}

	@Override
	protected void prepareForInitCells() {
		if (pars.isModifiedStart()){
			predefinedAlives = new HashSet<GridPosition>(pars.getListOfAlive());
		}
	}
}
