package xml.model;

import javax.xml.parsers.ParserConfigurationException;
import SpreadingFire.SFParameters;
import grid.Parameters;
import grid.Runner;

public class SpreadingFireXMLFileSaver extends XMLSaveFile {

    public SpreadingFireXMLFileSaver () throws ParserConfigurationException {
        super();
    }

    @Override
    public void createSimulationXML (Parameters params, Runner runner) {
        createAndAppendElement("probCatch", ((SFParameters) params).getProbCatch());
        createListOfElements(runner);
    }

}
