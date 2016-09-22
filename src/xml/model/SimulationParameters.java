package xml.model;

/*
 *  Store all of the parameters needed to initialize a simulation
 *  Front-End GUI stuff (ex: first menu) that is common between simulations resides here
 *  Subclasses include information that is passed to the Builder
 */

public class SimulationParameters {

    private final int guiSize;
    private final String guiTitle;
    
    public SimulationParameters(String GUIsize, String GUItitle){
        this.guiSize = Integer.parseInt(GUIsize);
        this.guiTitle = GUItitle;
    }

    public int getGuiSize () {
        return guiSize;
    }

    public String getGuiTitle () {
        return guiTitle;
    }
}
