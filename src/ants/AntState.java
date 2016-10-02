package ants;

import javafx.scene.paint.Color;
import cell.Cell;
import cell.State;

public class AntState extends State {
	
	private int age;
	private int maxAge;
	public static Color myColor = Color.BLACK;
	public static String myName = "ANT";
	protected boolean hasFoodItem = false;
	private Cell orientation;

	public AntState(int maxAge, Cell orientation) {
		super(myColor, myName);
		age = 0;
		this.maxAge = maxAge;
		this.orientation = orientation;
	}
	
	private void getOlder(){
		age ++;
	}
	
	/**
	 * increments age and returns true if ant
	 * is too old and should die
	 */
	public boolean isTooOld(){
		getOlder();
		return age >= maxAge;
	}
	
	public void setMaxAge(int maxAge){
		this.maxAge = maxAge;
	}
	
	protected Cell getOrientation(){
		return orientation;
	}
	
	protected void setOrientation(Cell orientation){
		this.orientation = orientation;
	}

}
