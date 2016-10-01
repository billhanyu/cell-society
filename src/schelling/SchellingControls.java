package schelling;

import java.util.List;
import java.util.ResourceBundle;

import cell.Cell;
import global.Initializer;
import javafx.scene.Node;
import ui.Controls;

public class SchellingControls extends Controls {

	public SchellingControls(Initializer initializer, ResourceBundle myResources) {
		super(initializer, myResources);
	}

	public Node initRatioSlider(double ratio) {
		int input = (int) (ratio/(ratio + 1) * 100);
		return makeSliderBox(input, 0, 100, "RedBlue", 
				(observable, old_val, new_val) -> {
					int newNumX = new_val.intValue();
					int newNumO = 100 - newNumX;
					double newRatio;
					if (newNumO == 0) {
						newRatio = Double.MAX_VALUE;
					}
					else {
						newRatio = ((double)newNumX) / newNumO;
					}
					((SLParameters) this.getInitializer().getParameters()).setRatio(newRatio);
					this.getInitializer().update();
				});
	}

	public Node initEmptySlider(double empty) {
		int input = (int) (empty*100);
		return makeSliderBox(input, 0, 100, "Empty",
				(observable, old_val, new_val) -> {
					int newEmptyPercent = new_val.intValue();
					double newEmpty = newEmptyPercent / 100.0;
					((SLParameters) this.getInitializer().getParameters()).setEmptyRatio(newEmpty);
					this.getInitializer().update();
				});
	}

	public Node initIdealSlider(double ideal) {
		int input = (int) (ideal * 100);
		return makeSliderBox(input, 0, 100, "Ideal", 
				(observable, old_val, new_val) -> {
					int newIdealPercent = new_val.intValue();
					double newIdeal = newIdealPercent / 100.0;
					List<Cell> cells = this.getInitializer().getRunner().getCells();
					for (Cell c: cells) {
						((SchellingCell)c).setIdealRatio(newIdeal);
					}
				});
	}
}
