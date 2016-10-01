package xml;

import java.util.Objects;

import org.w3c.dom.Element;

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

	public Parameters returnBasicParameters(){
		// throw error/exception here
		if(!isValidFile()){
			//return null;
		}
		String title = getTextValue("title");
		String author = getTextValue("author");
		String numRows = getTextValue("numRows");
		String numCols = getTextValue("numCols");
		return new Parameters(title, author, numRows, numCols);
	}

	public abstract Parameters getSimulationParameters();

	public String getSimulationType(){
	    return this.simulationType;
	}
}
