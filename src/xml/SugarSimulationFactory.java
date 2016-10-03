package xml;

import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Sugarscape.SugarParameters;
import cell.GridPosition;
import grid.Parameters;

/**
 * 
 * @author Brian
 * Generate Sugar parameters based on XML File of Simulation Type "Sugarscape". Cannot generate
 * location-specific simulation currently, but reads general parameters/conditions from XML
 * and returns an instance of SugarParameters
 */
public class SugarSimulationFactory extends SimulationFactory {
	
	public SugarSimulationFactory(Element rootElement) {
		super(rootElement);
	}

	@Override
	public Parameters createParameters(Parameters basicParams, NodeList listOfNodes) {
	    Collection<GridPosition> oneCell = createListOfLocations("one", listOfNodes);
	    Collection<GridPosition> twoCell = createListOfLocations("two", listOfNodes);
	    Collection<GridPosition> threeCell = createListOfLocations("three", listOfNodes);
	    Collection<GridPosition> fourCell = createListOfLocations("full", listOfNodes);
	    double ratio = getDoubleValue("agentRatio");
	    return new SugarParameters(basicParams, oneCell, twoCell, threeCell, fourCell, ratio);
	}

}
