package schelling;

import java.util.List;
import java.util.Map;

import cell.Cell;
import grid.CellGraphic;
import grid.Runner;

public class SchellingRunner extends Runner {

	public SchellingRunner(List<Cell> cells, Map<Cell, CellGraphic> cellGrid) {
		super(cells, cellGrid);
	}

}
