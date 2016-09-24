package xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import SpreadingFire.SFParameters;

public class SpreadingFireSimulationFactory extends SimulationFactory {
    
    public SpreadingFireSimulationFactory(Element rootElement) {
        super(rootElement);
    }

    @Override
    public SFParameters getSimulationParameters(){
        if(!isValidFile()){
            System.out.println("hello");
        }
        NodeList listTagNames = rootElement.getElementsByTagName("*");
        
        String title = getTextValue("title");
        String author = getTextValue("author");
        String probCatch = getTextValue("probCatch");
        String numRows = getTextValue("numRows");
        String numCols = getTextValue("numCols");
        for (int i = 0; i < listTagNames.getLength(); i++){
            if (listTagNames.item(i).getNodeName().equals("FireCell")){
                String startingCell = getTextValue("FireCell");
                return new SFParameters(title, author, numRows, numCols, probCatch, startingCell);
            }
        }
        
        return new SFParameters(title, author, numRows, numCols, probCatch);
    }
    
    @Override
    public String getSimulationType () {
        // TODO Auto-generated method stub
        return "Spreading Fire Simulation";
    }
}
