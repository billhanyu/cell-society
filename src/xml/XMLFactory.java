package xml;

import java.util.Objects;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class XMLFactory {
    
private final Element rootElement;
    
    public XMLFactory(Element rootElement) {
        this.rootElement = rootElement;
    }
    
    public abstract boolean isValidFile();
    
    
    protected String getAttribute (String attributeName) {
        return rootElement.getAttribute(attributeName);
    }
    
    protected String getTextValue (String tagName) {
        String textValue = null;
        NodeList nodeList = rootElement.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0){
            textValue = nodeList.item(0).getTextContent();
        }
        return textValue;
    }
    
    
}
