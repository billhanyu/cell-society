package ui;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

public class SimulationPane {
	private Group root;
	
	public SimulationPane() {
		root = new Group();
	}
	
	public void addShape(Shape s) {
		root.getChildren().add(s);
	}
	
	public Group getGroup() {
		return root;
	}
}
