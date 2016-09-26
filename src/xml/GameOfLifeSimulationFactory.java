package xml;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import gameOfLife.GLParameters;
import grid.Parameters;

public class GameOfLifeSimulationFactory extends SimulationFactory {
    
    private List<String> listOfAlive;
    
    public GameOfLifeSimulationFactory(Element rootElement) {
        super(rootElement);
    }

    @Override
    public String getSimulationType () {
        return "GameOfLife";
    }

    @Override
    public GLParameters getSimulationParameters () {
        
        listOfAlive = new ArrayList<String>();
        Parameters basicParams = returnBasicParameters();
        NodeList hello = rootElement.getChildNodes();
        for (int i = 0; i < hello.getLength(); i++){
            if (hello.item(i).getNodeName().equals("Alive")){
                String alivePos = new String(hello.item(i).getTextContent());
                listOfAlive.add(alivePos);
            }
        }
        if (listOfAlive.size() > 1){
            return new GLParameters(basicParams, listOfAlive);
        }
        else{
            //String aliveRatio = getTextValue("aliveRatio");
            return new GLParameters(basicParams, ".4");
        }
        
    }

}


