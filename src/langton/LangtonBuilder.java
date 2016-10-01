package langton;

import java.util.ResourceBundle;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import ui.ErrorPop;

public class LangtonBuilder extends Builder {
	
	private LTParameters pars;

	public LangtonBuilder(Parameters param, ResourceBundle myResource) {
		super(param, myResource);
	}

	@Override
	protected Runner initRunner() {
		return new LangtonRunner(this.getCells(), this.getCellGrid());
	}

	@Override
	protected void readParameters() {
		if (!(this.getParam() instanceof LTParameters)) {
			ErrorPop error = new ErrorPop(300, 200, this.getResource().getString("Langton Error"), this.getResource());
			error.popup();
		}
		pars = (LTParameters) this.getParam();
	}

	@Override
	protected void prepareForInitCells() {
	}

	@Override
	protected Cell initCell(GridPosition gp) {
		// TODO Init cell states
		return null;
	}

	@Override
	protected void addRectNeighbors(Cell c) {
		this.getNeighborAdder().addSidesAsNeighbors(c);
	}
}
