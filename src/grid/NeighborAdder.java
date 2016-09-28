package grid;

import cell.Cell;

public class NeighborAdder {
	
	private Cell[][] neighborGrid;
	private int numRows;
	private int numCols;
	private int top;
	private int right;
	private int left;
	private int bottom;
	
	public NeighborAdder(Cell[][] neighborGrid, int numRows, int numCols,
			int top, int right, int left, int bottom) {
		this.neighborGrid = neighborGrid;
		this.numRows = numRows;
		this.numCols = numCols;
		this.top = top;
		this.right = right;
		this.left = left;
		this.bottom = bottom;
	}
	
	public void addSidesAsNeighbors(Cell c){
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		if (row > 0) {
			c.addNeighbor(neighborGrid[row - 1][col]);
		}
		if (row < numRows - 1) {
			c.addNeighbor(neighborGrid[row + 1][col]);
		}
		if (col > 0) {
			c.addNeighbor(neighborGrid[row][col - 1]);
		}
		if (col < numCols - 1) {
			c.addNeighbor(neighborGrid[row][col + 1]);
		}
	}

	public void addSidesAcrossBoardAsNeighbors(Cell c){
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		if (row == 0) {
			c.addNeighbor(neighborGrid[numRows - 1][col]);
		}
		if (row == numRows - 1) {
			c.addNeighbor(neighborGrid[0][col]);
		}
		if (col == 0) {
			c.addNeighbor(neighborGrid[row][numCols - 1]);
		}
		if (col == numCols - 1) {
			c.addNeighbor(neighborGrid[row][0]);
		}
	}

	public void addCornersAsNeighbors(Cell c){
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		if (row > 0 && col > 0) {
			c.addNeighbor(neighborGrid[row - 1][col - 1]);
		}
		if (row < numRows - 1 && col < numCols - 1) {
			c.addNeighbor(neighborGrid[row + 1][col + 1]);
		}
		if (col > 0 && row < numRows - 1) {
			c.addNeighbor(neighborGrid[row + 1][col - 1]);
		}
		if (col < numCols - 1 && row > 0) {
			c.addNeighbor(neighborGrid[row - 1][col + 1]);
		}
	}

	public void addCornersAcrossBoardAsNeighbors(Cell c){
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		// TOP ROW
		if (row == top) {
			// UPPER LEFT
			if(col == left)
				c.addNeighbor(neighborGrid[bottom][right]);
			else
				c.addNeighbor(neighborGrid[bottom][col - 1]);
			// UPPER RIGHT
			if(col == right)
				c.addNeighbor(neighborGrid[bottom][left]);
			else
				c.addNeighbor(neighborGrid[bottom][col + 1]);
		}
		// BOTTOM ROW
		if (row == bottom) {
			// BOTTOM LEFT
			if(col == left)
				c.addNeighbor(neighborGrid[top][right]);
			else
				c.addNeighbor(neighborGrid[top][col - 1]);
			// BOTTOM RIGHT
			if(col == right)
				c.addNeighbor(neighborGrid[top][left]);
			else
				c.addNeighbor(neighborGrid[top][col + 1]);
		}
		// LEFT COLUMN excluding 4 corners (grid corners already handled above)
		if (col == left) {
			// UPPER LEFT
			if(row != top)
				c.addNeighbor(neighborGrid[row - 1][right]);
			// BOTTOM LEFT
			if(row != bottom)
				c.addNeighbor(neighborGrid[row + 1][right]);
		}
		if (col == right) {
			// UPPER LEFT
			if(row != top)
				c.addNeighbor(neighborGrid[row - 1][left]);
			// BOTTOM LEFT
			if(row != bottom)
				c.addNeighbor(neighborGrid[row + 1][left]);
		}
	}
}
