package xml.model;

import javax.xml.parsers.ParserConfigurationException;
import ants.AntParameters;
import grid.Parameters;
import grid.Runner;

/**
 * 
 * @author Brian
 * Create Ant XML File detailing important parameters (not currently able to support exact parameters
 * for each cell in the running simulation)
 */
public class AntXMLFileSaver extends XMLSaveFile{

    public AntXMLFileSaver () throws ParserConfigurationException {
        super();
    }

    @Override
    public void createSimulationXML (Parameters params, Runner runner) {
        createAndAppendElement("maxNumAnts", ((AntParameters) params).getMaxNumAnts());
        createAndAppendElement("maxAmountOfPheromone", ((AntParameters) params).getMaxAmountOfPheromone());
        createAndAppendElement("antsBornPerTimeStep", ((AntParameters) params).getAntsBornPerTimeStep());
        createAndAppendElement("maxAntAge", ((AntParameters) params).getMaxAntAge());
        createAndAppendElement("evaporationRatio", ((AntParameters) params).getEvaporationRatio());
        createAndAppendElement("diffusionRatio", ((AntParameters) params).getDiffusionRatio());
        createAndAppendElement("K", ((AntParameters) params).getK());
        createAndAppendElement("N", ((AntParameters) params).getN());
        createAndAppendElement("nestCell", ((AntParameters) params).getListOfNest().toArray()[0]);
        createAndAppendElement("foodCell", ((AntParameters) params).getListOfFood().toArray()[0]);
        createAndAppendElement("obstacleCell", ((AntParameters) params).getListOfObstacle().toArray()[0]);
        
    }

}
