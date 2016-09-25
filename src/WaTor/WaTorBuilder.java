package WaTor;

import cell.Cell;
import cell.GridPosition;
import grid.Builder;
import grid.CellGraphic;
import grid.Parameters;
import grid.Runner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.ErrorPop;

public class WaTorBuilder extends Builder {

	private WTParameters pars;
	private double emptyRatio;
	private double ratio;

	public WaTorBuilder(Parameters param) {
		super(param);
	}

	@Override
	protected Runner initRunner() {
		return new WaTorRunner(cells, cellGrid);
	}

	@Override
	protected void readParameters() {
		if (!(param instanceof WTParameters)) {
			ErrorPop error = new ErrorPop(300, 200, "Error Reading WTParameters");
			error.popup();
		}
		pars = (WTParameters) param;
		cellWidth = (double)width / numCols;
		cellHeight = cellWidth;
		emptyRatio = pars.getEmptyRatio();
		ratio = pars.getRatio();
	}

	@Override
	protected void initCells() {
		// initialize grid of cells
		for(int r = 0; r < numRows; r++) {
			for(int c = 0; c < numCols; c++) {
				GridPosition gp = new GridPosition(r, c);
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
				cells.add(wtCell);
				Rectangle rect = new Rectangle(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
				CellGraphic g = new CellGraphic(new GridPosition(r, c));
				rect.setFill(wtCell.getCurrState().getColor());
				rect.setStroke(Color.BLACK);
				g.setGraphic(rect);
				cellGrid.put(wtCell, g);
			}
		}
	}

	@Override
	protected void addAllNeighbors(Cell c) {
		this.addSidesAsNeighbors(c);
		this.addCornersAsNeighbors(c);
		this.addSidesAcrossBoardAsNeighbors(c);
		this.addCornersAcrossBoardAsNeighbors(c);
	}

}
