package ants;

import javafx.scene.paint.Color;
import cell.State;

public class PheromoneState extends State {

	private double foodPheromone;
	private double homePheromone;
	public static Color myColor = Color.GREEN;
	public static String myName = "PHEROMONE";
	private AntParameters params;

	
	public PheromoneState(double foodPheromone, double homePheromone, AntParameters params){
		super(myColor, myName);
		this.foodPheromone = foodPheromone;
		this.homePheromone = homePheromone;
		this.params = params;
	}

	public double getFoodPheromone() {
		return foodPheromone;
	}

	public void setFoodPheromone(double foodPheromone) {
		this.foodPheromone = foodPheromone;
	}
	
	public void addFoodPheromone(double toAdd) {
		this.foodPheromone += toAdd;
		if(this.foodPheromone >= params.getMaxAmountOfPheromone())
			this.foodPheromone = params.getMaxAmountOfPheromone();
	}

	public double getHomePheromone() {
		return homePheromone;
	}

	public void setHomePheromone(double homePheromone) {
		this.homePheromone = homePheromone;
	}
	
	public void addHomePheromone(double toAdd) {
		this.homePheromone += toAdd;
		if(this.homePheromone >= params.getMaxAmountOfPheromone())
			this.homePheromone = params.getMaxAmountOfPheromone();

	}

	
	public void evaporatePheromones(double evaporationRatio){
		foodPheromone -= (evaporationRatio * foodPheromone);
		homePheromone -= (evaporationRatio * homePheromone);
	}

}
