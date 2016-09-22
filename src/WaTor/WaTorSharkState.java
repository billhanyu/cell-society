package WaTor;

import javafx.scene.paint.Color;

public class WaTorSharkState extends WaTorState{
	
	private int energy;
	private static Color myColor = Color.BLUE;
	private static String myName = "SHARK";


	public WaTorSharkState(int chrononsSinceReproduction, int energy) {
		super(Color.BLUE, "Shark", chrononsSinceReproduction);
		this.energy = energy;
	}
	
	public WaTorSharkState() {
		super(myColor, myName, 0);
		energy = WaTorCell.SHARK_ENERGY;
	}


	public void loseEnergy(){
		energy--;
	}
	
	public void increaseEnergy(int increment){
		energy += increment;
	}

}
