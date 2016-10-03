package init;

import org.w3c.dom.Element;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import ui.Controls;
import xml.model.XMLSaveFile;

public abstract class SimulationComponents {
    
    private Runner runner;
    private Builder builder;
    private Parameters params;
    private Controls controls;
    private XMLSaveFile xmlSaveFile;
    
    
    public SimulationComponents(Element rootElement, Initializer initializer){
        
    }
    
    

}
