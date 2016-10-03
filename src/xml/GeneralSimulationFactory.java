package xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import grid.Parameters;

/**
 * 
 * @author Brian
 * Used to determine initial simulation Type so that correct SimulationFactory can be initialized
 */
public class GeneralSimulationFactory extends SimulationFactory{

    public GeneralSimulationFactory (Element rootElement) {
        super(rootElement);
    }

    
    public String getTitle(){
        return getTextValue("title");
    }

    @Override
    public Parameters createParameters (Parameters basicParams, NodeList listOfNodes) {
        return basicParams;
    }

}
