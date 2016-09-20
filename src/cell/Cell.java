package cell;

public abstract class Cell {
	private CellGraphic graphic;
	
	public Cell(CellGraphic graphic) {
		setGraphic(graphic);
	}
	
	public void setGraphic(CellGraphic graphic) {
		this.graphic = graphic;
	}
	
	public CellGraphic getGraphic() {
		return graphic;
	}
}
