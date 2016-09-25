package WaTor;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.paint.Color;
import cell.Cell;
import cell.GridPosition;
import cell.State;

public class WaTorCell extends Cell{
    
        WTParameters params;
	
	public WaTorCell(GridPosition gp, State s, WTParameters p) {
		super(gp, s);
		this.params = p;
	}

	public static State empty = new State(Color.GRAY, "EMPTY");

	@Override
	public void checkChangeState() {
		if (getCurrState().equals(new WaTorFishState()))
			fishSwim();
		if(getCurrState().equals(new WaTorSharkState(params)))
			sharkSwim();
	}

	private void fishSwim(){
		if(canReproduce(params.getFishRate())){
			if(canMoveLikeFish())
				this.setFutureState(new WaTorFishState());
		}
		else
			if(canMoveLikeFish())
				this.setFutureState(empty);	}

	private void sharkSwim(){
		if(isDead()){
			if(canReproduce(params.getSharkRate())){
				if(canMoveLikeShark())
					this.setFutureState(new WaTorSharkState(params));
			}
			else
				if(canMoveLikeShark())
					this.setFutureState(empty);
		}
	}

	private boolean isDead(){
		if( ((WaTorSharkState) getCurrState()).energy == 0){
			setFutureState(empty);
			return false;
		}
		((WaTorSharkState) getCurrState()).loseEnergy();
		return true;
	}

	private boolean canReproduce(int reproductionRate){
		if(((WaTorState) getCurrState()).getChrononsSinceReproduction() == reproductionRate){
			((WaTorState) getCurrState()).zeroChrononsSinceReproduction();
			return true;
		}
		((WaTorState) getCurrState()).incrementChrononsSinceReproduction();
		return false;
	}

	private boolean canMoveLikeShark(){
		ArrayList<Cell> shuffledNeighbors = new ArrayList<Cell>(getNeighbors());
		Collections.shuffle(shuffledNeighbors);
		for(Cell neighbor : shuffledNeighbors)
			if(neighbor.getFutureState().equals(new WaTorFishState())){
				((WaTorSharkState) getCurrState()).increaseEnergy(params.getEnergyFromEating());
				neighbor.setFutureState(this.getCurrState());
				return true;
			}
		return canMoveLikeFish();
	}

	private boolean canMoveLikeFish(){
		ArrayList<Cell> shuffledNeighbors = new ArrayList<Cell>(getNeighbors());
		Collections.shuffle(shuffledNeighbors);
		for(Cell neighbor : shuffledNeighbors)
			if(neighbor.getFutureState().equals(empty)){
				neighbor.setFutureState(this.getCurrState());
				return true;
			}
		return false;
	}
}