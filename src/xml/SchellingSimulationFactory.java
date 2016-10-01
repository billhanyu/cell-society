package xml;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
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
        return new SLParameters(basicParams, idealRatio, redBlueRatio, emptyRatio);
    }

    

}
