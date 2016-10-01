package xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import grid.Parameters;

public class GeneralSimulationFactory extends SimulationFactory{

    public GeneralSimulationFactory (Element rootElement) {
        super(rootElement);
    }

    
    public String getTitle(){
        return getTextValue("title");
    }

    @Override
    public String getSimulationType () {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Parameters createParameters (Parameters basicParams, NodeList listOfNodes) {
        return basicParams;
    }

}
