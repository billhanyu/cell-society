package grid;

import java.util.ArrayList;
import cell.Cell;

public class Runner {
	ArrayList<Cell> cells;
	
	public Runner() {
		cells = new ArrayList<Cell>();
	}
	
	public void addCell(Cell cell) {
		cells.add(cell);
	}
	
	public void removeCell(Cell cell) {
		cells.remove(cell);
	}
	
	public void update() {
		
	}
}
