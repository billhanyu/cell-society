package xml;
import org.w3c.dom.Element;
import grid.Builder;

public class SchellingSimulationFactory extends SimulationFactory {
    
    public SchellingSimulationFactory(Element rootElement) {
        super(rootElement);
    }

    @Override
    public Builder getSimulation () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSimulationType () {
        // TODO Auto-generated method stub
        return "SchellingSimulation";
    }

}
