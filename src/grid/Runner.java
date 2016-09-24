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

	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
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

	public void start(){
		if(freshStart){
			freshStart = false;
			Timeline animation = new Timeline();
			KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
					e -> step());
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
			currentAnimation = animation;
		}
	}

	public void pause(){
		currentAnimation.pause();
	}

	public void reset(){
		currentAnimation.stop();
	}



	// TODO get animation working

	// TODO move animation stuff from Main to here

	// TODO method to set step

	// TODO start, step, stop, reset
}