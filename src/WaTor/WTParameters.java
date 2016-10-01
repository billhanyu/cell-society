package WaTor;

import java.util.Collection;
import cell.GridPosition;
import grid.Parameters;

public class WTParameters extends Parameters {
    
        private int sharkStarve;
        private int fishRate;
        private int sharkRate;
        private int energyFromEating;
        private double emptyRatio;
        private double ratio;
    
        public WTParameters (Parameters p) {
            super(p);
        }
        
        public WTParameters(Parameters p, int sharkRate, int fishRate,
                            int sharkStarve, int energyFromEating){
            super(p);
            this.sharkRate = sharkRate;
            this.fishRate = fishRate;
            this.sharkStarve = sharkStarve;
            this.energyFromEating = energyFromEating;
            
        }
        
        public WTParameters(Parameters p, int sharkRate, int fishRate, double emptyRatio, double ratio,
                            int sharkStarve, int energyFromEating){
            super(p);
            this.sharkStarve = sharkStarve;
            this.fishRate = fishRate;
            this.sharkRate = sharkRate;
            this.energyFromEating = energyFromEating;
            this.emptyRatio = emptyRatio;
            this.ratio = ratio;
            setByLocations(false);
            
        }
    	
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
        
        public int getEnergyFromEating () {
            return energyFromEating;
        }

        public void setEnergyFromEating (int energyFromEating) {
            this.energyFromEating = energyFromEating;
        }

        
}
