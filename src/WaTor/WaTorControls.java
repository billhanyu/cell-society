package WaTor;

import global.Initializer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import ui.Controls;
import ui.SliderBox;

public class WaTorControls extends Controls {

	public WaTorControls(Initializer initializer) {
		super(initializer);
	}
	
	public Node sharkStarveRateSlider(int rate) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int starveRate = new_val.intValue();
				((WTParameters) initializer.getParameters()).setSharkStarve(starveRate);
			}
		};
		return new SliderBox("Shark Starve", 0, 25, (int) (rate * 100), 5, listener).getBox();
	}
	
	public Node fishReproductionRateSlider(int rate) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int fishReproductionRate = new_val.intValue();
				((WTParameters) initializer.getParameters()).setFishRate(fishReproductionRate);
			}
		};
		return new SliderBox("Fish Reproduction", 0, 25, (int) (rate * 100), 5, listener).getBox();
	}
	
	public Node sharkReproductionRateSlider(int rate) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int sharkReproductionRate = new_val.intValue();
				((WTParameters) initializer.getParameters()).setSharkRate(sharkReproductionRate);
			}
		};
		return new SliderBox("Shark Reproduction", 0, 25, (int) (rate * 100), 5, listener).getBox();
	}
	
	public Node sharkEnergyGainedFromEatingSlider(int rate) {
		ChangeListener<Number> listener = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				int energy = new_val.intValue();
				((WTParameters) initializer.getParameters()).setSharkRate(energy);
			}
		};
		return new SliderBox("Shark Gain", 0, 25, (int) (rate * 100), 5, listener).getBox();
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
		return new SliderBox("Empty Ratio", 0, 100, (int) (empty*100), 5, listener).getBox();
	}
}
