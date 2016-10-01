package ants;

import grid.Parameters;

public class AntParameters extends Parameters {
	
	private int maxNumAnts;
	private int minAmountOfPheromone = 0;
	private int maxAmountOfPheromone = 1000;
	private int antsBornPerTimeStep = 2;
	private int maxAntAge = 500;
	private double evaporationRatio = 0.001;
	private double diffusionRatio = 0.001;
	private double K = 0.001;
	private double N = 10.0;


	public AntParameters(Parameters p) {
		super(p);
	}

	public AntParameters(Parameters p, int maxNumAnts, int minAmountOfPheromone,
			int maxAmountOfPheromone, int antsBornPerTimeStep, int maxAntAge,
			double evaporationRatio, double diffusionRatio) {
		super(p);
		this.maxNumAnts = maxNumAnts;
		this.maxAmountOfPheromone = maxAmountOfPheromone;
		this.antsBornPerTimeStep = antsBornPerTimeStep;
		this.setMaxAntAge(maxAntAge);
		this.evaporationRatio = evaporationRatio;
		this.diffusionRatio = diffusionRatio;
	}

	public int getMaxNumAnts() {
		return maxNumAnts;
	}

	public void setMaxNumAnts(int maxNumAnts) {
		this.maxNumAnts = maxNumAnts;
	}

	public int getMinAmountOfPheromone() {
		return minAmountOfPheromone;
	}

	public void setMinAmountOfPheromone(int minAmountOfPheromone) {
		this.minAmountOfPheromone = minAmountOfPheromone;
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

}
