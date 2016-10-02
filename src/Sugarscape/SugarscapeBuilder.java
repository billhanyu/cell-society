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
		int sugar = (int)(Math.random() * 21 + 5);
		int visibility = (int)(Math.random() * 6 + 1);
		int metabolism = (int)(Math.random() * 4 + 1);
		AgentState agent;
		if (Math.random() < 0.8) {
			agent = SugarscapeCell.noAgent;
		}
		else {
			agent = new AgentState(sugar, visibility, metabolism);
		}
		if (rnd < prob) {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.empty, agent);
		}
		else if (rnd < 2 * prob) {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.oneFourthsFull, agent);
		}
		else if (rnd < 3 * prob) {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.twoFourthsFull, agent);
		}
		else if (rnd < 4 * prob) {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.threeFourthsFull, agent);
		}
		else {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.fourFourthsFull, agent);
		}
		return ssCell;
	}

	@Override
	protected void addRectNeighbors(Cell c) {
		this.getNeighborAdder().addSidesAsNeighbors(c);
	}

}
