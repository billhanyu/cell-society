package xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
		return new Parameters(basicParams);
	}

}
