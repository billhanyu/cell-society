package xml;

import java.util.ArrayList;
import java.util.Collection;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import cell.GridPosition;
import grid.Parameters;

/**
 * 
 * @author Brian
 * Abstract class contains many methods needed by each specific simulation's XML Factory
 * Tests to make sure basic parameters are instantiated; if they are not, fills in default values
 */
public abstract class SimulationFactory extends XMLFactory {
    
        public SimulationFactory(Element rootElement) {
		super(rootElement);
	}

	/**
	 * Return the type of the current simulation based on the loaded XML file
	 * @return Simulation Type
	 */
	public String getSimulationName(){
		return (getAttribute("SimulationType"));
	}

	/**
	 * Generate the basic parameters consistent among all simulations: title, author,
	 * number of rows, number of columns, and the shape of each cell
	 * Fills in default values if these are not specified in the XML file
	 * @return Parameters containing basic information about the Simulation
	 */
	public Parameters getBasicParameters() {
		String title = getTextValue("title");
		String author = getTextValue("author");
		int numRows = getIntegerValue("numRows");
		int numCols = getIntegerValue("numCols");
		String shape = getTextValue("shape");
		return new Parameters(title, author, numRows, numCols, shape);
	}
	
	/**
	 * Find all of the cells of state 'type' and add them to a list detailing the Cell's specified GridPosition
	 * @param type
	 * @param listOfNodes
	 * @return Collection of grid positions for a specific type of cell state
	 */
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

	/**
	 * Creates basic parameters and appends these basic parameters to the more detailed, 
	 * simulation-specific parameters generated in createParameters(Parameters, NodeList)
	 * @return Simulation-specific set of Parameters
	 */
	public Parameters getSimulationParameters() {
	    Parameters basicParameters = getBasicParameters();
	    NodeList listOfNodes = rootElement.getChildNodes();
	    Parameters parameters = createParameters(basicParameters, listOfNodes);
	    return parameters;
	}
	
	/**
	 * Convert XML Value from a string to an integer
	 * @param tagName
	 * @return integer element value
	 */
	public int getIntegerValue(String tagName){
	    return Integer.parseInt(getTextValue(tagName));
	}
	
	/**
	 * Convert XML Value from a string to a double
	 * @param tagName
	 * @return double element value
	 */
	public double getDoubleValue(String tagName){
	    return Double.parseDouble(getTextValue(tagName));
	}
	
	/**
	 * Create specific simulation parameters based on the given XML file. Varies depending on simulation
	 * class, hence it is an abstract method
	 * @param basicParams
	 * @param listOfNodes
	 * @return Simulation-specific parameters
	 */
	public abstract Parameters createParameters(Parameters basicParams, NodeList listOfNodes);

}
