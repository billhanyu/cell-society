package schelling;

import java.util.ResourceBundle;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import ui.ErrorPop;

public class SchellingBuilder extends Builder {

	private SLParameters pars;
	private double emptyRatio;
	private double ratio;
	private ResourceBundle myResource;

	public SchellingBuilder(Parameters param, ResourceBundle myResource) {
		super(param);
		this.myResource = myResource;
	}

	@Override
	protected Runner initRunner() {
		return new SchellingRunner(cells, cellGrid);
	}

	@Override
	protected Cell initCell(GridPosition gp) {
		SchellingCell slCell;
		double rnd = Math.random();
		if (rnd < emptyRatio) {
			slCell = new SchellingCell(gp, SchellingCell.vacant);
		}
		else if (rnd > (emptyRatio + ratio)/(ratio + 1)) {
			slCell = new SchellingCell(gp, SchellingCell.personO);
		}
		else {
			slCell = new SchellingCell(gp, SchellingCell.personX);
		}
		slCell.setCellsPointer(cells);
		return slCell;
	}

	@Override
	protected void addAllNeighbors(Cell c) {
		this.getNeighborAdder().addSidesAsNeighbors(c);
		this.getNeighborAdder().addCornersAsNeighbors(c);
	}

	@Override
	protected void readParameters() {
		if (!(param instanceof SLParameters)) {
			// not supposed to happen
			ErrorPop error = new ErrorPop(300, 200, myResource.getString("SchellingError"), myResource);
			error.popup();
		}
		pars = (SLParameters) param;
		emptyRatio = pars.getEmptyRatio();
		ratio = pars.getRatio();
	}

	@Override
	protected void prepareForInitCells() {
	}
}
