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
    
    public Parameters returnBasicParameters() {
        // throw error/exception here
        if(!isValidFile()){
            return null;
        }
        String title = getTextValue("title");
        String author = getTextValue("author");
        String numRows = getTextValue("numRows");
        String numCols = getTextValue("numCols");
        return new Parameters(title, author, numRows, numCols);
    }
    
    public abstract Parameters getSimulationParameters();
    
    public abstract String getSimulationType();
}
