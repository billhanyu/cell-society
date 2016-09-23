package ui;

import ui.ParamSlider;
import javafx.beans.value.ObservableValue;
import global.Initializer;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Controls {
	protected Initializer initializer;
	
	public Controls(Initializer initializer) {
		this.initializer = initializer;
	}
	
	public Node initSizeSlider() {
		HBox box = new HBox();
		Slider sizeSlider = new ParamSlider(10, 50, 20, 5).initSlider();
		Text sizeHud = new Text("Size: 20x20");
		sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
            	int newSize = new_val.intValue();
            	initializer.getParameters().setRows(newSize);
            	initializer.getParameters().setCols(newSize);
            	initializer.update();
            	sizeHud.setText("Size: " + newSize + "x" + newSize);
            }
		});
		box.getChildren().addAll(sizeSlider, sizeHud);
		box.setSpacing(20);
		return box;
	}
}
