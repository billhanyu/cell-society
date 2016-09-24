package SpreadingFire;

import grid.Parameters;

public class SFParameters extends Parameters {
        
    private double probCatch;
    private SFExtendedParameters extendedParams;
    protected boolean modifiedStart;
    
    public SFParameters(){
        super("Spreading Fire", "Bill", "10", "10");
    }
    
    public SFParameters (Parameters p) {
        super(p);
    }

    public SFParameters(Parameters p, String probCatch){
        super(p);
	this.probCatch = Double.parseDouble(probCatch);
	modifiedStart = false;
    }
    
    public SFParameters(Parameters p, String probCatch, String startLocation){
        super(p);
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
    
    public boolean isModified(){
        return modifiedStart;
    }
}
