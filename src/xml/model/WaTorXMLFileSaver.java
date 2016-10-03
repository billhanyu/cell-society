package xml.model;

import javax.xml.parsers.ParserConfigurationException;
import WaTor.WTParameters;
import grid.Parameters;
import grid.Runner;

/**
 * 
 * @author Brian
 * Save general simulation parameters (not currently able to save all current cell state parameters)
 * to a WaTor XML file. 
 */
public class WaTorXMLFileSaver extends XMLSaveFile{

    public WaTorXMLFileSaver () throws ParserConfigurationException {
        super();
    }

    @Override
    public void createSimulationXML (Parameters params, Runner runner) {
        createAndAppendElement("sharkStarve", ((WTParameters) params).getSharkStarve());
        createAndAppendElement("fishRate", ((WTParameters) params).getFishRate());
        createAndAppendElement("sharkRate", ((WTParameters) params).getSharkRate());
        createAndAppendElement("energyFromEating", ((WTParameters) params).getEnergyFromEating());
        createAndAppendElement("emptyRatio", ((WTParameters) params).getEmptyRatio());
        createAndAppendElement("ratio", ((WTParameters) params).getRatio());
    }

}
