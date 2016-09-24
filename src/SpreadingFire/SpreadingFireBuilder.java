package SpreadingFire;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.CellGraphic;
import grid.Parameters;
import grid.Runner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.ErrorPop;

public class SpreadingFireBuilder extends Builder {

	SFParameters pars;

	public SpreadingFireBuilder(Parameters param) {
		super(param);
	}

	@Override
	public Runner initRunner() {
		return new SpreadingFireRunner(cells, cellGrid);
	}

	@Override
	protected void initCells() {
		// initialize grid of cells
		for(int r = 0; r < numRows; r++) {
			for(int c = 0; c < numCols; c++) {
				SpreadingFireCell sfCell;
				GridPosition gp = new GridPosition(r, c);
				if (pars.isModified()){
				    if (pars.getExtendedParams().getRowStart() == r && pars.getExtendedParams().getColStart() == c){
				        sfCell = new SpreadingFireCell(gp, SpreadingFireCell.burning);
				        System.out.println(r);
				        System.out.println(c);
				    }
				    else{
				        sfCell = new SpreadingFireCell(gp, SpreadingFireCell.tree);
				    }
				}
				else{
				    if (r == (numRows / 2) && c == (numCols / 2) ) {
				        sfCell = new SpreadingFireCell(gp, SpreadingFireCell.burning);
				    }
				    else{
				        sfCell = new SpreadingFireCell(gp, SpreadingFireCell.tree);
				    }
				}
				
				sfCell.setProbCatch(pars.getProbCatch());
				cells.add(sfCell);
				Rectangle rect = new Rectangle(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
				CellGraphic g = new CellGraphic(new GridPosition(r, c));
				rect.setFill(sfCell.getCurrState().getColor());
				rect.setStroke(Color.BLACK);
				g.setGraphic(rect);
				cellGrid.put(sfCell, g);
			}
		}
	}

	@Override
	protected void addAllNeighbors(Cell c) {
		addSidesAsNeighbors(c);
	}

	@Override
	protected void readParameters() {
		if (!(param instanceof SFParameters)) {
			ErrorPop error = new ErrorPop(300, 200, "Error Reading SFParameters");
			error.popup();
		}
		pars = (SFParameters) param;
		cellWidth = (double)width / numCols;
		cellHeight = cellWidth;
	}
}
