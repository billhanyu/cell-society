package ants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;
import cell.Cell;
import cell.GridPosition;
import cell.State;

public class AntCell extends Cell{

	AntParameters params;

	// IDENTITY STATES
	protected PheromoneState pheromoneState;
	private List<AntState> antList;

	// DISPLAY STATES
	public static State nest = new State(Color.GOLD, "NEST");
	public static State food = new State(Color.RED, "FOOD");
	public static State populatedByAnts = new State(AntState.myColor, "ANTS");
	public static State obstacle = new State(Color.BLACK, "OBSTACLE");
	public static State lowPheromoneConcentration = new State(Color.LIGHTGREEN, "LOW PHEROMONE");
	public static State mediumPheromoneConcentration = new State(Color.FORESTGREEN, "MEDIUM PHEROMONE");
	public static State highPheromoneConcentration = new State(Color.DARKGREEN, "HIGH PHEROMONE");
	private final double MEDIUM_CUTOFF = 0.33;
	private final double HIGH_CUTOFF = 0.67;

	public AntCell(GridPosition gp, State s, AntParameters params) {
		super(gp, s);
		this.params = params;
	}

	@Override
	public void checkChangeState() {
		birthAntsIfNest();
		if(getNumAnts() > 0){
			checkIfAnyDeadAnts();
			moveAnts();
		}
		pheromoneState.evaporatePheromones(params.getEvaporationRatio());
		diffusePheromones(params.getDiffusionRatio());


		// SET FUTURE / DISPLAY STATE
		double avgPheromones = ((double) pheromoneState.getFoodPheromone() + pheromoneState.getHomePheromone() )/ 2 ;
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
		for(AntState ant: antList){
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
		if (! isValidToMove(forwardWithMaxHome)){
			forwardWithMaxHome = cellWithMostHome(getNeighbors());
		}
		if(isValidToMove(forwardWithMaxHome)){
			dropFoodPheromones();
			moveAnt(ant, forwardWithMaxHome);
		}





	}

	private void dropFoodPheromones() {
		// TODO Auto-generated method stub
		
	}

	private void antFindFoodSource(AntState ant){
		if (getCurrState().equals(food))
			ant.setOrientation(cellWithMostFood(getNeighbors()));
		if(getCurrState().equals(nest)){
			ant.hasFoodItem = true;
			return;
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
		
		acell.antList.add(ant);
		this.antList.remove(ant);
	}

	private boolean isValidToMove(AntCell ac){
		return (ac.getNumAnts() >= params.getMaxNumAnts() && ! ac.getCurrState().equals(obstacle));

	}

	private AntCell cellWithMostHome(List<Cell> cellsToCheck){
		int maxHomeFound = -1;
		AntCell toMove = (AntCell) this;
		for (Cell n : cellsToCheck){
			int nHome = ((AntCell) n).pheromoneState.getHomePheromone();
			if( nHome > maxHomeFound ){
				maxHomeFound = nHome;
				toMove = (AntCell) n;
			}
		}
		return toMove;
	}

	private AntCell cellWithMostFood(List<Cell> cellsToCheck){
		int maxFoodFound = -1;
		AntCell toMove = (AntCell) this;
		for (Cell n : cellsToCheck){
			int nFood = ((AntCell) n).pheromoneState.getFoodPheromone();
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
		possibleForward.add(front);

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

		if(left != null)
			possibleForward.add(left);
		if(right != null)
			possibleForward.add(left);

		return possibleForward;
	}

	private Cell checkIfHasThisNeighbor(int r, int c){
		for (Cell n : getNeighbors()){
			if(n.getGridPosition().getRow() == r && n.getGridPosition().getCol() == c)
				return n;	
		}
		return null;
	}
	
	private Cell selectRandLocation(List<Cell> possible){
		List<Cell> trulyPossible = new ArrayList<Cell>(possible);
		double probabilitySum = 0.0;
		
		for(Cell c : possible){
			if( ! isValidToMove((AntCell) c) )
				trulyPossible.remove(c);
			else
				probabilitySum += Math.pow((params.getK() + ((AntCell) c).pheromoneState.getFoodPheromone()), params.getN());
		}
		
		double stop = Math.random() * probabilitySum;
		
		double stoppingPointSum = 0;
		for (int i = 0; stoppingPointSum < stop; i++){
			stoppingPointSum += Math.pow((params.getK() + ((AntCell) trulyPossible.get(i)).pheromoneState.getFoodPheromone()), params.getN());
		}
		
		
		return null;
	}

	private void diffusePheromones(double diffusionRatio) {
		// TODO Auto-generated method stub

	}

	public int getNumAnts(){
		return antList.size();
	}

	private void birthAntsIfNest(){
		for(int i = 0; i < params.getAntsBornPerTimeStep(); i++)
			antList.add(new AntState(params.getMaxAntAge()));
	}

	private void checkIfAnyDeadAnts(){
		List<AntState> toRemove = new ArrayList<AntState>();
		for (AntState ant : antList){
			if (ant.isTooOld()){
				toRemove.add(ant);
			}
		}
		antList.removeAll(toRemove);
	}

}
