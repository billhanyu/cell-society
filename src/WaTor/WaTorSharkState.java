package WaTor;

import javafx.scene.paint.Color;

public class WaTorSharkState extends WaTorState{
	
	protected int energy;
	private static Color myColor = Color.BLUE;
	private static String myName = "SHARK";


	public WaTorSharkState(int chrononsSinceReproduction, int energy) {
		super(Color.BLUE, "Shark", chrononsSinceReproduction);
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
	public WaTorState copy() {
		return new WaTorSharkState(chrononsSinceReproduction, energy);
	}

}
