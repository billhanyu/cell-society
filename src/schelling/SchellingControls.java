package schelling;

import java.util.List;

import cell.Cell;
import global.Initializer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import ui.Controls;
import ui.SliderBox;

public class SchellingControls extends Controls {

	public SchellingControls(Initializer initializer) {
		super(initializer);
	}
	
	public Node initRatioSlider(double ratio) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int newNumX = new_val.intValue();
				int newNumO = 100 - newNumX;
				double newRatio;
				if (newNumO == 0) {
					newRatio = Double.MAX_VALUE;
				}
				else {
					newRatio = ((double)newNumX) / newNumO;
				}
				((SLParameters) initializer.getParameters()).setRatio(newRatio);
				initializer.update();
			}
		};
		return new SliderBox("Red/Blue", 0, 100, (int) (ratio/(ratio + 1) * 100), 5, listener).getBox();
	}
	
	public Node initEmptySlider(double empty) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int newEmptyPercent = new_val.intValue();
				double newEmpty = newEmptyPercent / 100.0;
				((SLParameters) initializer.getParameters()).setEmptyRatio(newEmpty);
				initializer.update();
			}
		};
		return new SliderBox("Empty Ratio", 0, 100, (int) (empty*100), 5, listener).getBox();
	}
	
	public Node initIdealSlider(double ideal) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int newIdealPercent = new_val.intValue();
				double newIdeal = newIdealPercent / 100.0;
				List<Cell> cells = initializer.getRunner().getCells();
				for (Cell c: cells) {
					((SchellingCell)c).setIdealRatio(newIdeal);
				}
			}
		};
		return new SliderBox("Ideal Ratio", 0, 100, (int) (ideal * 100), 5, listener).getBox();
	}
}
