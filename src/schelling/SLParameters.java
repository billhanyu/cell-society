package schelling;

import grid.Parameters;

public class SLParameters extends Parameters {
    public SLParameters(){
        super("Schelling", "Bill", "10", "10");
    }
	public SLParameters (String title, String author, String rows, String cols) {
        super(title, author, rows, cols);
        // TODO Auto-generated constructor stub
    }

    private double emptyRatio; // vacant ratio
	private double ratio; // red-blue ratio
	private double idealRatio; // the ratio for the cell to become satisfied
	
	public double getEmptyRatio() {
		return emptyRatio;
	}
	
	public void setEmptyRatio(double emptyRatio) {
		this.emptyRatio = emptyRatio;
	}
	
	public double getRatio() {
		return ratio;
	}
	
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public double getIdealRatio() {
		return idealRatio;
	}

	public void setIdealRatio(double idealRatio) {
		this.idealRatio = idealRatio;
	}
    
    public boolean isModified () {
        // TODO Auto-generated method stub
        return false;
    }
}
