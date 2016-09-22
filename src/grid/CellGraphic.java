package grid;

import cell.GridPosition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class CellGraphic {
	private Shape graphic;
	private double positionX;
	private double positionY;
	private double width;
	private double height;
	private GridPosition gridPos;
	private Color shapeColor;
	
	public CellGraphic(double x, double y, double width, double height) {
		setPosition(x, y);
		setWidth(width);
		setHeight(height);
	}

	public Shape getGraphic() {
		return graphic;
	}

	public void setGraphic(Shape graphic) {
		this.graphic = graphic;
	}
	
	public void setPosition(double x, double y) {
		positionX = x;
		positionY = y;
	}
	
	public double getPositionX() {
		return positionX;
	}
	
	public double getPositionY() {
		return positionY;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setColor(Color color){
		shapeColor = color;
		//TODO: set shape color
	}

	public GridPosition getGridPos() {
		return gridPos;
	}

	public void setGridPos(GridPosition gridPos) {
		this.gridPos = gridPos;
	}
}
