package xml.model;

import java.io.File;
import java.util.List;
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
import xmlExceptions.InvalidXMLSaveAddressException;


public abstract class XMLSaveFile {

    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private Document doc;
    private Element rootElement;
    
    public XMLSaveFile() throws ParserConfigurationException{
        this.docFactory = DocumentBuilderFactory.newInstance();
        this.docBuilder = docFactory.newDocumentBuilder();
        this.doc = docBuilder.newDocument();
    }

    public void initializeSaveFile(Parameters params, Runner runner, File fileLocation, 
                       ResourceBundle myResources, String simType){
        try {
            setRootElement(simType);
        }
        catch (InvalidXMLSaveAddressException e) {
            ErrorPop fileSaveError = new ErrorPop(300, 200, myResources.getString("FileSaveError"), myResources);
            fileSaveError.popup();
            return;
        }
        saveBasicParameters(params, simType);
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
    
    private void saveBasicParameters(Parameters params, String simType){
        createAndAppendElement("title", params.getTitle());
        createAndAppendElement("author", params.getAuthor());
        createAndAppendElement("numRows", params.getRows());
        createAndAppendElement("numCols", params.getCols());
        createAndAppendElement("shape", params.getGraphicType().toString());
    }

    public void setRootElement(String simType) throws InvalidXMLSaveAddressException{
        try{
            this.rootElement = this.doc.createElement("Simulation");
            this.rootElement.setAttribute("SimulationType", simType);
            this.doc.appendChild(rootElement);
        } 
        catch (Exception e){
            throw new InvalidXMLSaveAddressException();
        }
    }
    
    public void createAndAppendElement(String element, Object text){
        Element elem = doc.createElement(element);
        elem.appendChild(doc.createTextNode(text.toString()));
        this.rootElement.appendChild(elem);
    }
    
    public void writeFile(File fileToSave) throws TransformerException{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(fileToSave);
        transformer.transform(source, result);
    }

    public Document getDoc () {
        return doc;
    }

    public Element getRootElement () {
        return rootElement;
    }
    
    public abstract void createSimulationXML(Parameters params, Runner runner);
    
    public void createListOfElements(Runner runner){
        for (Cell cell : runner.getCells()){
            createAndAppendElement(cell.getCurrState().getStateName(), cell.getGridPosition().toString());
        }
    }
}
