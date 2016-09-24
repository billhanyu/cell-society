package xml;
import org.w3c.dom.Element;
import schelling.SLParameters;
public class SchellingSimulationFactory extends SimulationFactory {
    
    public SchellingSimulationFactory(Element rootElement) {
        super(rootElement);
    }

    @Override
    public String getSimulationType () {
        // TODO Auto-generated method stub
        return "SchellingSimulation";
    }

    @Override
    public SLParameters getSimulationParameters () {
        // TODO Auto-generated method stub
        return null;
    }

}
