package ui;

import ui.ParamSlider;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import global.Initializer;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Controls {
	protected Initializer initializer;
	
	public Controls(Initializer initializer) {
		this.initializer = initializer;
	}
	
	public Node initSizeSlider(int size) {
		HBox box = new HBox();
		Slider sizeSlider = new ParamSlider(10, 50, size, 5).initSlider();
		Text sizeHud = new Text("Size: " + size + "x" + size);
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
		Button back = new Button("Back");
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				initializer.start();
			}
		});
		return back;
	}
	
	private Button initStartButton() {
		Button start = new Button("start");
		start.setOnAction(e -> {
			initializer.getRunner().start(10);
		});
		return start;
	}
	
	private Button initStepButton() {
		Button step = new Button("step");
		step.setOnAction(e -> {
			initializer.getRunner().step();
		});
		return step;
	}
	
	private Button initStopButton() {
		Button stop = new Button("stop");
		stop.setOnAction(e -> {
			initializer.getRunner().pause();
		});
		return stop;
	}
	
	private Button initResetButton() {
		Button reset = new Button("reset");
		reset.setOnAction(e -> {
			initializer.reset();
		});
		return reset;
	}
}
