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

public class SchellingXMLGenerator {
    
    private Element rootElement;
    private Document doc;
    
    public SchellingXMLGenerator() {
        
    }
        
    public void createFile() throws ParserConfigurationException, TransformerException{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.newDocument();
        rootElement = doc.createElement("Simulation");
        rootElement.setAttribute("SimulationType", "Schelling");
        doc.appendChild(rootElement);
        
        createAndAppendElement("title", "Schelling Simulation");
        createAndAppendElement("author", "Bill");
        createAndAppendElement("numRows", 12);
        createAndAppendElement("numCols", 12);
        createAndAppendElement("redBlueRatio", 2);
        createAndAppendElement("idealRatio", .4);
        createAndAppendElement("emptyRatio", .5);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("/Users/Brian/Documents/Duke/JUNIOR_YEAR_2016_2017/workspace/cellsociety_team18/data/xml/SchellingExpanded.xml"));
        transformer.transform(source, result);
        System.out.println("File saved!");
    }
    
    private void createAndAppendElement(String element, Object text){
        Element elem = doc.createElement(element);
        elem.appendChild(doc.createTextNode(text.toString()));
        rootElement.appendChild(elem);
    }
    
}
