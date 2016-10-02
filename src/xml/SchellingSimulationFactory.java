package xml;
import java.util.Collection;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import cell.GridPosition;
import grid.Parameters;
import schelling.SLParameters;
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
        return new SLParameters(basicParams, idealRatio, redBlueRatio, emptyRatio);
    }

    

}
