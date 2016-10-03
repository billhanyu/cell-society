package grid;

import cell.Cell;

/**
 * @author billyu
 * helper class to add neighbors for cell
 * include many rules: sides, corners, etc
 * also take care of triangles and hexagons
 */
public class NeighborAdder {
	
	private Cell[][] neighborGrid;
	private int top;
	private int right;
	private int left;
	private int bottom;
	
	public NeighborAdder(Cell[][] neighborGrid, int top, int right, int left, int bottom) {
		this.neighborGrid = neighborGrid;
		this.top = top;
		this.right = right;
		this.left = left;
		this.bottom = bottom;
	}
	
	public void addSidesAsNeighbors(Cell c){
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		checkAddNeighbor(c, row - 1, col);
		checkAddNeighbor(c, row + 1, col);
		checkAddNeighbor(c, row, col - 1);
		checkAddNeighbor(c, row, col + 1);
	}

	public void addSidesAcrossBoardAsNeighbors(Cell c){
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		if (row == top) {
			c.addNeighbor(neighborGrid[bottom][col]);
		}
		if (row == bottom) {
			c.addNeighbor(neighborGrid[top][col]);
		}
		if (col == left) {
			c.addNeighbor(neighborGrid[row][right]);
		}
		if (col == right) {
			c.addNeighbor(neighborGrid[row][left]);
		}
	}

	public void addCornersAsNeighbors(Cell c){
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		checkAddNeighbor(c, row - 1, col - 1);
		checkAddNeighbor(c, row + 1, col + 1);
		checkAddNeighbor(c, row + 1, col - 1);
		checkAddNeighbor(c, row - 1, col + 1);
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
	
	public void addTriangleNeighbors(Cell c) {
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		checkAddNeighbor(c, row - 1, col - 1);
		checkAddNeighbor(c, row - 1, col);
		checkAddNeighbor(c, row - 1, col + 1);
		checkAddNeighbor(c, row, col - 1);
		checkAddNeighbor(c, row, col + 1);
		checkAddNeighbor(c, row + 1, col - 1);
		checkAddNeighbor(c, row + 1, col);
		checkAddNeighbor(c, row + 1, col + 1);
	}
	
	public void addHexagonNeighbors(Cell c) {
		int row = c.getGridPosition().getRow();
		int col = c.getGridPosition().getCol();
		int offset = row % 2 == 0 ? -1 : 1;
		checkAddNeighbor(c, row, col - 1);
		checkAddNeighbor(c, row, col + 1);
		checkAddNeighbor(c, row - 1, col);
		checkAddNeighbor(c, row + 1, col);
		checkAddNeighbor(c, row - 1, col + offset);
		checkAddNeighbor(c, row + 1, col + offset);
	}
	
	private void checkAddNeighbor(Cell cell, int r, int c) {
		if (r < top || r > bottom || c < left || c > right) {
			return;
		}
		cell.addNeighbor(neighborGrid[r][c]);
	}
}
