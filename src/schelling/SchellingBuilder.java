package schelling;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.CellGraphic;
import grid.Parameters;
import grid.Runner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.ErrorPop;

public class SchellingBuilder extends Builder {

	private SLParameters pars;
	private double emptyRatio;
	private double ratio;

	public SchellingBuilder(Parameters param) {
		super(param);
	}

	@Override
	protected Runner initRunner() {
		return new SchellingRunner(cells, cellGrid);
	}

	@Override
	protected void initCells() {
		// initialize grid of cells
		for(int r = 0; r < numRows; r++) {
			for(int c = 0; c < numCols; c++) {
				GridPosition gp = new GridPosition(r, c);
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
				cells.add(slCell);
				Rectangle rect = new Rectangle(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
				CellGraphic g = new CellGraphic(new GridPosition(r, c));
				rect.setFill(slCell.getCurrState().getColor());
				rect.setStroke(Color.BLACK);
				g.setGraphic(rect);
				cellGrid.put(slCell, g);
			}
		}
	}


	@Override
	protected void addAllNeighbors(Cell c) {
		addSidesAsNeighbors(c);
		addCornersAsNeighbors(c);
	}

	@Override
	protected void readParameters() {
		if (!(param instanceof SLParameters)) {
			// not supposed to happen
			ErrorPop error = new ErrorPop(300, 200, "Error reading Schelling Parameters");
			error.popup();
		}
		pars = (SLParameters) param;
		cellWidth = (double)width / numCols;
		cellHeight = cellWidth;
		emptyRatio = pars.getEmptyRatio();
		ratio = pars.getRatio();
	}
}
