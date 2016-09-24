package gameOfLife;

import java.util.List;
import java.util.Map;

import cell.Cell;
import grid.CellGraphic;
import grid.Runner;

public class GameOfLifeRunner extends Runner {

	public GameOfLifeRunner(List<Cell> cells, Map<Cell, CellGraphic> cellGrid) {
		super(cells, cellGrid);
	}
}


