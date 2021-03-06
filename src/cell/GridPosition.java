package cell;

/**
 * @author Addison Howenstine
 * 
 * Row and column position of a cell within a grid. Because
 * cells do not move themselves, this value will not change
 * during a simulation
 */

public class GridPosition {
	
	private int gridRow;
	private int gridCol;
	
	public GridPosition(GridPosition p){
	    this.gridRow = p.getRow();
	    this.gridCol = p.getCol();
	}
	public GridPosition(int r, int c){
		gridRow = r;
		gridCol = c;
	}
	
	/*
	 * GridPositions equal if they
	 * have the same row and column
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridPosition other = (GridPosition) obj;
		return (gridCol == other.gridCol && gridRow == other.gridRow);
	}
	
	@Override
	public int hashCode(){
            int prime = 31;
            int hashCode = 1;
            hashCode = prime * hashCode + gridRow;
            hashCode = prime * hashCode + gridCol;
	    return hashCode;
	}
	
	@Override
	public String toString(){
	    return gridRow + " " + gridCol;
	}
	public int getRow(){
		return gridRow;
	}
	
	public int getCol(){
		return gridCol;
	}
    

}
