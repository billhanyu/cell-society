package SpreadingFire;

import java.util.ResourceBundle;
import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.CellGraphic;
import grid.Parameters;
import grid.Runner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

	protected CellGraphic initCellGraphic(Cell cell, GridPosition gp) {
		int r = gp.getRow();
		int c = gp.getCol();
		Rectangle rect = new Rectangle(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
		CellGraphic g = new CellGraphic(new GridPosition(r, c));
		rect.setFill(cell.getCurrState().getColor());
		rect.setStroke(Color.BLACK);
		g.setGraphic(rect);
		return g;
	}

	@Override
	protected void addAllNeighbors(Cell c) {
		addSidesAsNeighbors(c);
	}

	@Override
	protected void readParameters() {
		if (!(param instanceof SFParameters)) {
			ErrorPop error = new ErrorPop(300, 200, myResource.getString("FireError"), myResource);
			error.popup();
		}
		pars = (SFParameters) param;
		cellWidth = (double)width / numCols;
		cellHeight = cellWidth;
	}

	@Override
	protected void prepareForInitCells() {
	}
}
