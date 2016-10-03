package xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Brian
 * Defines useful methods when trying to parse the XML file. The root element is traversed in
 * order to get the value of each node/attribute in the XML file
 */
public abstract class XMLFactory {

	protected final Element rootElement;

	/**
	 * Initialize XMLFactory with root element of file
	 * @param rootElement
	 */
	public XMLFactory(Element rootElement) {
		this.rootElement = rootElement;
	}

	/**
	 * Return the value of the attribute. Used to determine which type of simulation is being loaded
	 * @param attributeName
	 * @return value associated with the current attribute
	 */
	protected String getAttribute (String attributeName) {
		return rootElement.getAttribute(attributeName);
	}

	/**
	 * Return the value of the String associated with each element in the XML file
	 * @param tagName
	 * @return XML Element value
	 */
	protected String getTextValue (String tagName) {
		String textValue = null;
		NodeList nodeList = rootElement.getElementsByTagName(tagName);
		if (nodeList != null && nodeList.getLength() > 0){
			textValue = nodeList.item(0).getTextContent();
		}
		return textValue;
	}
}
