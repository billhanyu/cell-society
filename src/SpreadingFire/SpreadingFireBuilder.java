package SpreadingFire;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;

public class SpreadingFireBuilder extends Builder{
	
	private int rows;
	private int cols;
	private double probCatch;
	
	public SpreadingFireBuilder(int r, int c, double prob){
		rows = r;
		cols = c;
		probCatch = prob;
	}
	
	public void init(){
		SpreadingFireRunner runner = new SpreadingFireRunner();
		runner.init(rows, cols);
		setStates(runner);
	}
	
	public void setStates(SpreadingFireRunner runner){
		for(Cell cell : runner.getGrid()){
			SpreadingFireCell sfcell = (SpreadingFireCell) cell;
			sfcell.setFutureState(SpreadingFireCell.tree);
			if(sfcell.getGridPosition().equals(new GridPosition(rows/2, cols/2)))
				sfcell.setFutureState(SpreadingFireCell.burning);
			sfcell.setProbCatch(probCatch);
		}
	}

}
