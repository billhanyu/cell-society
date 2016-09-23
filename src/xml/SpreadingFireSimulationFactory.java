package xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import grid.Builder;
import xml.model.SimulationParameters;
import xml.model.SpreadingFireModel;

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
    public SpreadingFireModel getSimulationParameters(){
        if(!isValidFile()){
            System.out.println("hello");
        }
        //NodeList listTagNames = rootElement.getElementsByTagName("*");
        //System.out.println(listTagNames.item(2));
        String title = getTextValue("title");
        String size = getTextValue("size");
        String author = getTextValue("author");
        String probCatch = getTextValue("probCatch");
        String numRows = getTextValue("numRows");
        String numCols = getTextValue("numCols");
        return new SpreadingFireModel(title, author, size, numRows, numCols, probCatch);
    }
    
    @Override
    public String getSimulationType () {
        // TODO Auto-generated method stub
        return "Spreading Fire Simulation";
    }
}
