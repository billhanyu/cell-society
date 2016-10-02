package xml.model;

import javax.xml.parsers.ParserConfigurationException;
import grid.Parameters;
import grid.Runner;
import schelling.SLParameters;

public class SchellingXMLFileSaver extends XMLSaveFile{

    public SchellingXMLFileSaver () throws ParserConfigurationException {
        super();
    }

    @Override
    public void createSimulationXML (Parameters params, Runner runner) {
        createAndAppendElement("redBlueRatio", ((SLParameters) params).getRatio());
        createAndAppendElement("idealRatio", ((SLParameters) params).getIdealRatio());
        createAndAppendElement("emptyRatio", ((SLParameters) params).getEmptyRatio());
        createListOfElements(runner);
    }

}
