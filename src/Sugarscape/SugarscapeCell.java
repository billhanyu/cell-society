package Sugarscape;


import cell.Cell;
import cell.GridPosition;
import javafx.scene.paint.Color;

public class SugarscapeCell extends Cell {
	
	// DEFAULT PATCH STATES
	public static PatchState empty = new PatchState(Color.web("#FFFFFF"), "0/4 FULL", 0);
	public static PatchState oneFourthsFull = new PatchState(Color.web("#FFE4b7"), "1/4 QUARTER FULL", 1);
	public static PatchState twoFourthsFull = new PatchState(Color.web("#FFCE7a"), "2/4 HALF FULL", 2);
	public static PatchState threeFourthsFull = new PatchState(Color.web("#FFB942"), "3/4 FULL", 3);
	public static PatchState fourFourthsFull = new PatchState(Color.web("#FFA100"), "4/4 FULL", 4);

	// DEFAULT AGENT STATE
	public static AgentState noAgent = new AgentState(Color.GRAY, "NO AGENT");

	private PatchState patch;
	private AgentState agent;

	public SugarscapeCell(GridPosition gp, PatchState ps, AgentState as) {
		super(gp, as);
		patch = ps;
		agent = as;
		if(! agent.equals(noAgent))
			setFutureState(agent);
	}

	@Override
	public void checkChangeState() {
		growBack();
		// if the cell is occupied by an agent
		if( ! agent.equals(noAgent) ) {
			if( ! checkIfDead())
				moveToNewCell();
		}
		if(agent.equals(noAgent))
			setFutureState(patch);
		else
			setFutureState(noAgent);
	}

	protected PatchState getPatchState() {
		return patch;
	}

	protected void setPatchState(PatchState ps) {
		patch = ps;
	}

	protected AgentState getAgentState() {
		return agent;
	}

	protected void setAgentState(AgentState as) {
		agent = as;
	}

	private void growBack() {
		if(getCurrState().equals(empty))
			setFutureState(oneFourthsFull);
		if(getCurrState().equals(oneFourthsFull))
			setFutureState(twoFourthsFull);
		if(getCurrState().equals(twoFourthsFull))
			setFutureState(threeFourthsFull);
		if(getCurrState().equals(threeFourthsFull))
			setFutureState(fourFourthsFull);
	}

	private boolean checkIfDead() {
		if (agent.getSugar() <= 0){
			agent = (AgentState) noAgent;
			return true;
		}
		agent.decreaseMetabolism();
		return false;
	}

	private void moveToNewCell() {
		SugarscapeCell bestNeighborOption = 
				getBestNeighborOption(this, agent.getVisibility());
		this.agent.addSugar(bestNeighborOption.getPatchState().getHowFull());
		bestNeighborOption.setPatchState((PatchState) empty);
		bestNeighborOption.setAgentState(this.getAgentState());
		this.setAgentState((AgentState) noAgent);
	}
	
	private SugarscapeCell getBestNeighborOption(SugarscapeCell current, int visibility) {
		if (visibility <= 0) return current;
		SugarscapeCell best = this;
		for(int i = 0; i < current.getNeighbors().size(); i++) {
			SugarscapeCell currNeighbor = (SugarscapeCell) current.getNeighbors().get(i);
			SugarscapeCell nextBest = 
					getBestNeighborOption(currNeighbor, visibility - 1);
			if (nextBest.getPatchState().getHowFull() > best.getPatchState().getHowFull()) {
				best = nextBest;
			}
		}
		return best;
	}

}
