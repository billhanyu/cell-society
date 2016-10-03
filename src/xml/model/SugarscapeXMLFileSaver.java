package xml.model;

import javax.xml.parsers.ParserConfigurationException;
import Sugarscape.SugarParameters;
import cell.Cell;
import grid.Parameters;
import grid.Runner;

/**
 * 
 * @author Brian
 * Save general simulation parameters (not currently able to save all current cell state information)
 * to a SugarScape XML file. 
 */
public class SugarscapeXMLFileSaver extends XMLSaveFile{
    
    public SugarscapeXMLFileSaver () throws ParserConfigurationException {
        super();
    }

    @Override
    public void createSimulationXML (Parameters params, Runner runner) {
        createAndAppendElement("agentRatio", ((SugarParameters) params).getAgentRatio());
        for (Cell cell : runner.getCells()){
            if (cell.getCurrState().getStateName().equals("1/4 QUARTER FULL")){
                createAndAppendElement("one", cell.getGridPosition().toString());
            }
            else if (cell.getCurrState().getStateName().equals("2/4 HALF FULL")){
                createAndAppendElement("two", cell.getGridPosition().toString());
            }
            else if (cell.getCurrState().getStateName().equals("3/4 FULL")){
                createAndAppendElement("three", cell.getGridPosition().toString());
            }
            else if (cell.getCurrState().getStateName().equals("4/4 FULL")){
                createAndAppendElement("full", cell.getGridPosition().toString());
            }
        }
        
    }

    
}
