package xml.model;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class XMLFileGenerator {
    
    
    protected Element rootElement;
    protected Document doc;
    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    
    public XMLFileGenerator() throws ParserConfigurationException{
        this.docFactory = DocumentBuilderFactory.newInstance();
        this.docBuilder = docFactory.newDocumentBuilder();
        this.doc = docBuilder.newDocument();
    }
    
    public void setRootElement(String simType){
        this.rootElement = this.doc.createElement("Simulation");
        this.rootElement.setAttribute("SimulationType", simType);
        this.doc.appendChild(rootElement);
    }

    
    public void createAndAppendElement(String element, Object text){
        Element elem = doc.createElement(element);
        elem.appendChild(doc.createTextNode(text.toString()));
        this.rootElement.appendChild(elem);
    }
    
    public void writeFile(String dataPath) throws TransformerException{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        String filePath = System.getProperty("java.class.path");
        int index = filePath.indexOf('8');
        String copyFile = new String(filePath.substring(0, index+1));
        String correctCopy = new String(copyFile.replace('\\', '/'));
        String finalFilePath = correctCopy + dataPath;
        StreamResult result = new StreamResult(new File(finalFilePath));
        transformer.transform(source, result);
        System.out.println(finalFilePath);
    }
    
    public void createElementsAtLocations(String element, int[] row, int [] col){
        if (row.length != col.length){
            return;
        }
        for (int i = 0; i < row.length; i++){
            createAndAppendElement(element, RCString(row[i], col[i]));
        }
    }
    
    public String RCString(int row, int col){
        return row + " " + col;
    }
    
    public abstract void createFile() throws ParserConfigurationException, TransformerException;
}
