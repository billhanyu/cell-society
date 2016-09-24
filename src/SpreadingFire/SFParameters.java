package SpreadingFire;

import grid.Parameters;

public class SFParameters extends Parameters {
        
    private double probCatch;
    
    public SFParameters(){
        super("Spreading Fire", "Bill", "10", "10");
    }
    
        public SFParameters (String title, String author, String rows, String cols) {
            super(title, author, rows, cols);
        }

	public SFParameters(String title, String author, String rows, String cols, String probCatch){
	    super(title, author, rows, cols);
	    this.probCatch = Double.parseDouble(probCatch);
	}
	public double getProbCatch() {
		return probCatch;
	}
	
	public void setProbCatch(double probCatch) {
		this.probCatch = probCatch;
	}
}
