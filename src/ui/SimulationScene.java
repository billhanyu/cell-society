package ui;

import javafx.scene.Group;
import javafx.scene.Scene;

public class SimulationScene extends ProgScene {
	
	private SimulationPane pane;

	public SimulationScene(SimulationPane pane) {
		super();
		this.pane = pane;
	}

	@Override
	public Scene initScene() {
		//TODO: init with pane
		Group root = new Group();
		Group simu = pane.getGroup();
		simu.setLayoutX((width - pane.getWidth())/2);
		simu.setLayoutY(10);
		root.getChildren().add(pane.getGroup());
		Scene scn = new Scene(root, width, height);
		return scn;
	}
}
