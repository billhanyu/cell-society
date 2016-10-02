package xml;

import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Sugarscape.SugarParameters;
import cell.GridPosition;
import grid.Parameters;

public class SugarSimulationFactory extends SimulationFactory {
	
	public SugarSimulationFactory(Element rootElement) {
		super(rootElement);
	}

	public SugarSimulationFactory(Element rootElement, String simType) {
		super(rootElement, simType);
	}

	@Override
	public Parameters createParameters(Parameters basicParams, NodeList listOfNodes) {
		Collection<GridPosition> oneCell = createListOfLocations("one", listOfNodes);
        Collection<GridPosition> twoCell = createListOfLocations("two", listOfNodes);
        Collection<GridPosition> threeCell = createListOfLocations("three", listOfNodes);
        Collection<GridPosition> fourCell = createListOfLocations("full", listOfNodes);
		return new SugarParameters(basicParams, oneCell, twoCell, threeCell, fourCell);
	}

}
