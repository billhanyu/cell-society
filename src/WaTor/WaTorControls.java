package WaTor;

import java.util.ResourceBundle;
import global.Initializer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import ui.Controls;
import ui.SliderBox;

public class WaTorControls extends Controls {

	public WaTorControls(Initializer initializer, ResourceBundle myResource) {
		super(initializer, myResource);
	}
	
	public Node sharkStarveRateSlider(int rate) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int starveRate = new_val.intValue();
				((WTParameters) initializer.getParameters()).setSharkStarve(starveRate);
			}
		};
		return new SliderBox(myResource.getString("SharkStarve"), 0, 25, rate, 5, listener).getBox();
	}
	
	public Node fishReproductionRateSlider(int rate) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int fishReproductionRate = new_val.intValue();
				((WTParameters) initializer.getParameters()).setFishRate(fishReproductionRate);
			}
		};
		return new SliderBox(myResource.getString("FishRep"), 0, 25, rate, 5, listener).getBox();
	}
	
	public Node sharkReproductionRateSlider(int rate) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int sharkReproductionRate = new_val.intValue();
				((WTParameters) initializer.getParameters()).setSharkRate(sharkReproductionRate);
			}
		};
		return new SliderBox(myResource.getString("SharkRep"), 0, 25, rate, 5, listener).getBox();
	}
	
	public Node sharkEnergyGainedFromEatingSlider(int rate) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int energy = new_val.intValue();
				((WTParameters) initializer.getParameters()).setEnergyFromEating(energy);
			}
		};
		return new SliderBox(myResource.getString("SharkGain"), 0, 25, rate, 5, listener).getBox();
	}
	
	public Node initRatioSlider(double ratio) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int newNumShark = new_val.intValue();
				int newNumFish = 100 - newNumShark;
				double newRatio;
				if (newNumFish == 0) {
					newRatio = Double.MAX_VALUE;
				}
				else {
					newRatio = ((double)newNumShark) / newNumFish;
				}
				((WTParameters) initializer.getParameters()).setRatio(newRatio);
				initializer.update();
			}
		};
		return new SliderBox("Shark/Fish", 0, 100, (int) (ratio/(ratio + 1) * 100), 5, listener).getBox();
	}
	
	public Node initEmptySlider(double empty) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int newEmptyPercent = new_val.intValue();
				double newEmpty = newEmptyPercent / 100.0;
				((WTParameters) initializer.getParameters()).setEmptyRatio(newEmpty);
				initializer.update();
			}
		};
		return new SliderBox(myResource.getString("Empty"), 0, 100, (int) (empty*100), 5, listener).getBox();
	}
}