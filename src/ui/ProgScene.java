package ui;

import javafx.scene.Scene;

public abstract class ProgScene {
	protected double width;
	protected double height;
	
	public ProgScene(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public abstract Scene initScene();
}
