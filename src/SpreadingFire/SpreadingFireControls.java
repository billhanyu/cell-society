package SpreadingFire;

import java.util.List;
import java.util.ResourceBundle;

import cell.Cell;
import global.Initializer;
import javafx.scene.Node;
import ui.Controls;

public class SpreadingFireControls extends Controls {

	public SpreadingFireControls(Initializer initializer, ResourceBundle myResources) {
		super(initializer, myResources);
	}

	public Node flamabilitySlider(double flamInput) {
		int input = (int) (flamInput*100);
		return makeSliderBox(input, 0, 100, "FireChance",
				(observable, old_val, new_val) -> {
					int flamabilityPercent = new_val.intValue();
					double flamability = flamabilityPercent / 100.0;
					List<Cell> cells = this.getInitializer().getRunner().getCells();
					for (Cell c: cells) {
						((SpreadingFireCell)c).setProbCatch(flamability);
					}
				});
	}
}
