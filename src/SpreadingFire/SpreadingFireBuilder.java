package SpreadingFire;

import java.util.ResourceBundle;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import ui.ErrorPop;

public class SpreadingFireBuilder extends Builder {

	SFParameters pars;
	ResourceBundle myResource;

	public SpreadingFireBuilder(Parameters param, ResourceBundle myResource) {
		super(param);
		this.myResource = myResource;
	}

	@Override
	public Runner initRunner() {
		return new SpreadingFireRunner(cells, cellGrid);
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
			if (r == (numRows / 2) && c == (numCols / 2) ) {
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
		if (!(param instanceof SFParameters)) {
			ErrorPop error = new ErrorPop(300, 200, myResource.getString("FireError"), myResource);
			error.popup();
		}
		pars = (SFParameters) param;
	}

	@Override
	protected void prepareForInitCells() {
	}
}
