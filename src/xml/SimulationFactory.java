package xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cell.GridPosition;
import grid.Parameters;

public abstract class SimulationFactory extends XMLFactory {
    
        private String simulationType;

	public SimulationFactory(Element rootElement) {
		super(rootElement);
	}
	
	public SimulationFactory(Element rootElement, String simType) {
	    super(rootElement);
	    this.simulationType = simType;
	}

	public String getSimulationName(){
		return (getAttribute("SimulationType"));
	}

	public boolean isValidFile () {
		return Objects.equals(getAttribute("SimulationType"), getSimulationType());
	}

	public Parameters getBasicParameters() {
		// throw error/exception here
		if(!isValidFile()){
			
		}
		String title = getTextValue("title");
		String author = getTextValue("author");
		int numRows = getIntegerValue("numRows");
		int numCols = getIntegerValue("numCols");
		String shape = getTextValue("shape");
		return new Parameters(title, author, numRows, numCols, shape);
	}
	
	public Collection<GridPosition> createListOfLocations(String type, NodeList listOfNodes){
	    Collection<GridPosition> listOfCells = new ArrayList<GridPosition>();
	    for (int i = 0; i < listOfNodes.getLength(); i++){
	            if (listOfNodes.item(i).getNodeName().equals(type)){
	                String positionOfCells = new String(listOfNodes.item(i).getTextContent());
	                GridPosition cellPosition = new GridPosition(Integer.parseInt(positionOfCells.split(" ")[0]),
	                                                             Integer.parseInt(positionOfCells.split(" ")[1]));;
	                                                             
	                listOfCells.add(cellPosition);
	            }
	    }
	    return listOfCells;
	}

	public Parameters getSimulationParameters() {
	    Parameters basicParameters = getBasicParameters();
	    NodeList listOfNodes = rootElement.getChildNodes();
	    Parameters parameters = createParameters(basicParameters, listOfNodes);
	    return parameters;
	}
	
	public int getIntegerValue(String tagName){
	    return Integer.parseInt(getTextValue(tagName));
	}
	
	public double getDoubleValue(String tagName){
	    return Double.parseDouble(getTextValue(tagName));
	}
	
	public abstract Parameters createParameters(Parameters basicParams, NodeList listOfNodes);

	public String getSimulationType(){
	    return this.simulationType;
	}
}
