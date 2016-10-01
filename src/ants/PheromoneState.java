package ants;

import javafx.scene.paint.Color;
import cell.State;

public class PheromoneState extends State {

	private int foodPheromone = 0;
	private int homePheromone = 0;

	public PheromoneState(Color c, String n) {
		super(c, n);
		// TODO Auto-generated constructor stub
	}

	public int getFoodPheromone() {
		return foodPheromone;
	}

	public void setFoodPheromone(int foodPheromone) {
		this.foodPheromone = foodPheromone;
	}
	
	public void addFoodPheromone(int toAdd) {
		this.foodPheromone += toAdd;
	}

	public int getHomePheromone() {
		return homePheromone;
	}

	public void setHomePheromone(int homePheromone) {
		this.homePheromone = homePheromone;
	}
	
	public void addHomePheromone(int toAdd) {
		this.homePheromone += toAdd;
	}

	
	public void evaporatePheromones(double evaporationRatio){
		foodPheromone = (int) (evaporationRatio * foodPheromone);
		homePheromone = (int) (evaporationRatio * homePheromone);
	}

}
