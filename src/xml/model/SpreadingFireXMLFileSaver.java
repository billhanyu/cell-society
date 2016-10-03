package xml.model;

import javax.xml.parsers.ParserConfigurationException;
import SpreadingFire.SFParameters;
import grid.Parameters;
import grid.Runner;

/**
 * 
 * @author Brian
 * Create Spreading Fire XML File detailing probability of catching fire and the current state of each cell
 * within the running simulation
 */
public class SpreadingFireXMLFileSaver extends XMLSaveFile {

    public SpreadingFireXMLFileSaver () throws ParserConfigurationException {
        super();
    }

    @Override
    public void createSimulationXML (Parameters params, Runner runner) {
        createAndAppendElement("probCatch", ((SFParameters) params).getProbCatch());
        createListOfSimpleElements(runner);
    }

}
