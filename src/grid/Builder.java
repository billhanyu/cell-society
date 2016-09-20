package grid;

import cell.Cell;
import cell.CellGraphic;

public class Builder {
	private double width;
	private double height;
	private double cellWidth;
	private double cellHeight;
	private double startX; // top left corner of the grid
	private double startY;
	private int numRows;
	private int numCols;
	
	public Runner buildGrid() {
		Runner runner = new Runner();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				CellGraphic graphic = new CellGraphic(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
				Cell cell = buildCell(graphic);
				runner.addCell(cell);
			}
		}
		return runner;
	}
	
	protected Cell buildCell(CellGraphic graphic) {
		//TODO: override this method to actually return a cell
		return null;
	}
}
