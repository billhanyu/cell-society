package xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import gameOfLife.GLParameters;
import grid.Parameters;
import schelling.SLParameters;

public class GameOfLifeSimulationFactory extends SimulationFactory {
    
    public GameOfLifeSimulationFactory(Element rootElement) {
        super(rootElement);
    }

    @Override
    public String getSimulationType () {
        return "GameOfLife";
    }

    @Override
    public GLParameters getSimulationParameters () {
        Parameters basicParams = returnBasicParameters();
        
        NodeList listTagNames = rootElement.getElementsByTagName("*");
        
        
        String aliveRatio = getTextValue("aliveRatio");
        return new GLParameters(basicParams, aliveRatio);
        
    }

}


