package WaTor;

import java.util.ResourceBundle;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import ui.ErrorPop;

public class WaTorBuilder extends Builder {

	public WaTorBuilder(Parameters param, ResourceBundle myResource) {
		super(param, myResource);
	}

	private WTParameters pars;
	private double emptyRatio;
	private double ratio;
	private ResourceBundle myResource;

	@Override
	protected Runner initRunner() {
		return new WaTorRunner(this.getCells(), this.getCellGrid());
	}

	@Override
	protected void readParameters() {
		if (!(this.getParam() instanceof WTParameters)) {
			ErrorPop error = new ErrorPop(300, 200, myResource.getString("WaTorError"), myResource);
			error.popup();
		}
		pars = (WTParameters) this.getParam();
		emptyRatio = pars.getEmptyRatio();
		ratio = pars.getRatio();
	}

	@Override
	protected Cell initCell(GridPosition gp) {
		WaTorCell wtCell;
		double rnd = Math.random();
		if (rnd < emptyRatio) {
			wtCell = new WaTorCell(gp, WaTorCell.empty, pars);
		}
		else if (rnd > (emptyRatio + ratio)/(ratio + 1)) {
			wtCell = new WaTorCell(gp, new WaTorFishState(), pars);
		}
		else {
			wtCell = new WaTorCell(gp, new WaTorSharkState(pars), pars);
		}
		return wtCell;

	}

	@Override
	protected void addRectNeighbors(Cell c) {
		this.getNeighborAdder().addSidesAsNeighbors(c);
		this.getNeighborAdder().addCornersAsNeighbors(c);
		this.getNeighborAdder().addSidesAcrossBoardAsNeighbors(c);
		this.getNeighborAdder().addCornersAcrossBoardAsNeighbors(c);
	}

	@Override
	protected void prepareForInitCells() {
	}

}
