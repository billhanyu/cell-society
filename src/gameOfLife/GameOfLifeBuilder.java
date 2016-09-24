package gameOfLife;

import cell.Cell;
import grid.Builder;
import grid.Parameters;
import grid.Runner;

public class GameOfLifeBuilder extends Builder{

	public GameOfLifeBuilder(Parameters param) {
		super(param);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Runner initRunner() {
		return new GameOfLifeRunner(cells, cellGrid);
	}

	@Override
	protected void readParameters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initCells() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addAllNeighbors(Cell c) {
		// TODO Auto-generated method stub
		
	}

}
