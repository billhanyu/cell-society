package grid;

import java.util.List;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import cell.Cell;

public abstract class Runner {

	protected List<Cell> cells;
	protected Map<Cell, CellGraphic> cellGrid;

	public static final double MIN_SPEED_IN_SECONDS = .25;
	public static final double MAX_SPEED_IN_SECONDS = 3;
	private Timeline currentAnimation;
	private boolean freshStart;



	public Runner(List<Cell> cells, Map<Cell, CellGraphic> cellGrid) {
		this.cells = cells;
		this.cellGrid = cellGrid;
		freshStart = true;
	}

	public List<Cell> getCells(){
		return cells;
	}

	public Map<Cell, CellGraphic> getGraphics() {
		return cellGrid;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	public void setCellGrid(Map<Cell, CellGraphic> list){
		cellGrid = list;
	}

	private void updateAllCellStates(){
		for(Cell c : cells)
			c.checkChangeState();
		for(Cell c : cells)
			c.updateState();
	}

	private void updateCellGrid(){
		for(Cell c: cells){
			CellGraphic cg = cellGrid.get(c);
			cg.setColor(c.getCurrState().getColor());
			cellGrid.put(c, cg);
		}
	}

	public void step(){
		updateAllCellStates();
		updateCellGrid();
		System.out.println("step");
	}

	public void start(int speedOutOf100){
		if(freshStart){
			freshStart = false;
			
			// maps speedOutOf100 to the equivalent in range of minimum speed to maximum speed
			double mappedTimeInSecs = speedOutOf100 * ((MAX_SPEED_IN_SECONDS - MIN_SPEED_IN_SECONDS)/100) + MIN_SPEED_IN_SECONDS;
			
			Timeline animation = new Timeline();
			KeyFrame frame = new KeyFrame(Duration.seconds(mappedTimeInSecs),
					e -> step());
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			currentAnimation = animation;
		}
		currentAnimation.play();
	}

	public void pause(){
		currentAnimation.pause();
	}

	public void reset(){
		currentAnimation.stop();
	}
	
	public void updateSpeed(int newSpeed){
		start(newSpeed);
	}


}