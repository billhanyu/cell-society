package WaTor;

import grid.Parameters;

public class WTParameters extends Parameters {
    
    public WTParameters(){
        super("WaTor", "Bill", "10", "10");
    }
	public WTParameters (String title, String author, String rows, String cols) {
        super(title, author, rows, cols);
        // TODO Auto-generated constructor stub
    }

    private int sharkStarve;
	private int fishRate;
	private int sharkRate;
	private double emptyRatio;
	private double ratio;
	
	public int getSharkStarve() {
		return sharkStarve;
	}
	
	public void setSharkStarve(int sharkStarve) {
		this.sharkStarve = sharkStarve;
	}

	public int getFishRate() {
		return fishRate;
	}

	public void setFishRate(int fishRate) {
		this.fishRate = fishRate;
	}

	public int getSharkRate() {
		return sharkRate;
	}

	public void setSharkRate(int sharkRate) {
		this.sharkRate = sharkRate;
	}

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
}
