package schelling;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.CellGraphic;
import grid.Parameters;
import grid.Runner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SchellingBuilder extends Builder {

	private SLParameters pars;
	private double emptyRatio;
	private double ratio;

	public SchellingBuilder(Parameters param) {
		super(param);
	}

	@Override
	protected Runner initRunner() {
		if (!(param instanceof SLParameters)) {
			// not supposed to happen
			return null;
		}
		pars = (SLParameters) param;
		cellWidth = (double)width / numCols;
		cellHeight = cellWidth;
		emptyRatio = pars.getEmptyRatio();
		ratio = pars.getRatio();
		initCells();
		giveAllCellsNeighbors();
		return new SchellingRunner(cells, cellGrid);
	}

	@Override
	protected void initCells() {
		// initialize grid of cells
		for(int r = 0; r < numRows; r++) {
			for(int c = 0; c < numCols; c++) {
				SchellingCell slCell = new SchellingCell(new GridPosition(r,c));
				double rnd = Math.random();
				if (rnd < emptyRatio) {
					slCell.setFutureState(SchellingCell.vacant);
				}
				else if (rnd > (emptyRatio + ratio)/(ratio + 1)) {
					slCell.setFutureState(SchellingCell.personO);
				}
				else {
					slCell.setFutureState(SchellingCell.personX);
				}
				cells.add(slCell);
				Rectangle rect = new Rectangle(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
				CellGraphic g = new CellGraphic(new GridPosition(r, c));
				rect.setFill(slCell.getFutureState().getColor());
				rect.setStroke(Color.BLACK);
				g.setGraphic(rect);
				cellGrid.put(slCell, g);
			}
		}
	}

	@Override
	protected void addAllNeighbors(Cell c) {
		addSidesAsNeighbors(c);
	}
}
