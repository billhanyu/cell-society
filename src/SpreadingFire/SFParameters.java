package SpreadingFire;

import java.util.Collection;
import cell.GridPosition;
import grid.Parameters;

public class SFParameters extends Parameters {
        
    private double probCatch;
    private Collection<GridPosition> listOfFire;
      
      
    public SFParameters (Parameters p) {
        super(p);
    }

    public SFParameters(Parameters p, double probCatch){
        super(p);
	this.probCatch = probCatch;
	setByLocations(false);
    }
    
    public SFParameters(Parameters p, double probCatch, Collection<GridPosition> fireCells){
        this(p, probCatch);
        this.listOfFire = fireCells;        
        setByLocations(true);
    }
    
    public double getProbCatch() {
	return probCatch;
    }
    
    public Collection<GridPosition> getFireCells(){
        return listOfFire;
    }
    
    public void setProbCatch(double probCatch){
    	this.probCatch = probCatch;
    }
	    
}
