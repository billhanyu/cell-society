package xml;

import grid.Builder;
import xml.model.SimulationParameters;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Objects;
import grid.Parameters;
public abstract class SimulationFactory extends XMLFactory {
  
    public SimulationFactory(Element rootElement) {
        super(rootElement);
    }
    
    public boolean isValidFile () {
        return Objects.equals(getAttribute("SimulationType"), getSimulationType());
    }
    
    public abstract Parameters getSimulationParameters();
    
    public abstract String getSimulationType();
}
