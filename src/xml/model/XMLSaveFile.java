package xml.model;

import java.io.File;
import java.util.ResourceBundle;
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
import cell.Cell;
import grid.Parameters;
import grid.Runner;
import ui.ErrorPop;
import xmlExceptions.InvalidXMLSaveException;

/***
 * 
 * @author Brian
 * Generates XML File based on current simulation parameters/conditions. Throws various XML exceptions depending
 * on the type of issue encountered while saving the files
 */
public abstract class XMLSaveFile {

    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private Document doc;
    private Element rootElement;
    
    /**
     * Creates new XML File Saver by creating a new DocumentBuilder and new Document
     * @throws ParserConfigurationException
     */
    public XMLSaveFile() throws ParserConfigurationException{
        this.docFactory = DocumentBuilderFactory.newInstance();
        this.docBuilder = docFactory.newDocumentBuilder();
        this.doc = docBuilder.newDocument();
    }

    /**
     * Using the current simulation's parameters, runner, and simType, save the current simulation
     * setup to the location chosen (fileLocation). Exceptions tested for include an InvalidXMLSaveException
     * and a TransformerException. Each of these stops the file saving process and creates a pop-up error window
     * 
     * @param params
     * @param runner
     * @param fileLocation
     * @param myResources
     * @param simType
     */
    public void initializeSaveFile(Parameters params, Runner runner, File fileLocation, 
                       ResourceBundle myResources, String simType){
        try {
            setRootElement(simType);
        }
        catch (InvalidXMLSaveException e) {
            ErrorPop fileSaveError = new ErrorPop(300, 200, myResources.getString("FileSaveError"), myResources);
            fileSaveError.popup();
            return;
        }
        saveBasicParameters(params);
        createSimulationXML(params, runner);
        try {
            writeFile(fileLocation);
        }
        catch (TransformerException ex) {
           ErrorPop fileWriteError = new ErrorPop(300, 200, myResources.getString("FileWriteError"), myResources);
           fileWriteError.popup();
           return;
        } 
    }
    
    private void saveBasicParameters(Parameters params){
        createAndAppendElement("title", params.getTitle());
        createAndAppendElement("author", params.getAuthor());
        createAndAppendElement("numRows", params.getRows());
        createAndAppendElement("numCols", params.getCols());
        createAndAppendElement("shape", params.getGraphicType().toString());
    }

    /**
     * Create an XML File with simType as the SimulationType attribute. If there is an error instantiating
     * the file (due to an incorrect save address or simType), an InvalidXMLSaveException is thrown
     * @param simType
     * @throws InvalidXMLSaveException
     */
    public void setRootElement(String simType) throws InvalidXMLSaveException{
        try{
            this.rootElement = this.doc.createElement("Simulation");
            this.rootElement.setAttribute("SimulationType", simType);
            this.doc.appendChild(rootElement);
        } 
        catch (Exception e){
            throw new InvalidXMLSaveException();
        }
    }
    
    /**
     * Add a Node 'element' with value 'text' to the current XML file
     * @param element
     * @param text
     */
    public void createAndAppendElement(String element, Object text){
        Element elem = doc.createElement(element);
        elem.appendChild(doc.createTextNode(text.toString()));
        this.rootElement.appendChild(elem);
    }
    
    /**
     * Writes the file to the location chosen via the FileChooser. Throws a TransformerException if the file
     * is null or cannot be transformed to XML format
     * @param fileToSave
     * @throws TransformerException
     */
    public void writeFile(File fileToSave) throws TransformerException{
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fileToSave);
            transformer.transform(source, result);
        }
        catch (Exception e){
            throw new TransformerException("Error saving XML file");
        }
    }

    /**
     * 
     * @return XML document
     */
    public Document getDoc () {
        return doc;
    }

    /**
     * 
     * @return first root element of the XML file
     */
    public Element getRootElement () {
        return rootElement;
    }
    
    /**
     * Create custom XML file depending on which simulation is currently running
     * @param params
     * @param runner
     */
    public abstract void createSimulationXML(Parameters params, Runner runner);
    
    /**
     * Create a list of common elements (typically cell states) with GridPositions as the value
     * @param runner
     */
    public void createListOfSimpleElements(Runner runner){
        for (Cell cell : runner.getCells()){
            createAndAppendElement(cell.getCurrState().getStateName(), cell.getGridPosition().toString());
        }
    }
}
