package ants;

import java.util.Collection;
import cell.GridPosition;
import grid.Parameters;

public class AntParameters extends Parameters {
	
	private int maxNumAnts;
	private int maxAmountOfPheromone;
	private int antsBornPerTimeStep;
	private int maxAntAge;
	private double evaporationRatio;
	private double diffusionRatio;
	private double K;
	private double N;
	
	private Collection<GridPosition> nestCells;
	private Collection<GridPosition> foodCells;


	public AntParameters(Parameters p) {
		super(p);
	}

	public AntParameters(Parameters p, int maxNumAnts, int maxAmountOfPheromone, 
	                     int antsBornPerTimeStep, int maxAntAge, 
	                     double evaporationRatio, double diffusionRatio, double K, double N,
	                     Collection<GridPosition> nestCell, Collection<GridPosition> foodCell) {
		super(p);
		this.maxNumAnts = maxNumAnts;
		this.maxAmountOfPheromone = maxAmountOfPheromone;
		this.antsBornPerTimeStep = antsBornPerTimeStep;
		this.setMaxAntAge(maxAntAge);
		this.evaporationRatio = evaporationRatio;
		this.diffusionRatio = diffusionRatio;
		this.K = K;
		this.N = N;
		this.nestCells = nestCell;
		this.foodCells = foodCell;
		setByLocations(false);
	}

	public int getMaxNumAnts() {
		return maxNumAnts;
	}

	public void setMaxNumAnts(int maxNumAnts) {
		this.maxNumAnts = maxNumAnts;
	}


	public int getMaxAmountOfPheromone() {
		return maxAmountOfPheromone;
	}

	public void setMaxAmountOfPheromone(int maxAmountOfPheromone) {
		this.maxAmountOfPheromone = maxAmountOfPheromone;
	}

	public int getAntsBornPerTimeStep() {
		return antsBornPerTimeStep;
	}

	public void setAntsBornPerTimeStep(int antsBornPerTimeStep) {
		this.antsBornPerTimeStep = antsBornPerTimeStep;
	}

	public int getMaxAntAge() {
		return maxAntAge;
	}

	public void setMaxAntAge(int maxAntAge) {
		this.maxAntAge = maxAntAge;
	}

	public double getEvaporationRatio() {
		return evaporationRatio;
	}

	public void setEvaporationRatio(double evaporationRatio) {
		this.evaporationRatio = evaporationRatio;
	}

	public double getDiffusionRatio() {
		return diffusionRatio;
	}

	public void setDiffusionRatio(double diffusionRatio) {
		this.diffusionRatio = diffusionRatio;
	}

	public double getK() {
		return K;
	}

	public void setK(double k) {
		K = k;
	}

	public double getN() {
		return N;
	}

	public void setN(double n) {
		N = n;
	}
	
	public Collection<GridPosition> getListOfNest(){
	    return nestCells;
	}
	
	public Collection<GridPosition> getListOfFood(){
	    return foodCells;
	}

}
