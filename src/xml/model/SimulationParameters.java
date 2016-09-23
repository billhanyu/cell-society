package xml.model;

/*
 *  Store all of the parameters needed to initialize a simulation
 *  Front-End GUI stuff (ex: first menu) that is common between simulations resides here
 *  Subclasses include information that is passed to the Builder
 */

public class SimulationParameters {

    private final int simSize;
    private final String simTitle;
    private final String simAuthor;
    
    public SimulationParameters(String title, String author, String size){
        this.simSize = Integer.parseInt(size);
        this.simTitle = title;
        this.simAuthor = author;
    }

    public int getSimSize () {
        return simSize;
    }

    public String getSimTitle () {
        return simTitle;
    }

    public String getSimAuthor () {
        return simAuthor;
    }

}
