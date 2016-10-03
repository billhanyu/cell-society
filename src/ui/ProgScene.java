package ui;

import init.Initializer;

/**
 * @author billyu
 * custom Scene class for this project
 * prevent duplication of width and height
 */
public abstract class ProgScene {
	protected double width;
	protected double height;
	
	public ProgScene(){
		this.width = Initializer.SCENE_WIDTH;
		this.height = Initializer.SCENE_HEIGHT;
	}
    
}
