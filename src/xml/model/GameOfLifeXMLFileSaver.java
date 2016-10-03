package xml.model;

import javax.xml.parsers.ParserConfigurationException;
import grid.Parameters;
import grid.Runner;

/**
 * 
 * @author Brian
 * Create Game Of Life XML File detailing the current state of each cell
 * within the running simulation
 */
public class GameOfLifeXMLFileSaver extends XMLSaveFile{

    public GameOfLifeXMLFileSaver () throws ParserConfigurationException {
        super();
    }

    @Override
    public void createSimulationXML (Parameters params, Runner runner) {
        createListOfSimpleElements(runner);
    }

}
