package SpreadingFire;

import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import ui.SimulationPane;


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
		initCells();
		initCellGrid();
		return new SpreadingFireRunner(cells, cellGrid);
	}

	@Override
	protected void initCells() {
		// initialize grid of cells
		for(int r = 0; r < numRows; r++) {
			for(int c = 0; c < numCols; c++){
				SpreadingFireCell sfCell = new SpreadingFireCell(new GridPosition(r,c));
				sfCell.setProbCatch(pars.getProbCatch());
				if (r == (numRows / 2) && c == (numCols / 2) )
					sfCell.setFutureState(SpreadingFireCell.burning);
				else
					sfCell.setFutureState(SpreadingFireCell.tree);
				cells.add(sfCell);
			}
		}
	}

	@Override
	protected void initCellGrid() {
		// TODO create SpreadingFireBuilder to create visual representation
		
	}
}
