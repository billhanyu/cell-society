package Sugarscape;


import javafx.scene.paint.Color;
import cell.Cell;
import cell.GridPosition;
import cell.State;

public class SugarscapeCell extends Cell{

	public static PatchState empty = new PatchState(Color.ORANGE.darker().darker().darker().darker(), "0/4 FULL", 0);
	public static PatchState oneFourthsFull = new PatchState(Color.ORANGE.darker().darker().darker(), "1/4 QUARTER FULL", 1);
	public static PatchState twoFourthsFull = new PatchState(Color.ORANGE.darker().darker(), "2/4 HALF FULL", 2);
	public static PatchState threeFourthsFull = new PatchState(Color.ORANGE.darker(), "3/4 FULL", 3);
	public static PatchState fourFourthsFull = new PatchState(Color.ORANGE, "4/4 FULL", 4);

	public static AgentState noAgent = new AgentState(Color.GRAY, "NO AGENT");

	private PatchState patch;
	private AgentState agent;

	public SugarscapeCell(GridPosition gp, State s, PatchState ps, AgentState as) {
		super(gp, s);
		patch = ps;
		agent = as;
	}

	@Override
	public void checkChangeState() {
		growBack();
		// if the cell is occupied by an agent
		if( ! agent.equals(noAgent) ){
			if( ! checkIfDead())
				moveToNewCell();
		}
		if(agent.equals(noAgent))
			setFutureState(patch);
		else
			setFutureState(noAgent);
	}

	protected PatchState getPatchState(){
		return patch;
	}

	protected void setPatchState(PatchState ps){
		patch = ps;
	}

	protected AgentState getAgentState(){
		return agent;
	}

	protected void setAgentState(AgentState as){
		agent = as;
	}

	private void growBack(){
		if(getCurrState().equals(empty))
			setFutureState(oneFourthsFull);
		if(getCurrState().equals(oneFourthsFull))
			setFutureState(twoFourthsFull);
		if(getCurrState().equals(twoFourthsFull))
			setFutureState(threeFourthsFull);
		if(getCurrState().equals(threeFourthsFull))
			setFutureState(fourFourthsFull);
	}

	private boolean checkIfDead(){
		if (agent.getSugar() <= 0){
			agent = noAgent;
			return true;
		}
		agent.decreaseMetabolism();
		return false;
	}

	private void moveToNewCell(){
		SugarscapeCell bestNeighborOption = this;
		int maxHowFull = -1;
		for(int i = 0; i < getNeighbors().size(); i++){
			SugarscapeCell currNeighbor = (SugarscapeCell) getNeighbors().get(i);
			for(int v = 0; v < agent.getVisibility(); v++){
				PatchState ps = currNeighbor.getPatchState();
				if (ps.getHowFull() > maxHowFull){
					bestNeighborOption = currNeighbor;
					maxHowFull = ps.getHowFull();
				}
				currNeighbor = (SugarscapeCell) currNeighbor.getNeighbors().get(i);
			}
		}
		this.agent.addSugar(bestNeighborOption.getPatchState().getHowFull());
		bestNeighborOption.setPatchState(empty);
		bestNeighborOption.setAgentState(this.getAgentState());
		this.setAgentState(noAgent);
	}

}
