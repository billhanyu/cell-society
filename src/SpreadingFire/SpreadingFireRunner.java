package SpreadingFire;

import cell.GridPosition;
import grid.Runner;

public class SpreadingFireRunner extends Runner{
	
	private double probCatch;

	@Override
	public void init(int rows, int cols) {
		
		// initialize grid of cells
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < cols; c++){
				SpreadingFireCell sfCell = new SpreadingFireCell(new GridPosition(r,c));
				sfCell.setProbCatch(probCatch);
				if (r == (rows / 2) && c == (cols / 2) )
					sfCell.setFutureState(SpreadingFireCell.burning);
				else
					sfCell.setFutureState(SpreadingFireCell.tree);

				grid.add(sfCell);
			}
		
		//TODO: create SpreadingFireBuilder to create visual representation
		
	}

}
