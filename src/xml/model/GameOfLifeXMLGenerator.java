package xml.model;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class GameOfLifeXMLGenerator extends XMLFileGenerator {
    
    private static final String ELEMENT = "Alive";

    public GameOfLifeXMLGenerator () throws ParserConfigurationException {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void createFile () throws ParserConfigurationException, TransformerException {
        // TODO Auto-generated method stub
        setRootElement("GameOfLife");
        createBlock(ELEMENT, 3, 3);
        writeFile("/data/xml/GameOfLifePleaseWork2.xml");   
        
    }
        
    public void createBlock(String element, int row, int col){
        int[] rowArray = {row, row, row + 1, row + 1};
        int[] colArray = {col, col + 1, col, col + 1};
        createElementsAtLocations(element, rowArray, colArray);
    }
    
    public void createBehive(int row, int col){
        
    }
    
}
