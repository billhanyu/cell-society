package WaTor;

import java.util.List;
import java.util.Map;

import cell.Cell;
import grid.CellGraphic;
import grid.Runner;

public class WaTorRunner extends Runner {

	public WaTorRunner(List<Cell> cells, Map<Cell, CellGraphic> cellGrid) {
		super(cells, cellGrid);
	}

	@Override
	protected void updateAllCellStates(){
		for(Cell c : cells)
			if (c.getCurrState() instanceof WaTorSharkState)
				c.checkChangeState();
		for(Cell c : cells)
			c.updateState();
		for(Cell c : cells)
			if (c.getCurrState() instanceof WaTorFishState)
				c.checkChangeState();
		for(Cell c : cells)
			c.updateState();
	}

}
