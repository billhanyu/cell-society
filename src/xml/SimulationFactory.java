package xml;

import grid.Builder;
import xml.model.SimulationParameters;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Objects;

public abstract class SimulationFactory extends XMLFactory {
  
    public SimulationFactory(Element rootElement) {
        super(rootElement);
    }
    
    public boolean isValidFile () {
        return Objects.equals(getAttribute("SimulationType"), getSimulationType());
    }
    
    public abstract SimulationParameters getSimulationParameters();
    
    public abstract Builder getSimulation();
    
    
    public abstract String getSimulationType();
}
