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
        Collection<GridPosition> listOfAlive = createListOfLocations("Alive", listOfNodes);
        if (listOfAlive.size() > 0){
            return new GLParameters(basicParams, listOfAlive);
        }
        else{
            double ratioOfAlive = getDoubleValue("ratioOfAlive");
            return new GLParameters(basicParams, ratioOfAlive);
        }
    }

}


