package xml;

import java.util.Collection;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import SpreadingFire.SFParameters;
import cell.GridPosition;
import grid.Parameters;

public class SpreadingFireSimulationFactory extends SimulationFactory {
    
    public SpreadingFireSimulationFactory(Element rootElement) {
        super(rootElement);
    }
    



    @Override
    public Parameters createParameters (Parameters basicParams, NodeList listOfNodes) {
        Collection<GridPosition> listOfFireCells = createListOfLocations("FireCell", listOfNodes);
        if (listOfFireCells.size() > 0){
            return new SFParameters(basicParams, listOfFireCells);
        }
        else {
            double probCatch = getDoubleValue("probCatch");
            return new SFParameters(basicParams, probCatch);
        }
    }
    
    
    
}
