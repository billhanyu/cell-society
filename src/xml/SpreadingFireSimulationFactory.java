package xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import SpreadingFire.SFParameters;
import grid.Parameters;

public class SpreadingFireSimulationFactory extends SimulationFactory {
    
    public SpreadingFireSimulationFactory(Element rootElement) {
        super(rootElement);
    }

    @Override
    public SFParameters getSimulationParameters(){
        Parameters basicParams = returnBasicParameters();
        NodeList listTagNames = rootElement.getElementsByTagName("*");
        String probCatch = getTextValue("probCatch");
        for (int i = 0; i < listTagNames.getLength(); i++){
            if (listTagNames.item(i).getNodeName().equals("FireCell")){
                String startingCell = getTextValue("FireCell");
                return new SFParameters(basicParams, probCatch, startingCell);
            }
        }
        
        return new SFParameters(basicParams, probCatch);
    }
    
    @Override
    public String getSimulationType () {
        // TODO Auto-generated method stub
        return "Spreading Fire";
    }
}
