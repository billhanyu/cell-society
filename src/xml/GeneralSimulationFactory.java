package xml;

import org.w3c.dom.Element;
import grid.Parameters;

public class GeneralSimulationFactory extends SimulationFactory{

    public GeneralSimulationFactory (Element rootElement) {
        super(rootElement);
    }

    @Override
    public Parameters getSimulationParameters () {
        return new Parameters(getTextValue("title"), getTextValue("author"),
                              getTextValue("numRows"), getTextValue("numCols"));
    }

    @Override
    public String getSimulationType () {
        return getAttribute("SimulatonType");
    }

}
