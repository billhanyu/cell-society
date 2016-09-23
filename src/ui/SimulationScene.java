package ui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import schelling.SchellingControls;

public class SimulationScene extends ProgScene {
	
	private SimulationPane pane;
	private Controls controls;
	private VBox controlGroup;
	private Group root;
	private Group simu; // simulation pane group

	public SimulationScene(SimulationPane pane, Controls controls) {
		super();
		this.pane = pane;
		this.controls = controls;
	}
	
	public void setSimulationPane(SimulationPane pane) {
		this.pane = pane;
		root.getChildren().remove(simu);
		addSimulationView();
	}

	@Override
	public Scene initScene() {
		root = new Group();
		addSimulationView();
		Node slider = controls.initSizeSlider();
		controlGroup = new VBox();
		controlGroup.getChildren().add(slider);
		controlGroup.setLayoutX(simu.getLayoutX() + pane.getWidth() + 20);
		controlGroup.setLayoutY(20);
		controlGroup.setSpacing(10);
		if (controls instanceof SchellingControls) {
			controlGroup.getChildren().add(((SchellingControls) controls).initRatioSlider());
			controlGroup.getChildren().add(((SchellingControls) controls).initEmptySlider());
		}
		root.getChildren().addAll(controlGroup);
		Scene scn = new Scene(root, width, height);
		
		return scn;
	}
	
	private void addSimulationView() {
		simu = pane.getGroup();
		simu.setLayoutX(10);
		simu.setLayoutY((height - pane.getHeight())/2);
		root.getChildren().add(simu);
	}
}
