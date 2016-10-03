package ui;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * @author billyu
 * a box that contains a parameter slider and a text prompt
 */
public class SliderBox {
	private Slider slider;
	private Text hud;
	private HBox box;
	
	public SliderBox(String text, int min, int max, int value, int increment){
		hud = new Text(text);
		slider = new ParamSlider(min, max, value, increment).initSlider();
	}
	
	public SliderBox(String text, int min, int max, int value, int increment, 
			ChangeListener<Number> listener) {
		hud = new Text(text);
		slider = new ParamSlider(min, max, value, increment).initSlider();
		slider.valueProperty().addListener(listener);
	}

	/**
	 * @return the frontend box
	 */
	public HBox getBox() {
		box = new HBox();
		box.getChildren().addAll(slider, hud);
		box.setSpacing(20);
		return box;
	}
}
