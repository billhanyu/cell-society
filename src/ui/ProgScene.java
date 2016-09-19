package ui;

import javafx.scene.Scene;

public abstract class ProgScene {
	protected double width;
	protected double height;
	
	public ProgScene(double width, double height){
		
	}
	
	public abstract Scene initScene();
}
