package xml;
import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import cell.GridPosition;
import grid.Parameters;
import schelling.SLParameters;

/**
 * 
 * @author Brian
 * Generate Schelling parameters based on XML File of Simulation Type "Schelling". Depending on format
 * of the XML file, can either initialize cells randomly based on parameters or can specify exact condition
 * of each cell; returns an instance of SLParameters
 */
public class SchellingSimulationFactory extends SimulationFactory {
    
    public SchellingSimulationFactory(Element rootElement) {
        super(rootElement);
    }

    @Override
    public Parameters createParameters (Parameters basicParams, NodeList listOfNodes) {
        double idealRatio = getDoubleValue("idealRatio");
        double redBlueRatio = getDoubleValue("redBlueRatio");
        double emptyRatio = getDoubleValue("emptyRatio");
        Collection<GridPosition> listOfRed = createListOfLocations("X", listOfNodes);
        Collection<GridPosition> listOfBlue = createListOfLocations("O", listOfNodes);
        Collection<GridPosition> listOfEmpty = createListOfLocations("Vacant", listOfNodes);
        if (listOfRed.size() > 0 | listOfBlue.size() > 0 | listOfEmpty.size() > 0){
            return new SLParameters(basicParams, idealRatio, listOfRed, listOfBlue, listOfEmpty);
        }
        return new SLParameters(basicParams, idealRatio, redBlueRatio, emptyRatio);
    }

    

}
