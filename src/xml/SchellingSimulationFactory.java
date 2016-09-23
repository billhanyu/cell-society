package xml;
import org.w3c.dom.Element;
import grid.Builder;
import xml.model.SimulationParameters;

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

    @Override
    public SimulationParameters getSimulationParameters () {
        // TODO Auto-generated method stub
        return null;
    }

}
