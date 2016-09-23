package schelling;

import global.Initializer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ui.Controls;
import ui.ParamSlider;

public class SchellingControls extends Controls {

	public SchellingControls(Initializer initializer) {
		super(initializer);
	}
	
	public Node initRatioSlider() {
		HBox box = new HBox();
		Slider sizeSlider = new ParamSlider(0, 100, 50, 5).initSlider();
		Text sizeHud = new Text("Red/Blue");
		sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
            	int newNumX = new_val.intValue();
            	int newNumO = 100 - newNumX;
            	double newRatio;
            	if (newNumO == 0) {
            		newRatio = Double.MIN_VALUE;
            	}
            	else {
            		newRatio = ((double)newNumO) / newNumX;
            	}
            	((SLParameters) initializer.getParameters()).setRatio(newRatio);
            	initializer.update();
            }
		});
		box.getChildren().addAll(sizeSlider, sizeHud);
		box.setSpacing(20);
		return box;
	}
	
	public Node initEmptySlider() {
		HBox box = new HBox();
		Slider emptySlider = new ParamSlider(0, 100, 25, 5).initSlider();
		Text emptyHud = new Text("Empty Ratio");
		emptySlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int newEmptyPercent = new_val.intValue();
				double newEmpty = newEmptyPercent / 100.0;
				((SLParameters) initializer.getParameters()).setEmptyRatio(newEmpty);
				initializer.update();
			}
		});
		box.getChildren().addAll(emptySlider, emptyHud);
		box.setSpacing(20);
		return box;
	}

}
