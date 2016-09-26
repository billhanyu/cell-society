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
    private int rows = 12;
    private int cols = 12;
    private double redBlue = 1;
    private double ideal = .5;
    private double empty = .3;
    
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
        createAndAppendElement("numRows", rows);
        createAndAppendElement("numCols", cols);
        createAndAppendElement("redBlueRatio", redBlue);
        createAndAppendElement("idealRatio", ideal);
        createAndAppendElement("emptyRatio", empty);
        
        createAndAppendElement("red", "5 5");
        createAndAppendElement("red", "6 6");
        
        writeFile();
    }
    
    private void createAndAppendElement(String element, Object text){
        Element elem = doc.createElement(element);
        elem.appendChild(doc.createTextNode(text.toString()));
        rootElement.appendChild(elem);
    }
    
    private void writeFile() throws TransformerException{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        String filePath = System.getProperty("java.class.path");
        int index = filePath.indexOf('8');
        String copyFile = new String(filePath.substring(0, index+1));
        String correctCopy = new String(copyFile.replace('\\', '/'));
        String finalFilePath = correctCopy + "/data/xml/Schelling2.xml";
        StreamResult result = new StreamResult(new File(finalFilePath));
        transformer.transform(source, result);
        System.out.println(finalFilePath);
    }
    
}
