package xml;

import java.util.Collection;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import SpreadingFire.SFParameters;
import cell.GridPosition;
import grid.Parameters;

/**
 * 
 * @author Brian
 * Generate Fire parameters based on XML File of Simulation Type "Fire". Depending on format of
 * XML file, can return Cell-specific parameters or more general, randomizing parameters in an
 * instance of SFParameters
 */
public class SpreadingFireSimulationFactory extends SimulationFactory {
    
    public SpreadingFireSimulationFactory(Element rootElement) {
        super(rootElement);
    }
    
    @Override
    public Parameters createParameters (Parameters basicParams, NodeList listOfNodes) {
        double probCatch = getDoubleValue("probCatch");
        Collection<GridPosition> listOfFireCells = createListOfLocations("BURNING", listOfNodes);
        Collection<GridPosition> listOfEmptyCells = createListOfLocations("EMPTY", listOfNodes);
        if (listOfFireCells.size() > 0){
            return new SFParameters(basicParams, probCatch, listOfFireCells, listOfEmptyCells);
        }
        else {
            return new SFParameters(basicParams, probCatch);
        }
    }
    
    
    
}
