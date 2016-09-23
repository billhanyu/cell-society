package SpreadingFire;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.CellGraphic;
import grid.Parameters;
import grid.Runner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SpreadingFireBuilder extends Builder {

	SFParameters pars;

	public SpreadingFireBuilder(Parameters param) {
		super(param);
	}

	@Override
	public Runner init() {
		if (!(param instanceof SFParameters)) {
			// not supposed to happen
			return null;
		}
		pars = (SFParameters) param;
		cellWidth = (double)width / numCols;
		cellHeight = cellWidth;
		initCells();
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
				rect.setFill(Color.BEIGE); // for debugging
				rect.setStroke(Color.BLACK);
				cellGrid.put(sfCell, g);
			}
		}
	}

	@Override
	protected void addNeighbors(Cell c) {
		addSidesAsNeighbors(c);
	}
}
