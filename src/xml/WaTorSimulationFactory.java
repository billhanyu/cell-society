package xml;

import org.w3c.dom.Element;
import grid.Parameters;

public class WaTorSimulationFactory extends SimulationFactory{
    
    public WaTorSimulationFactory(Element rootElement) {
        super(rootElement);
    }

    @Override
    public Parameters getSimulationParameters () {
        Parameters basicParams = returnBasicParameters();
        return basicParams;
    }

    @Override
    public String getSimulationType () {
        return "WaTor";
    }

}
