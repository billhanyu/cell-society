package SpreadingFire;

import java.util.ResourceBundle;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import ui.ErrorPop;

public class SpreadingFireBuilder extends Builder {

	public SpreadingFireBuilder(Parameters param, ResourceBundle myResource) {
		super(param, myResource);
	}

	SFParameters pars;
	ResourceBundle myResource;
	
	@Override
	public Runner initRunner() {
		return new SpreadingFireRunner(this.getCells(), this.getCellGrid());
	}

	@Override
	protected Cell initCell(GridPosition gp) {
		int r = gp.getRow();
		int c = gp.getCol();
		SpreadingFireCell sfCell;
		if (pars.isModified()){
			if (pars.getExtendedParams().getRowStart() == r && pars.getExtendedParams().getColStart() == c){
				sfCell = new SpreadingFireCell(gp, SpreadingFireCell.burning);
			}
			else{
				sfCell = new SpreadingFireCell(gp, SpreadingFireCell.tree);
			}
		}
		else{
			if (r == (this.getNumRows() / 2) && c == (this.getNumCols() / 2) ) {
				sfCell = new SpreadingFireCell(gp, SpreadingFireCell.burning);
			}
			else{
				sfCell = new SpreadingFireCell(gp, SpreadingFireCell.tree);
			}
		}
		sfCell.setProbCatch(pars.getProbCatch());
		return sfCell;
	}

	@Override
	protected void addRectNeighbors(Cell c) {
		this.getNeighborAdder().addSidesAsNeighbors(c);
	}

	@Override
	protected void readParameters() {
		if (!(this.getParam() instanceof SFParameters)) {
			ErrorPop error = new ErrorPop(300, 200, myResource.getString("FireError"), myResource);
			error.popup();
		}
		pars = (SFParameters) this.getParam();
	}

	@Override
	protected void prepareForInitCells() {
	}
}
