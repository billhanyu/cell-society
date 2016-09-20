package SpreadingFire;

import grid.Builder;

public class SpreadingFireBuilder extends Builder{
	
	private int rows;
	private int cols;
	private double probCatch;
	
	public SpreadingFireBuilder(int r, int c, double prob){
		rows = r;
		cols = c;
		probCatch = prob;
	}
	
	public void init(){
		SpreadingFireRunner runner = new SpreadingFireRunner();
		runner.init(rows, cols);		
	}

}
