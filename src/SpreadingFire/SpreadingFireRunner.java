package SpreadingFire;

import cell.GridPosition;
import grid.Runner;

public class SpreadingFireRunner extends Runner{

	@Override
	public void init(int rows, int cols) {
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < cols; c++){
				// adds new cell initialized to have a position ID in grid
				grid.add(new SpreadingFireCell(new GridPosition(r,c)));
			}
	}

	public void initStates(){
	}
}
