package gameOfLife;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	private Set<GridPosition> gpUsed;

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
	    gpUsed = new HashSet<GridPosition>();
	    if (pars.isModifiedStart()){
	        for (int s = 0; s < pars.getListOfAlive().size(); s++){
	            GameOfLifeCell glCell;
	            GridPosition gp = pars.getListOfAlive().get(s);
	            gpUsed.add(gp);
	            glCell = new GameOfLifeCell(gp, GameOfLifeCell.alive);
	            addRectCell(gp.getRow(), gp.getCol(), glCell);
	        }
	        for(int r = 0; r < numRows; r++) {
                    for(int c = 0; c < numCols; c++) {
                            GameOfLifeCell glCell;
                            GridPosition gp = new GridPosition(r, c);
                            if (!gpUsed.contains(gp)){
                                glCell = new GameOfLifeCell(gp, GameOfLifeCell.dead);
                                addRectCell(r, c, glCell);
                            }
                    }
              }
	        
	        
	    }
	    else{
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
				addRectCell(r, c, glCell);
			}
	          }
	    }
	}
	
	private void addRectCell(int r, int c, GameOfLifeCell glCell){
	    cells.add(glCell);
            Rectangle rect = new Rectangle(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
            CellGraphic g = new CellGraphic(new GridPosition(r, c));
            rect.setFill(glCell.getCurrState().getColor());
            rect.setStroke(Color.BLACK);
            g.setGraphic(rect);
            cellGrid.put(glCell, g);
	}

	@Override
	protected void addAllNeighbors(Cell c) {
		this.addSidesAsNeighbors(c);
		this.addCornersAsNeighbors(c);
		this.addCornersAcrossBoardAsNeighbors(c);
		this.addSidesAcrossBoardAsNeighbors(c);
	}
}
