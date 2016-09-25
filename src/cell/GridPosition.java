package cell;

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
	    final int prime = 31;
	        int result = 1;
	        result = prime * result + (gridRow);
	        result = prime * result + (gridCol);
	        return result;
	}
	
	public int getRow(){
		return gridRow;
	}
	
	public int getCol(){
		return gridCol;
	}
	

}
