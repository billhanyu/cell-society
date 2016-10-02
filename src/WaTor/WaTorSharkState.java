package WaTor;

import javafx.scene.paint.Color;

public class WaTorSharkState extends WaTorState{
	
	protected int energy;
	public static Color myColor = Color.BLUE;
	public static String myName = "SHARK";


	public WaTorSharkState(int chrononsSinceReproduction, int energy) {
		super(myColor, myName, chrononsSinceReproduction);
		this.energy = energy;
	}
	
	public WaTorSharkState(WTParameters p) {
		super(myColor, myName, 0);
		energy = p.getSharkStarve();
		chrononsSinceReproduction = 0;
	}


	public void loseEnergy(){
		energy--;
	}
	
	public void increaseEnergy(int increment){
		energy += increment;
	}

	@Override
	public WaTorState clone() {
		return new WaTorSharkState(chrononsSinceReproduction, energy);
	}

}
