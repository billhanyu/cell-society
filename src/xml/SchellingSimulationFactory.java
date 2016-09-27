package xml;
import org.w3c.dom.Element;

import grid.Parameters;
import schelling.SLParameters;
public class SchellingSimulationFactory extends SimulationFactory {
    
    public SchellingSimulationFactory(Element rootElement) {
        super(rootElement);
    }

    @Override
    public String getSimulationType () {
        return "Schelling";
    }

    @Override
    public SLParameters getSimulationParameters () {
        Parameters basicParams = returnBasicParameters();
        
        //NodeList listTagNames = rootElement.getElementsByTagName("*");
        String idealRatio = getTextValue("idealRatio");
        String redBlueRatio = getTextValue("redBlueRatio");
        String emptyRatio = getTextValue("emptyRatio");
        return new SLParameters(basicParams, idealRatio, redBlueRatio, emptyRatio);
        
    }

}
