package xml.model;

import javax.xml.parsers.ParserConfigurationException;
import grid.Parameters;
import grid.Runner;

public class GameOfLifeXMLFileSaver extends XMLSaveFile{

    public GameOfLifeXMLFileSaver () throws ParserConfigurationException {
        super();
    }

    @Override
    public void createSimulationXML (Parameters params, Runner runner) {
        createListOfElements(runner);
    }

}
