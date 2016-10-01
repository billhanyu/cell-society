package ants;

import javafx.scene.paint.Color;
import cell.State;

public class PheromoneState extends State {

	private double foodPheromone = 0;
	private double homePheromone = 0;
	public static Color myColor = Color.GREEN;
	public static String myName = "PHEROMONE";


	public PheromoneState() {
		super(myColor, myName);
	}

	public double getFoodPheromone() {
		return foodPheromone;
	}

	public void setFoodPheromone(double foodPheromone) {
		this.foodPheromone = foodPheromone;
	}
	
	public void addFoodPheromone(double toAdd) {
		this.foodPheromone += toAdd;
	}

	public double getHomePheromone() {
		return homePheromone;
	}

	public void setHomePheromone(double homePheromone) {
		this.homePheromone = homePheromone;
	}
	
	public void addHomePheromone(double toAdd) {
		this.homePheromone += toAdd;
	}

	
	public void evaporatePheromones(double evaporationRatio){
		foodPheromone = (evaporationRatio * foodPheromone);
		homePheromone = (evaporationRatio * homePheromone);
	}

}
