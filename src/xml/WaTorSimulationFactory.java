package xml;

import java.util.Collection;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import WaTor.WTParameters;
import cell.GridPosition;
import grid.Parameters;

public class WaTorSimulationFactory extends SimulationFactory{
    
    public WaTorSimulationFactory(Element rootElement) {
        super(rootElement);
    }    
    
    @Override
    public Parameters createParameters (Parameters basicParams, NodeList listOfNodes) {
        int sharkRate = getIntegerValue("sharkRate");
        int fishRate = getIntegerValue("fishRate");
        int sharkStarve = getIntegerValue("sharkStarve");
        int energyFromEating = getIntegerValue("energyFromEating");
        Collection<GridPosition> sharkLocations = createListOfLocations("Shark", listOfNodes);
        Collection<GridPosition> fishLocations = createListOfLocations("Fish", listOfNodes);
        Collection<GridPosition> emptyLocations = createListOfLocations("empty", listOfNodes);
        
        
        
        double emptyRatio = getDoubleValue("emptyRatio");
        double ratio = getDoubleValue("ratio");
        return new WTParameters(basicParams, sharkRate, fishRate, emptyRatio, ratio, sharkStarve, energyFromEating);
    }


    @Override
    public String getSimulationType () {
        return "Predator-Prey";
    }

}
