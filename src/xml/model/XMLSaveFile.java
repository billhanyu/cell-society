package xml.model;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import grid.Parameters;
import grid.Runner;

public class XMLSaveFile {
    
        
    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private Document doc;

    public XMLSaveFile(Parameters params, Runner runner, String simType) throws ParserConfigurationException{
        this.docFactory = DocumentBuilderFactory.newInstance();
        this.docBuilder = docFactory.newDocumentBuilder();
        this.doc = docBuilder.newDocument();
    }
}
