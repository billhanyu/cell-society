package Sugarscape;

import java.util.ResourceBundle;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;

public class SugarscapeBuilder extends Builder {

	public SugarscapeBuilder(Parameters param, ResourceBundle myResource) {
		super(param, myResource);
	}

	@Override
	protected Runner initRunner() {
		return new Runner(this.getCells(), this.getCellGrid());
	}

	@Override
	protected void readParameters() {
	}

	@Override
	protected void prepareForInitCells() {
	}

	@Override
	protected Cell initCell(GridPosition gp) {
		SugarscapeCell ssCell;
		double rnd = Math.random();
		double prob = 1.0 / 5;
		if (rnd < prob) {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.empty, SugarscapeCell.empty, SugarscapeCell.noAgent);
		}
		else if (rnd < 2 * prob) {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.empty, SugarscapeCell.oneFourthsFull, SugarscapeCell.noAgent);
		}
		else if (rnd < 3 * prob) {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.empty, SugarscapeCell.twoFourthsFull, SugarscapeCell.noAgent);
		}
		else if (rnd < 4 * prob) {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.empty, SugarscapeCell.threeFourthsFull, SugarscapeCell.noAgent);
		}
		else {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.empty, SugarscapeCell.fourFourthsFull, SugarscapeCell.noAgent);
		}
		return ssCell;
	}

	@Override
	protected void addRectNeighbors(Cell c) {
		this.getNeighborAdder().addSidesAsNeighbors(c);
	}

}
