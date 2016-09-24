package SpreadingFire;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.CellGraphic;
import grid.Parameters;
import grid.Runner;
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
				SpreadingFireCell sfCell = new SpreadingFireCell(new GridPosition(r,c));
				sfCell.setProbCatch(pars.getProbCatch());
				if (r == (numRows / 2) && c == (numCols / 2) )
					sfCell.setFutureState(SpreadingFireCell.burning);
				else
					sfCell.setFutureState(SpreadingFireCell.tree);
				cells.add(sfCell);
				Rectangle rect = new Rectangle(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
				CellGraphic g = new CellGraphic(new GridPosition(r, c));
				g.setGraphic(rect);
				rect.setFill(sfCell.getFutureState().getColor()); // for debugging
				rect.setStroke(sfCell.getFutureState().getColor());
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
