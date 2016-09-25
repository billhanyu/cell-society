package SpreadingFire;

import java.util.List;
import java.util.Map;

import cell.Cell;
import grid.CellGraphic;
import grid.Runner;

public class SpreadingFireRunner extends Runner {

	public SpreadingFireRunner(List<Cell> cells, Map<Cell, CellGraphic> cellGrid) {
		super(cells, cellGrid);
	}
}
