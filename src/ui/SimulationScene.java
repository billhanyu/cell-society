package ui;

import global.Initializer;
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
	public Scene initScene(int size) {
		root = new Group();
		addSimulationView();
		Node slider = controls.initSizeSlider(size);
		Node buttons = controls.initActionButtons();
		Node back = controls.initBackButton();
		controlGroup = new VBox();
		controlGroup.getChildren().addAll(slider);
		controlGroup.setLayoutX(simu.getLayoutX() + pane.getWidth() + 20);
		controlGroup.setLayoutY(20);
		controlGroup.setSpacing(10);
		if (controls instanceof SchellingControls) {
			controlGroup.getChildren().add(((SchellingControls) controls).initRatioSlider());
			controlGroup.getChildren().add(((SchellingControls) controls).initEmptySlider());
			controlGroup.getChildren().add(((SchellingControls) controls).initIdealSlider());
		}
		buttons.setLayoutX(controlGroup.getLayoutX());
		buttons.setLayoutY(Initializer.SCENE_HEIGHT - 150);
		back.setLayoutX(Initializer.SCENE_WIDTH - 80);
		back.setLayoutY(Initializer.SCENE_HEIGHT - 50);
		root.getChildren().addAll(controlGroup, buttons, back);
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
