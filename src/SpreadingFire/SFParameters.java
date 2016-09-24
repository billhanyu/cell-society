package SpreadingFire;

import grid.Parameters;

public class SFParameters extends Parameters {
        
    private double probCatch;
    private SFExtendedParameters extendedParams;
    protected boolean modifiedStart;
    
    public SFParameters(){
        super("Spreading Fire", "Bill", "10", "10");
    }
    
    public SFParameters (String title, String author, String rows, String cols) {
        super(title, author, rows, cols);
    }

    public SFParameters(String title, String author, String rows, String cols, String probCatch){
        super(title, author, rows, cols);
	this.probCatch = Double.parseDouble(probCatch);
	modifiedStart = false;
    }
    
    public SFParameters(String title, String author, String rows, String cols, String probCatch, String startLocation){
        super(title, author, rows, cols);
        this.probCatch = Double.parseDouble(probCatch);
        extendedParams = new SFExtendedParameters();
        extendedParams.setRowStart(Integer.parseInt(startLocation.split(" ")[0]) - 1);
        extendedParams.setColStart(Integer.parseInt(startLocation.split(" ")[1]) - 1);
        modifiedStart = true;
    }
    
    public double getProbCatch() {
	return probCatch;
    }
    
    
	
    public SFExtendedParameters getExtendedParams () {
        return extendedParams;
    }

    public void setModified(boolean condition){
        modifiedStart = condition;
    }
    @Override
    public boolean isModified(){
        return modifiedStart;
    }
}
