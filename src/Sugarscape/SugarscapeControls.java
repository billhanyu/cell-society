package Sugarscape;

import java.util.ResourceBundle;

import init.Initializer;
import javafx.scene.Node;
import ui.Controls;

public class SugarscapeControls extends Controls {

	public SugarscapeControls(Initializer initializer, ResourceBundle myResource) {
		super(initializer, myResource);
	}
	
	public Node initAgentSlider(double ratio) {
		int input = (int) (ratio * 100);
		return makeSliderBox(input, 0, 10, this.getResource().getString("AgentRatio"), 
				(observable, old_val, new_val) -> {
					int newNumX = new_val.intValue();
					double newRatio = newNumX / 100.0;
					((SugarParameters) this.getInitializer().getParameters()).setAgentRatio(newRatio);
					this.getInitializer().update();
				});
	}

}
