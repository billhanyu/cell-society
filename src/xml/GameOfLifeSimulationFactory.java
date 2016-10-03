package xml;

import java.util.Collection;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import cell.GridPosition;
import gameOfLife.GLParameters;
import grid.Parameters;

/**
 * 
 * @author Brian
 * Generate GameOfLife parameters based on XML File of Simulation Type "GameOfLife". Depending on format of
 * XML file, can either randomly determine which cells are alive based on a ratio or set each cell to a 
 * predefined state; returns an instance of GLParameters
 */
public class GameOfLifeSimulationFactory extends SimulationFactory {
    
    
    public GameOfLifeSimulationFactory(Element rootElement) {
        super(rootElement);
    }


    @Override
    public Parameters createParameters (Parameters basicParams, NodeList listOfNodes) {
        Collection<GridPosition> listOfAlive = createListOfLocations("ALIVE", listOfNodes);
        GLParameters param;
        if (listOfAlive.size() > 0){
            param = new GLParameters(basicParams, listOfAlive);
            param.setByLocations(true);
        }
        else{
            double ratioOfAlive = getDoubleValue("ratioOfAlive");
            param = new GLParameters(basicParams, ratioOfAlive);
        }
        return param;
    }

}


