package xml;

import grid.Builder;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Objects;

public abstract class SimulationFactory {
    
    private final Element rootElement;
    
    public SimulationFactory(Element rootElement) {
        this.rootElement = rootElement;
    }
    
    public boolean isValidFile() {
        return Objects.equals(getAttribute("SimulationType"), getSimulationType());
    }
    
    protected String getAttribute (String attributeName) {
        return rootElement.getAttribute(attributeName);
    }
    
    protected String getTextValue (String tagName) {
        String textValue = null;
        NodeList nodeList = rootElement.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0){
            textValue = nodeList.item(0).getTextContent();
        }
        return textValue;
    }
    
    
    public abstract Builder getSimulation();
    
    
    public abstract String getSimulationType();
}
