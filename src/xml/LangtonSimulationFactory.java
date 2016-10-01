package xml;

import org.w3c.dom.Element;

import grid.Parameters;

public class LangtonSimulationFactory extends SimulationFactory {

	public LangtonSimulationFactory(Element rootElement) {
		super(rootElement);
	}

	@Override
	public Parameters getSimulationParameters() {
		return null;
	}

}
