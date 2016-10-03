package SpreadingFire;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import ui.ErrorPop;

public class SpreadingFireBuilder extends Builder {

	private SFParameters pars;
	private Set<GridPosition> predefinedFire;

	public SpreadingFireBuilder(Parameters param, ResourceBundle myResource) {
		super(param, myResource);
	}

	@Override
	public Runner initRunner() {
		return new Runner(this.getCells(), this.getCellGrid());
	}

	@Override
	protected Cell initCell(GridPosition gp) {
		int r = gp.getRow();
		int c = gp.getCol();
		SpreadingFireCell sfCell;
		if (pars.isSetByLocations()) {
			if (pars.getFireCells().contains(gp))  {
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
			else {
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
			ErrorPop error = new ErrorPop(300, 200, this.getResource().getString("FireError"), this.getResource());
			error.popup();
		}
		pars = (SFParameters) this.getParam();
	}

	@Override
	protected void prepareForInitCells() {
		if (pars.isSetByLocations()) {
			predefinedFire = new HashSet<GridPosition>(pars.getFireCells());
		}
	}
}
