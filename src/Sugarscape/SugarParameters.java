package Sugarscape;

import java.util.Collection;

import cell.GridPosition;
import grid.Parameters;

public class SugarParameters extends Parameters {
	
	private Collection<GridPosition> oneFourth;
	private Collection<GridPosition> twoFourth;
	private Collection<GridPosition> threeFourth;
	private Collection<GridPosition> full;
	private double agentRatio;

	public SugarParameters(Parameters p) {
		super(p);
	}
	
	public SugarParameters(Parameters p, Collection<GridPosition> oneFourth, Collection<GridPosition> twoFourth,
			Collection<GridPosition> threeFourth, Collection<GridPosition> full, double agentRatio) {
		super(p);
		this.oneFourth = oneFourth;
		this.twoFourth = twoFourth;
		this.threeFourth = threeFourth;
		this.full = full;
		this.agentRatio = agentRatio;
	}
	
	public Collection<GridPosition> getOne() {
		return this.oneFourth;
	}
	
	public Collection<GridPosition> getTwo() {
		return this.twoFourth;
	}
	
	public Collection<GridPosition> getThree() {
		return this.threeFourth;
	}
	
	public Collection<GridPosition> getFull() {
		return this.full;
	}
	
	public void setAgentRatio(double ratio) {
		this.agentRatio = ratio;
	}
	
	public double getAgentRatio() {
		return this.agentRatio;
	}

}
