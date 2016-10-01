package ui;

import init.Initializer;

public abstract class ProgScene {
	protected double width;
	protected double height;
	
	public ProgScene(){
		this.width = Initializer.SCENE_WIDTH;
		this.height = Initializer.SCENE_HEIGHT;
	}
	
	//public abstract Scene initScene(int size);
    
}
