package cell;

import javafx.scene.paint.Color;

/**
 * @author Addison Howenstine
 * 
 * A state holds a color and a name
 * and will be used to occupy a cell and reasoned with
 * in each model. May be extended further in some models
 */

public class State implements Cloneable {
	
	private Color color;
	private String stateName;	
	
	public State(Color c, String n){
		color = c;
		stateName = n;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	/*
	 * Equal if the name of the state
	 * is the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof State))
			return false;
		State other = (State) obj;
		return(stateName != null && stateName.equals(other.stateName));
	}
	
	public State clone() {
		return this;
	}
}
