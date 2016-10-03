package ui;

import java.util.ResourceBundle;
import javax.xml.parsers.ParserConfigurationException;
import init.Initializer;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * @author billyu
 * UI controls of simulations
 * return Node's that are sliders and buttons
 * this class include the general ones: size, play, reset, pause, stop etc
 * controls unique to each simulation are in subclasses
 */

public class Controls {
	private Initializer initializer;
	private ResourceBundle myResource;
	
	public Controls(Initializer initializer, ResourceBundle myResource) {
		this.initializer = initializer;
		this.myResource = myResource;
	}
	
	/**
	 * @param input default value of the slider
	 * @param min minimum value of the slider
	 * @param max maximum value of the slider
	 * @param resourceName slider prompt resource key
	 * @param listener what will happens after the value of slider is changed
	 * @return a Node that contains the slider and a Label prompt
	 */
	public Node makeSliderBox(int input, int min, int max, String resourceName, ChangeListener<Number> listener) {
		if (input < min || input > max) {
			ErrorPop pop = new ErrorPop(300, 200, this.myResource.getString("SliderRangeError"), this.myResource);
			pop.popup();
			throw new IllegalArgumentException(this.myResource.getString("SliderRangeError"));
		}
		return new SliderBox(resourceName, min, max, input, 5, listener).getBox();
	}
	
	/**
	 * @param size default size of the grid
	 * @return slider control of the size
	 */
	public Node initSizeSlider(int size) {
		return makeSliderBox(size, 10, 50, this.myResource.getString("Size"), 
				(observable, old_val, new_val) -> {
					int newSize = new_val.intValue();
					initializer.getParameters().setRows(newSize);
					initializer.getParameters().setCols(newSize);
					initializer.update();
				});
	}
	
	/**
	 * @return slider control of refresh speed
	 */
	public Node initSpeedSlider() {
		return makeSliderBox(90, 0, 100, this.myResource.getString("Speed"), (observable, old_val, new_val) -> {            	
			int newSpeed = 100 - new_val.intValue();
			initializer.getRunner().setSpeed(newSpeed);
		});
	}	
	
	/**
	 * @return an HBox that contains animation control buttons
	 */
	public Node initActionButtons() {
		Button start = initStartButton();
		Button step = initStepButton();
		Button stop = initStopButton();
		Button reset = initResetButton();
		HBox buttons = new HBox();
		buttons.getChildren().addAll(start, step, stop, reset);
		buttons.setSpacing(10);
		return buttons;
	}
	
	public Node initBackButton() {
		return makeButton(this.myResource.getString("Back"), e->initializer.start());
	}
	
	protected Initializer getInitializer() {
		return this.initializer;
	}
	
	protected ResourceBundle getResource() {
		return this.myResource;
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
	
	/**
	 * This method will be used to save current simulation parameters to an xml file
	 */
	public Button initXMLSaveButton(){
	        return makeButton(myResource.getString("SaveFile"), e->{
                try {
                    initializer.saveFile();
                }
                catch (ParserConfigurationException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
	}
	
	/**
	 * @param text button text
	 * @param handler button action handler
	 * @return a button with text and handler
	 */
	private Button makeButton(String text, EventHandler<ActionEvent> handler) {
		Button btn = new Button(text);
		btn.setOnAction(handler);
		return btn;
	}
}
