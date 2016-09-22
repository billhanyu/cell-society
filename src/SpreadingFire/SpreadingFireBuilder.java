package SpreadingFire;

import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;


public class SpreadingFireBuilder extends Builder {
	
	public SpreadingFireBuilder(Parameters param) {
		super(param);
	}

	@Override
	public Runner init() {
		if (!(param instanceof SFParameters)) {
			// not supposed to happen
			return null;
		}
		
		SFParameters pars = (SFParameters) param;
		int rows = pars.getRows();
		int cols = pars.getCols();
		// initialize grid of cells
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < cols; c++){
				SpreadingFireCell sfCell = new SpreadingFireCell(new GridPosition(r,c));
				sfCell.setProbCatch(pars.getProbCatch());
				if (r == (rows / 2) && c == (cols / 2) )
					sfCell.setFutureState(SpreadingFireCell.burning);
				else
					sfCell.setFutureState(SpreadingFireCell.tree);
				cells.add(sfCell);
			}
		
		//TODO: create SpreadingFireBuilder to create visual representation
		
		return new SpreadingFireRunner(cells, cellGrid);
	}
}
