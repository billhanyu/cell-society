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

	// DISPLAY STATES
	public static State nest = new State(Color.GOLD, "NEST");
	public static State food = new State(Color.RED, "FOOD");
	public static State populatedByAnts = new State(AntState.myColor, "ANTS");
	public static State obstacle = new State(Color.PURPLE, "OBSTACLE");
	public static State lowPheromoneConcentration = new State(Color.LIGHTGREEN, "LOW PHEROMONE");
	public static State mediumPheromoneConcentration = new State(Color.FORESTGREEN, "MEDIUM PHEROMONE");
	public static State highPheromoneConcentration = new State(Color.DARKGREEN, "HIGH PHEROMONE");
	private final double MEDIUM_CUTOFF = 0.33;
	private final double HIGH_CUTOFF = 0.67;

	public AntCell(GridPosition gp, State s, AntParameters params, PheromoneState pheromoneState) {
		super(gp, s);
		this.params = params;
		this.pheromoneState = pheromoneState;
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
		birthAntsIfNest();
		if(getNumAnts() > 0){
			checkIfAnyDeadAnts();
			moveAnts();
		}
		getPheromoneState().evaporatePheromones(params.getEvaporationRatio());
		diffusePheromones(params.getDiffusionRatio());


		// SET FUTURE / DISPLAY STATE
		double avgPheromones = ((double) getPheromoneState().getFoodPheromone() + getPheromoneState().getHomePheromone() )/ 2 ;
		// if currState is obstacle, nest, or food, leave it be
		if( ! (getCurrState().equals(nest) || getCurrState().equals(food) || getCurrState().equals(obstacle)) ){
			// if there are ants, show it
			if(getNumAnts() > 0)
				setFutureState(populatedByAnts);
			// if not, show how many pheromones
			else {
				if(avgPheromones <= (MEDIUM_CUTOFF * params.getMaxAmountOfPheromone()))
					setFutureState(lowPheromoneConcentration);
				if(avgPheromones >= (MEDIUM_CUTOFF * params.getMaxAmountOfPheromone()))
					setFutureState(mediumPheromoneConcentration);
				if(avgPheromones >= (HIGH_CUTOFF * params.getMaxAmountOfPheromone()))
					setFutureState(highPheromoneConcentration);
			}
		}
	}
	private void moveAnts() {
		for(AntState ant: getAntList()){
			if (ant.hasFoodItem)
				antReturnToNest(ant);
			else
				antFindFoodSource(ant);
		}	
	}

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

	private void antFindFoodSource(AntState ant){
		if (getCurrState().equals(food))
			ant.setOrientation(cellWithMostFood(getNeighbors()));
		if(getCurrState().equals(nest)){
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

	private Cell checkIfHasThisNeighbor(int r, int c){
		for (Cell n : getNeighbors()){
			if(n.getGridPosition().getRow() == r && n.getGridPosition().getCol() == c)
				return n;	
		}
		return null;
	}

	private Cell selectWeightedRandomLocation(List<Cell> possible){
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

	private double calculateProbability(AntCell c){
		return Math.pow((params.getK() + c.getPheromoneState().getFoodPheromone()), params.getN());
	}

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

	public int getNumAnts(){
		return getAntList().size();
	}

	private void birthAntsIfNest(){
		for(int i = 0; i < params.getAntsBornPerTimeStep(); i++)
			getAntList().add(new AntState(params.getMaxAntAge()));
	}

	private void checkIfAnyDeadAnts(){
		List<AntState> toRemove = new ArrayList<AntState>();
		for (AntState ant : getAntList()){
			if (ant.isTooOld()){
				toRemove.add(ant);
			}
		}
		getAntList().removeAll(toRemove);
	}
}