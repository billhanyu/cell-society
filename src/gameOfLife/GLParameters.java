package gameOfLife;

import java.util.Collection;
import cell.GridPosition;
import grid.Parameters;

public class GLParameters extends Parameters {
    
    private double ratioOfAlive;
    private Collection<GridPosition> listOfAlive;

    public GLParameters(Parameters p, double ratioOfAlive){
        super(p);
        this.ratioOfAlive = ratioOfAlive;
        setByLocations(false);
    }
    
    public GLParameters(Parameters p, Collection<GridPosition> listOfAlive){
        super(p);
        this.listOfAlive = listOfAlive;
        setByLocations(true);
    }

    
    public double getRatioOfAlive () {
        return ratioOfAlive;
    }
    
    public Collection<GridPosition> getListOfAlive(){
        return listOfAlive;
    }

}
