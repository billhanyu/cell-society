package SpreadingFire;

import java.util.List;

import cell.Cell;
import global.Initializer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import ui.Controls;
import ui.SliderBox;

public class SpreadingFireControls extends Controls {

	public SpreadingFireControls(Initializer initializer) {
		super(initializer);
	}
	
	public Node flamabilitySlider(double empty) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int flamabilityPercent = new_val.intValue();
				double flamability = flamabilityPercent / 100.0;
				List<Cell> cells = initializer.getRunner().getCells();
				for (Cell c: cells) {
					((SpreadingFireCell)c).setProbCatch(flamability);
				}
			}
		};
		return new SliderBox("Chance of catching fire", 0, 100, (int) (empty*100), 5, listener).getBox();
	}
}
