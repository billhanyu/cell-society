package gameOfLife;

import grid.Parameters;

public class GLParameters extends Parameters {
    
    private double ratioOfAlive;
    private boolean modifiedStart;

    public GLParameters (String title, String author, String rows, String cols) {
        super(title, author, rows, cols);
    }
    
    public GLParameters(Parameters p, String ratioOfAlive){
        super(p);
        this.ratioOfAlive = Double.parseDouble(ratioOfAlive);
        modifiedStart = false;
    }

    
    public double getRatioOfAlive () {
        return ratioOfAlive;
    }

    public boolean isModifiedStart () {
        return modifiedStart;
    }

}
