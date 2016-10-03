package xml.model;

import javax.xml.parsers.ParserConfigurationException;
import grid.Parameters;
import grid.Runner;
import schelling.SLParameters;

/**
 * 
 * @author Brian
 * Create Segregation XML File detailing important ratios and the current state of each cell
 * within the running simulation
 */
public class SchellingXMLFileSaver extends XMLSaveFile{

    public SchellingXMLFileSaver () throws ParserConfigurationException {
        super();
    }

    @Override
    public void createSimulationXML (Parameters params, Runner runner) {
        createAndAppendElement("redBlueRatio", ((SLParameters) params).getRatio());
        createAndAppendElement("idealRatio", ((SLParameters) params).getIdealRatio());
        createAndAppendElement("emptyRatio", ((SLParameters) params).getEmptyRatio());
        createListOfSimpleElements(runner);
    }

}
