package gameOfLife;

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

	public GameOfLifeBuilder(Parameters param) {
		super(param);
	}

	@Override
	protected Runner initRunner() {
		return new GameOfLifeRunner(cells, cellGrid);
	}

	@Override
	protected void readParameters() {
		if (!(param instanceof GLParameters)) {
			ErrorPop error = new ErrorPop(300, 200, "Error Reading GLParameters");
			error.popup();
		}
		pars = (GLParameters) param;
		cellWidth = (double)width / numCols;
		cellHeight = cellWidth;
	}

	@Override
	protected void initCells() {
		// initialize grid of cells
		for(int r = 0; r < numRows; r++) {
			for(int c = 0; c < numCols; c++) {
				GameOfLifeCell glCell;
				GridPosition gp = new GridPosition(r, c);
				double rnd = Math.random();
				if (rnd < pars.getRatioOfAlive()) {
					glCell = new GameOfLifeCell(gp, GameOfLifeCell.alive);
				}
				else {
					glCell = new GameOfLifeCell(gp, GameOfLifeCell.dead);
				}
				cells.add(glCell);
				Rectangle rect = new Rectangle(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
				CellGraphic g = new CellGraphic(new GridPosition(r, c));
				rect.setFill(glCell.getCurrState().getColor());
				rect.setStroke(Color.BLACK);
				g.setGraphic(rect);
				cellGrid.put(glCell, g);
			}
		}
	}

	@Override
	protected void addAllNeighbors(Cell c) {
		this.addSidesAsNeighbors(c);
	}
}
