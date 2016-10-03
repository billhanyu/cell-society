package xml;

import java.util.Collection;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import cell.GridPosition;
import gameOfLife.GLParameters;
import grid.Parameters;

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
            param.setByLocations(false);
        }
        return param;
    }

}


