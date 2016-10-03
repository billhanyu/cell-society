package ants;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import cell.Cell;
import cell.GridPosition;
import cell.State;

public class AntCell extends Cell{

	AntParameters params;

	// IDENTITY STATES
	private PheromoneState pheromoneState;
	private List<AntState> antList;

	// DEFAULT DISPLAY STATES
	public static State nest = new State(Color.GOLD, "NEST");
	public static State food = new State(Color.RED, "FOOD");
	public static State empty = new State(Color.GRAY, "EMPTY");
	public static State obstacle = new State(Color.PURPLE, "OBSTACLE");

	// ANT CELL DISPLAY STATES
	public static State populatedByAnts = new State(AntState.myColor, "ANTS");
	public static State lowPheromoneConcentration = new State(Color.LIGHTGREEN, "LOW PHEROMONE");
	public static State mediumPheromoneConcentration = new State(Color.FORESTGREEN, "MEDIUM PHEROMONE");
	public static State highPheromoneConcentration = new State(Color.DARKGREEN, "HIGH PHEROMONE");

	private final double LOW_CUTOFF= 0.05;
	private final double MEDIUM_CUTOFF = 0.33;
	private final double HIGH_CUTOFF = 0.67;

	/**
	 * @param gp
	 * @param s is one of the four DEFAULT DISPLAY STATES (nest, food, empty, obstacle)
	 * @param params
	 */
	public AntCell(GridPosition gp, State s, AntParameters params) {
		super(gp, s);
		this.params = params;
		this.pheromoneState = new PheromoneState(1.0, 1.0, params);
		antList = new ArrayList<AntState>();
		if (s.equals(nest))
			pheromoneState.setHomePheromone(params.getMaxAmountOfPheromone());
		if (s.equals(food))
			pheromoneState.setFoodPheromone(params.getMaxAmountOfPheromone());
	}

	public PheromoneState getPheromoneState() {
		return pheromoneState;
	}

	public void setPheromoneState(PheromoneState pheromoneState) {
		this.pheromoneState = pheromoneState;
	}

	public List<AntState> getAntList() {
		return antList;
	}

	public void setAntList(List<AntState> antList) {
		this.antList = antList;
	}

	@Override
	public void checkChangeState() {
		boolean hadAnt = (getNumAnts() > 0);

		if(getCurrState().equals(nest) && getNumAnts() <= params.getMaxNumAnts())
			birthAntsIfNest();
		if(getNumAnts() > 0){
			checkIfAnyDeadAnts();
			moveAnts();
		}
		diffusePheromones(params.getDiffusionRatio());
		getPheromoneState().evaporatePheromones(params.getEvaporationRatio());


		// SET FUTURE / DISPLAY STATE
		double totalPheromones = getPheromoneState().getFoodPheromone() + getPheromoneState().getHomePheromone();
		// if currState is obstacle, nest, or food, leave it be
		if( ! (getCurrState().equals(nest) || getCurrState().equals(food) || getCurrState().equals(obstacle)) ){
			// if there are ants, show it
			if(hadAnt){
				setFutureState(populatedByAnts);
			}
			// if not, show how many pheromones
			else {
				double max = params.getMaxAmountOfPheromone();
				if(totalPheromones < LOW_CUTOFF * max)
					setFutureState(empty);
				if(totalPheromones >= (LOW_CUTOFF * max))
					setFutureState(lowPheromoneConcentration);
				if(totalPheromones >= (MEDIUM_CUTOFF * max))
					setFutureState(mediumPheromoneConcentration);
				if(totalPheromones >= (HIGH_CUTOFF * params.getMaxAmountOfPheromone()))
					setFutureState(highPheromoneConcentration);
			}
		}
	}

	public int getNumAnts(){
		return getAntList().size();
	}

	private void birthAntsIfNest(){
		for(int i = 0; i < params.getAntsBornPerTimeStep(); i++)
			getAntList().add(new AntState(params.getMaxAntAge(), getNeighbors().get((int) Math.random() * getNeighbors().size()) ));
	}

	/**
	 * Increments all ant ages and
	 * removes them if they are too old / dead
	 */
	private void checkIfAnyDeadAnts(){
		List<AntState> toRemove = new ArrayList<AntState>();
		for (AntState ant : getAntList()){
			if (ant.isTooOld()){
				toRemove.add(ant);
			}
		}
		getAntList().removeAll(toRemove);
	}

	private void moveAnts() {
		for(AntState ant: getAntList()){
			if (ant.hasFoodItem)
				antReturnToNest(ant);
			else
				antFindFoodSource(ant);
		}	
	}

	/**
	 * Moves ant towards nest if possible
	 * Takes shortest path with no wandering
	 */
	private void antReturnToNest(AntState ant){
		if (getCurrState().equals(food))
			ant.setOrientation(cellWithMostHome(getNeighbors()));
		if (getCurrState().equals(nest)){
			ant.hasFoodItem = false;
			return;
		}
		AntCell forwardWithMaxHome = cellWithMostHome(getPossibleForward((AntCell) ant.getOrientation()));
		if (forwardWithMaxHome == null){
			forwardWithMaxHome = cellWithMostHome(getNeighbors());
		}
		if(isValidToMove(forwardWithMaxHome)){
			dropFoodPheromones(forwardWithMaxHome);
			moveAnt(ant, forwardWithMaxHome);
		}
	}

	private void dropFoodPheromones(AntCell acell) {
		if (getCurrState().equals(food))
			this.getPheromoneState().setFoodPheromone(params.getMaxAmountOfPheromone());
		else{
			double max = 0;
			for(Cell n : getNeighbors()){
				double fPher = ((AntCell) n).getPheromoneState().getFoodPheromone();
				if (fPher > max)
					max = fPher;
			}
			double deposit = max - 2 - this.getPheromoneState().getFoodPheromone();
			if(deposit > 0)
				acell.getPheromoneState().addFoodPheromone(deposit);
		}
	}

	/**
	 * Moves ant towards food if possible
	 * Wanders along path decided by probability
	 * defined in selectWeightedRandomLocation
	 */
	private void antFindFoodSource(AntState ant){
		if (getCurrState().equals(nest))
			ant.setOrientation(cellWithMostFood(getNeighbors()));
		if(getCurrState().equals(food)){
			ant.hasFoodItem = true;
			return;
		}
		Cell possibleNewLoc = selectWeightedRandomLocation(getPossibleForward((AntCell) ant.getOrientation()));
		if (possibleNewLoc == null)
			possibleNewLoc = selectWeightedRandomLocation(getNeighbors());
		if(possibleNewLoc != null){
			dropHomePheromones((AntCell) possibleNewLoc);
			moveAnt(ant, (AntCell) possibleNewLoc);
		}
	}

	private void dropHomePheromones(AntCell acell) {
		if (getCurrState().equals(nest))
			this.getPheromoneState().setHomePheromone(params.getMaxAmountOfPheromone());
		else{
			double max = 0;
			for(Cell n : getNeighbors()){
				double hPher = ((AntCell) n).getPheromoneState().getHomePheromone();
				if (hPher > max)
					max = hPher;
			}
			double deposit = max - 2 - this.getPheromoneState().getHomePheromone();
			if(deposit > 0)
				acell.getPheromoneState().addHomePheromone(deposit);
		}
	}

	/**
	 * Both home and food pheromones diffuse from 
	 * cells with higher concentration to lower concentration
	 * away from food and nest
	 */
	private void diffusePheromones(double diffusionRatio) {
		for(Cell n : getNeighbors()){
			double thisFood = this.getPheromoneState().getFoodPheromone();
			double nFood = ((AntCell) n).getPheromoneState().getFoodPheromone();
			if(thisFood > nFood)
				((AntCell) n).getPheromoneState().addFoodPheromone((thisFood * diffusionRatio));
			double thisHome = this.getPheromoneState().getHomePheromone();
			double nHome = ((AntCell) n).getPheromoneState().getHomePheromone();
			if(thisHome > nHome)
				((AntCell) n).getPheromoneState().addHomePheromone((thisHome * diffusionRatio));
		}
	}

	/**
	 * moves @param ant to new cell @param acell
	 * Sets the new orientation of the ant to the cell
	 * in the same direction as it came from, unless
	 * that cell does not exist, in which case the ant randomly
	 * chooses a new orientation
	 */
	private void moveAnt(AntState ant, AntCell acell){		
		int myRow = acell.getGridPosition().getRow();
		int myCol = acell.getGridPosition().getCol();
		int rowInc = myRow - acell.getGridPosition().getRow();
		int colInc = myCol - acell.getGridPosition().getCol();

		Cell newOrientation = checkIfHasThisNeighbor(myRow + rowInc, myCol + colInc);

		// set orientation as a random neighbor if at edge of board
		if (newOrientation != null)
			ant.setOrientation(newOrientation);
		else
			ant.setOrientation(acell.getNeighbors().get(acell.getNeighbors().size()));

		acell.getAntList().add(ant);
		this.getAntList().remove(ant);
	}

	/**
	 * Cell is a valid location if there are fewer than the 
	 * max number of ants and if the cell is not an obstacle
	 */
	private boolean isValidToMove(AntCell ac){
		return (ac.getNumAnts() >= params.getMaxNumAnts() && ! ac.getCurrState().equals(obstacle));

	}

	private AntCell cellWithMostHome(List<Cell> cellsToCheck){
		double maxHomeFound = -1;
		AntCell toMove = (AntCell) this;
		for (Cell n : cellsToCheck){
			double nHome = ((AntCell) n).getPheromoneState().getHomePheromone();
			if( nHome > maxHomeFound ){
				maxHomeFound = nHome;
				toMove = (AntCell) n;
			}
		}
		return toMove;
	}

	private AntCell cellWithMostFood(List<Cell> cellsToCheck){
		double maxFoodFound = -1;
		AntCell toMove = (AntCell) this;
		for (Cell n : cellsToCheck){
			double nFood = ((AntCell) n).getPheromoneState().getFoodPheromone();
			if( nFood > maxFoodFound ){
				maxFoodFound = nFood;
				toMove = (AntCell) n;
			}
		}
		return toMove;
	}

	/**
	 * Given the forward oriented cell,
	 * returns possible forward cells (forward, foward left, and forward right)
	 * if they exist and are valid moves
	 * returns null if none are found
	 */
	private List<Cell> getPossibleForward(Cell front){
		int frontRow = front.getGridPosition().getRow();
		int frontCol = front.getGridPosition().getCol();
		int myRow = this.getGridPosition().getRow();
		int myCol = this.getGridPosition().getCol();

		List<Cell> possibleForward = new ArrayList<Cell>();

		Cell left;
		Cell right;
		if(frontRow == myRow){
			left = checkIfHasThisNeighbor(frontRow, frontCol - 1);
			right = checkIfHasThisNeighbor(frontRow, frontCol + 1);
		}
		else if(frontCol == myCol){
			left = checkIfHasThisNeighbor(frontRow - 1, frontCol);
			right = checkIfHasThisNeighbor(frontRow + 1, frontCol);
		}
		else{
			int r = myRow - frontRow;
			int c = myCol - frontCol;
			left = checkIfHasThisNeighbor(myRow + r, myCol);
			right = checkIfHasThisNeighbor(myRow, myCol + c);
		}

		if(isValidToMove((AntCell) front))
			possibleForward.add(front);
		if(left != null  && isValidToMove((AntCell) left))
			possibleForward.add(left);
		if(right != null && isValidToMove((AntCell) right))
			possibleForward.add(right);
		if(possibleForward.size() == 0)
			return null;
		return possibleForward;
	}

	/**
	 * checks if the current cell has a neighbor with
	 * a specific row and column
	 * Returns the cell if it exists, returns null otherwise
	 */
	private Cell checkIfHasThisNeighbor(int r, int c){
		for (Cell n : getNeighbors()){
			if(n.getGridPosition().getRow() == r && n.getGridPosition().getCol() == c)
				return n;	
		}
		return null;
	}

	/**
	 * Given a list of possible cells, returns a semi-random cell
	 * weighted by the equation given in caclculateProbability
	 */
	private Cell selectWeightedRandomLocation(List<Cell> possible){
		if (possible == null || possible.size() == 0)
			return null;
		List<Cell> trulyPossible = new ArrayList<Cell>(possible);
		double probabilitySum = 0.0;

		for(Cell c : possible){
			if( ! isValidToMove((AntCell) c) )
				trulyPossible.remove(c);
			else
				probabilitySum += calculateProbability((AntCell) c);
		}
		if (trulyPossible.size() == 0)
			return null;

		double stop = Math.random() * probabilitySum;
		double stoppingPointSum = 0;
		int i = 0;
		while(stoppingPointSum < stop){
			stoppingPointSum += calculateProbability((AntCell) trulyPossible.get(i));
			i++;
		}
		return trulyPossible.get(i);
	}

	/**
	 * Gives probability that cell is chosen based on equation:
	 * p = (k + foodPheromone)^N
	 */
	private double calculateProbability(AntCell c){
		return Math.pow((params.getK() + c.getPheromoneState().getFoodPheromone()), params.getN());
	}
}