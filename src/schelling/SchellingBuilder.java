package schelling;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import ui.ErrorPop;

public class SchellingBuilder extends Builder {

	public SchellingBuilder(Parameters param, ResourceBundle myResource) {
		super(param, myResource);
	}

	private SLParameters pars;
	private double emptyRatio;
	private double ratio;
	
	private Set<GridPosition> listOfRed;
	private Set<GridPosition> listOfBlue;

	@Override
	protected Runner initRunner() {
		return new Runner(this.getCells(), this.getCellGrid());
	}

	@Override
	protected Cell initCell(GridPosition gp) {
		SchellingCell slCell;
		double rnd = Math.random();
		if (pars.isSetByLocations()){
		    if (listOfRed.contains(gp)){
		        slCell = new SchellingCell(gp, SchellingCell.personX);
		    }
		    else if (listOfBlue.contains(gp)){
		        slCell = new SchellingCell(gp, SchellingCell.personO);
		    }
		    else {
		        slCell = new SchellingCell(gp, SchellingCell.vacant);
		    }
		}
		else{
        		if (rnd < emptyRatio) {
        			slCell = new SchellingCell(gp, SchellingCell.vacant);
        		}
        		else if (rnd > (emptyRatio + ratio)/(ratio + 1)) {
        			slCell = new SchellingCell(gp, SchellingCell.personO);
        		}
        		else {
        			slCell = new SchellingCell(gp, SchellingCell.personX);
        		}
		}
		slCell.setCellsPointer(this.getCells());
		return slCell;
	}

	@Override
	protected void addRectNeighbors(Cell c) {
		this.getNeighborAdder().addSidesAsNeighbors(c);
		this.getNeighborAdder().addCornersAsNeighbors(c);
	}

	@Override
	protected void readParameters() {
		if (!(this.getParam() instanceof SLParameters)) {
			// not supposed to happen
			ErrorPop error = new ErrorPop(300, 200, this.getResource().getString("SchellingError"), this.getResource());
			error.popup();
		}
		pars = (SLParameters) this.getParam();
		emptyRatio = pars.getEmptyRatio();
		ratio = pars.getRatio();
	}

	@Override
	protected void prepareForInitCells() {
	    if (pars.isSetByLocations()){
	        listOfRed = new HashSet<GridPosition>(pars.getListOfRed());
	        listOfBlue = new HashSet<GridPosition>(pars.getListOfBlue());
	    }
	}
}
