package Sugarscape;

import java.util.ResourceBundle;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;

public class SugarscapeBuilder extends Builder {
	
	private SugarParameters pars;

	public SugarscapeBuilder(Parameters param, ResourceBundle myResource) {
		super(param, myResource);
	}

	@Override
	protected Runner initRunner() {
		return new Runner(this.getCells(), this.getCellGrid());
	}

	@Override
	protected void readParameters() {
		pars = (SugarParameters)this.getParam();
	}

	@Override
	protected void prepareForInitCells() {
	}

	@Override
	protected Cell initCell(GridPosition gp) {
		SugarscapeCell ssCell;
		int sugar = (int)(Math.random() * 21 + 5);
		int visibility = (int)(Math.random() * 6 + 1);
		int metabolism = (int)(Math.random() * 4 + 1);
		AgentState agent;
		if (Math.random() < 1 - pars.getAgentRatio()) {
			agent = SugarscapeCell.noAgent;
		}
		else {
			agent = new AgentState(sugar, visibility, metabolism);
		}
		if (pars.getOne().contains(gp)) {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.oneFourthsFull, agent);
		}
		else if (pars.getTwo().contains(gp)) {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.twoFourthsFull, agent);
		}
		else if (pars.getThree().contains(gp)) {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.threeFourthsFull, agent);
		}
		else if (pars.getFull().contains(gp)) {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.fourFourthsFull, agent);
		}
		else {
			ssCell = new SugarscapeCell(gp, SugarscapeCell.empty, agent);
		}
		return ssCell;
	}

	@Override
	protected void addRectNeighbors(Cell c) {
		this.getNeighborAdder().addSidesAsNeighbors(c);
	}

}
