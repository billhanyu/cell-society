package WaTor;

import javafx.scene.paint.Color;
import cell.Cell;
import cell.GridPosition;
import cell.State;

public class WaTorCell extends Cell{
	
	public WaTorCell(GridPosition gp, State s) {
		super(gp, s);
	}

	public static final int SHARK_ENERGY = 10;
	public static final int FISH_REPRODUCTION_RATE = 3;
	public static final int SHARK_REPRODUCTION_RATE = 15;
	public static State empty = new State(Color.GRAY, "EMPTY");



	@Override
	public void checkChangeState() {
		if (getCurrState().equals(new WaTorFishState()));
		
	}
	
	public void fishSwim(){
		for(Cell neighbor : getNeighbors())
			if (neighbor.getCurrState().equals(empty))
				
				;

	}

}
