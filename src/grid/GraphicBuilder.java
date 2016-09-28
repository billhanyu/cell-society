package grid;

import cell.Cell;
import cell.GridPosition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class GraphicBuilder {
	
	private static final double sqrt3 = Math.pow(3, 0.5);
	private double squareUnit;
	private double triangleUnit;
	private double hexagonUnit;
	
	public GraphicBuilder(double square, double triangle, double hexagon) {
		this.squareUnit = square;
		this.triangleUnit = triangle;
		this.hexagonUnit = hexagon;
	}
	
	public CellGraphic initRectGraphic(Cell cell, GridPosition gp) {
		int r = gp.getRow();
		int c = gp.getCol();
		Rectangle rect = new Rectangle(c * squareUnit, r * squareUnit, squareUnit, squareUnit);
		CellGraphic g = new CellGraphic(gp);
		rect.setFill(cell.getCurrState().getColor());
		rect.setStroke(Color.BLACK);
		g.setGraphic(rect);
		return g;
	}

	public CellGraphic initTriangleGraphic(Cell cell, GridPosition gp) {
		int r = gp.getRow();
		int c = gp.getCol();
		double x = triangleUnit * (c + 1);
		double y = triangleUnit * sqrt3 * r;
		CellGraphic g = new CellGraphic(gp);
		Polygon gon = new Polygon();
		if ((r + c) % 2 == 0) {
			gon.getPoints().addAll(new Double[]{
					x, y,
					x - triangleUnit, y + sqrt3 * triangleUnit,
					x + triangleUnit, y + sqrt3 * triangleUnit
			});
		}
		else {
			gon.getPoints().addAll(new Double[]{
					x, y + sqrt3 * triangleUnit,
					x - triangleUnit, y,
					x + triangleUnit, y
			});
		}
		gon.setFill(cell.getCurrState().getColor());
		gon.setStroke(Color.BLACK);
		g.setGraphic(gon);
		return g;
	}

	public CellGraphic initHexagonGraphic(Cell cell, GridPosition gp) {
		int r = gp.getRow();
		int c = gp.getCol();
		CellGraphic g = new CellGraphic(gp);
		double offset = r % 2 == 0 ? 0 : sqrt3/2*hexagonUnit;
		double x = sqrt3 * hexagonUnit * c;
		double y = 3 * hexagonUnit / 2 * r;
		double xUnit = sqrt3 * hexagonUnit / 2;
		double yUnit = hexagonUnit / 2;
		Polygon gon = new Polygon();
		gon.getPoints().addAll(new Double[]{
				x + xUnit + offset, y,
				x + offset, y + yUnit,
				x + offset, y + 3 * yUnit,
				x + xUnit + offset, y + 4 * yUnit,
				x + 2 * xUnit + offset, y + 3 * yUnit,
				x + 2 * xUnit + offset, y + yUnit
		});
		gon.setFill(cell.getCurrState().getColor());
		gon.setStroke(Color.BLACK);
		g.setGraphic(gon);
		return g;
	}
}
