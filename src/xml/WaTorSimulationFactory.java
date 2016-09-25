package xml;

import org.w3c.dom.Element;
import WaTor.WTParameters;
import grid.Parameters;

public class WaTorSimulationFactory extends SimulationFactory{
    
    public WaTorSimulationFactory(Element rootElement) {
        super(rootElement);
    }

    @Override
    public Parameters getSimulationParameters () {
        Parameters basicParams = returnBasicParameters();
        String sharkRate = getTextValue("sharkRate");
        String fishRate = getTextValue("fishRate");
        String sharkStarve = getTextValue("sharkStarve");
        String energyFromEating = getTextValue("energyFromEating");
        String emptyRatio = getTextValue("emptyRatio");
        String ratio = getTextValue("ratio");
        
        return new WTParameters(basicParams, sharkRate, fishRate, emptyRatio, ratio, sharkStarve, energyFromEating);
    }

    @Override
    public String getSimulationType () {
        return "WaTor";
    }

}
