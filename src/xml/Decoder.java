package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ui.ErrorPop;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Handles parsing XML files by returning the root elements.
 *
 * @author Rhondu Smithwick
 * @author Brian (Small Addition)
 */
public class Decoder {

    private final DocumentBuilder documentBuilder = getDocumentBuilder();
    private static ResourceBundle myResources;
    
    public Decoder (ResourceBundle myResources){
        Decoder.myResources = myResources;
    }
    /**
     * A helper method to get a documentBuilder.
     * <p>
     * We keep only one documentBuilder around because they're expensive to make
     * and we can just reset it before every xml Parse.
     * </p>
     *
     * @return a new documentBuilder
     */
    private static DocumentBuilder getDocumentBuilder () {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            ErrorPop error = new ErrorPop(300, 200, myResources.getString("ErrorMessage"), myResources);
            error.popup();
        }
        return documentBuilder;
    }

    /**
     * Gets the root element in an XML file.
     *
     * @param xmlFilename the location of the xmlFile
     * @return the root element in the xmlFile
     */
    public Element getRootElement (String xmlFilename) {
        documentBuilder.reset();
        Element xmlElement = null;
        try {
            Document xmlDocument = documentBuilder.parse(xmlFilename);
            xmlElement = xmlDocument.getDocumentElement();
        } catch (SAXException
                | IOException e) {
            ErrorPop error = new ErrorPop(300, 200, myResources.getString("ErrorMessage"), myResources);
            error.popup();
        }
        return xmlElement;
    }
}

