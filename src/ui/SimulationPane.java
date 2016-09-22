package ui;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

public class SimulationPane {
	private Group root;
	private double width;
	private double height;
	
	public SimulationPane(double width, double height) {
		this.width = width;
		this.height = height;
		root = new Group();
	}
	
	public void addShape(Shape s) {
		root.getChildren().add(s);
	}
	
	public Group getGroup() {
		return root;
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
}
