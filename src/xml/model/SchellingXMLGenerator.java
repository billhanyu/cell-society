package xml.model;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class SchellingXMLGenerator extends XMLFileGenerator{

	private int rows = 12;
	private int cols = 12;
	private double redBlue = 1;
	private double ideal = .5;
	private double empty = .3;

	public SchellingXMLGenerator() throws ParserConfigurationException {
		super();
	}

	public void createFile() throws ParserConfigurationException, TransformerException{
		setRootElement("Schelling");
		createAndAppendElement("title", "Schelling Simulation");
		createAndAppendElement("author", "Bill");
		createAndAppendElement("numRows", rows);
		createAndAppendElement("numCols", cols);
		createAndAppendElement("redBlueRatio", redBlue);
		createAndAppendElement("idealRatio", ideal);
		createAndAppendElement("emptyRatio", empty);

		createAndAppendElement("red", "5 5");
		createAndAppendElement("red", "6 6");

		writeFile("/data/xml/SchellingTests.xml");
	}
}
