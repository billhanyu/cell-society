package xml.model;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
        this.rootElement = this.doc.createElement("Simulation");
        this.rootElement.setAttribute("SimulationType", "Schelling");
        this.doc.appendChild(rootElement);
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
