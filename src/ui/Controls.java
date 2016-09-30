package ui;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ResourceBundle;

import global.Initializer;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Controls {
	protected Initializer initializer;
	protected ResourceBundle myResource;
	
	public Controls(Initializer initializer, ResourceBundle myResource) {
		this.initializer = initializer;
		this.myResource = myResource;
	}
	
	public Node makeSliderBox(int input, int min, int max, String resourceName, ChangeListener<Number> listener){
		return new SliderBox(myResource.getString(resourceName), min, max, input, 5, listener).getBox();
	}
	
	public Node initSizeSlider(int size) {
		return makeSliderBox(size, 10, 50, "Size", 
				(observable, old_val, new_val) -> {
					int newSize = new_val.intValue();
					initializer.getParameters().setRows(newSize);
					initializer.getParameters().setCols(newSize);
					initializer.update();
				});
	}
	
	public Node initSpeedSlider() {
		return makeSliderBox(90, 0, 100, "Speed", (observable, old_val, new_val) -> {            	
			int newSpeed = 100 - new_val.intValue();
			initializer.getRunner().setSpeed(newSpeed);
		});
	}	
	public Node initActionButtons() {
		Button start = initStartButton();
		Button step = initStepButton();
		Button stop = initStopButton();
		Button reset = initResetButton();
		HBox buttons = new HBox();
		buttons.getChildren().addAll(start, step, stop, reset);
		buttons.setSpacing(15);
		return buttons;
	}
	
	public Node initBackButton() {
		return makeButton("back", e->initializer.start());
	}
	
	private Button initStartButton() {
		return makeButton(myResource.getString("Start"), e->initializer.getRunner().start());
	}
	
	private Button initStepButton() {
		return makeButton(myResource.getString("Step"), e->initializer.getRunner().step());
	}
	
	private Button initStopButton() {
		return makeButton(myResource.getString("Stop"), e->initializer.getRunner().pause());
	}
	
	private Button initResetButton() {
		return makeButton(myResource.getString("Reset"), e->initializer.reset());
	}
	
	private Button makeButton(String text, EventHandler<ActionEvent> handler) {
		Button btn = new Button(text);
		btn.setOnAction(handler);
		return btn;
	}
	
}
