package xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import grid.Parameters;

public class LangtonSimulationFactory extends SimulationFactory {

	public LangtonSimulationFactory(Element rootElement) {
		super(rootElement);
	}

	@Override
	public Parameters getSimulationParameters() {
		return null;
	}

	@Override
	public Parameters createParameters(Parameters basicParams, NodeList listOfNodes) {
		// TODO Auto-generated method stub
		return null;
	}

}
