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

	public static final double MIN_SPEED_IN_SECONDS = .05;
	public static final double MAX_SPEED_IN_SECONDS = 3;
	private int speedOutOf100;
	private Timeline currentAnimation;
	private boolean freshStart;
	
	private KeyFrame frame;
	private boolean inPlay = false;

	public Runner(List<Cell> cells, Map<Cell, CellGraphic> cellGrid) {
		this.cells = cells;
		this.cellGrid = cellGrid;
		freshStart = true;
		speedOutOf100 = 10;
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
	}

	public void start(){
		if(freshStart){
			freshStart = false;
			// maps speedOutOf100 to the equivalent in range of minimum speed to maximum speed
			
			Timeline animation = new Timeline();
			frame = new KeyFrame(Duration.seconds(getMappedTime()),
					e -> step());
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			currentAnimation = animation;
		}
		currentAnimation.play();
		inPlay = true;
	}

	public void pause(){
		currentAnimation.pause();
		inPlay = false;
	}
	
	public void setSpeed(int newSpeed){
		this.speedOutOf100 = newSpeed;
		if (inPlay) {
			frame = new KeyFrame(Duration.seconds(getMappedTime()),
					e -> step());
			currentAnimation.stop();
			currentAnimation.getKeyFrames().setAll(frame);
			currentAnimation.play();
		}
	}
	
	private double getMappedTime() {
		return speedOutOf100 * ((MAX_SPEED_IN_SECONDS - MIN_SPEED_IN_SECONDS)/100) + MIN_SPEED_IN_SECONDS;
	}
}