package SpreadingFire;

import java.util.Collection;
import cell.GridPosition;
import grid.Parameters;

/**
 * 
 * @author Brian
 * Contains parameters for Spreading Fire Simulation. These parameters include:
 * probCatch : the probability that an adjacent cell will catch on fire
 * listOfFire : list of cells that begin initialized as fire
 * listOfEmpty: list of cells that begin initialized as empty
 */
public class SFParameters extends Parameters {
        
    private double probCatch;
    private Collection<GridPosition> listOfFire;
    private Collection<GridPosition> listOfEmpty;
      
      
    public SFParameters (Parameters p) {
        super(p);
    }

    public SFParameters(Parameters p, double probCatch){
        super(p);
	this.probCatch = probCatch;
	setByLocations(false);
    }
    
    public SFParameters(Parameters p, double probCatch, Collection<GridPosition> fireCells, Collection<GridPosition> emptyCells){
        this(p, probCatch);
        this.listOfFire = fireCells;
        this.listOfEmpty = emptyCells;
        setByLocations(true);
    }
    
    public double getProbCatch() {
	return probCatch;
    }
    
    public Collection<GridPosition> getFireCells(){
        return listOfFire;
    }
        
    public Collection<GridPosition> getEmptyCells(){
        return listOfEmpty;
    }
    
    public void setProbCatch(double probCatch){
    	this.probCatch = probCatch;
    }
	    
}
