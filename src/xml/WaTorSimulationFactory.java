package xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import WaTor.WTParameters;
import grid.Parameters;

/**
 * 
 * @author Brian
 * Generate WaTor parameters based on XML File of Simulation Type "WaTor". Cannot generate
 * location-specific simulation currently, but reads general parameters/conditions from XML
 * and returns an instance of WTParameters
 */
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
//        Collection<GridPosition> sharkLocations = createListOfLocations("Shark", listOfNodes);
//        Collection<GridPosition> fishLocations = createListOfLocations("Fish", listOfNodes);
        
        double emptyRatio = getDoubleValue("emptyRatio");
        double ratio = getDoubleValue("ratio");
        return new WTParameters(basicParams, sharkRate, fishRate, emptyRatio, ratio, sharkStarve, energyFromEating);
    }


}
