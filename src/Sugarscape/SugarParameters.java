package Sugarscape;

import java.util.Collection;

import cell.GridPosition;
import grid.Parameters;

/**
 * 
 * @author Brian
 * Contains parameters to begin a Sugarscape simulation. Parameters include:
 * oneFourth: list of cells that begin as 1/4 full
 * twoFourth: list of cells that begin as 1/2 full
 * threeFourth: list of cells that begin as 3/4 full
 * full: list of cells that begin as full
 * agentRatio: the ratio of the number of agents to the number of patches
 */
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
