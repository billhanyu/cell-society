package xml;

import org.w3c.dom.Element;
import grid.Builder;

public class SpreadingFireSimulationFactory extends SimulationFactory {
    
    public SpreadingFireSimulationFactory(Element rootElement) {
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
        return "SpreadingFireSimulation";
    }
}
