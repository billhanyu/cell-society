package WaTor;

import javafx.scene.paint.Color;

public class WaTorFishState extends WaTorState{
	
	private static Color myColor = Color.GREEN;
	private static String myName = "FISH";
	
	public WaTorFishState(int chrononsSinceReproduction){
		super(myColor, myName, chrononsSinceReproduction);
	}	
	
	public WaTorFishState(){
		super(myColor, myName, 0);
	}
}
