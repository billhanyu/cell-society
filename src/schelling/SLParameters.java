package schelling;

import java.util.Collection;
import cell.GridPosition;
import grid.Parameters;

/**
 * 
 * @author Brian
 * Contains parameters for Segregation simulation. These parameters include:
 * emptyRatio : ratio of overall cells that are empty
 * ratio : ratio of red cells:blue cells
 * idealRatio: minimum ratio of friendly neighbors needed to stay in one place
 * listOfRed : list of Cells that are initialized as Red
 * listOfBlue: list of Cells that are initialized as Blue
 * listOfEmpty: list of Cells that are initialized as Empty
 */
public class SLParameters extends Parameters {
    
        private double emptyRatio; // vacant ratio
        private double ratio; // red-blue ratio
        private double idealRatio; // the ratio for the cell to become satisfied
        
        private Collection<GridPosition> listOfRed;
        private Collection<GridPosition> listOfBlue;
        private Collection<GridPosition> listOfEmpty;
        
       
    	public SLParameters (Parameters p) {
            super(p);
        }
    	
    	public SLParameters (Parameters p, double ideal, double redBlue, double empty){
    	    super(p);
    	    this.idealRatio = ideal;
    	    this.ratio = redBlue;
    	    this.emptyRatio = empty;
    	    setByLocations(false);
    	}
    	
    	public SLParameters (Parameters p, double ideal, Collection<GridPosition> listOfRed, 
    	                     Collection<GridPosition> listOfBlue, Collection<GridPosition> listOfEmpty){
    	    super(p);
    	    this.idealRatio = ideal;
    	    this.listOfRed = listOfRed;
    	    this.listOfBlue = listOfBlue;
    	    this.listOfEmpty = listOfEmpty;
    	    this.emptyRatio = ((double) listOfEmpty.size())/(listOfRed.size() + listOfBlue.size() + listOfEmpty.size());
    	    System.out.println(this.emptyRatio);
    	    this.ratio = ((double) (listOfRed.size()/listOfBlue.size()));
    	    setByLocations(true);
    	}

     
	public double getEmptyRatio() {
		return emptyRatio;
	}
	
	public void setEmptyRatio(double emptyRatio) {
		this.emptyRatio = emptyRatio;
	}
	
	public double getRatio() {
		return ratio;
	}
	
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public double getIdealRatio() {
		return idealRatio;
	}

	public void setIdealRatio(double idealRatio) {
		this.idealRatio = idealRatio;
	}

    public Collection<GridPosition> getListOfRed () {
        return listOfRed;
    }

    public Collection<GridPosition> getListOfBlue () {
        return listOfBlue;
    }

    public Collection<GridPosition> getListOfEmpty () {
        return listOfEmpty;
    }
    
   
}
