package ui;
import SpreadingFire.SFParameters;
import SpreadingFire.SpreadingFireControls;
import WaTor.WTParameters;
import WaTor.WaTorControls;
import global.Initializer;
import grid.Parameters;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import schelling.SLParameters;
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

	public Scene initScene(int size, Parameters p){ //double ratio, double empty, double ideal) {
		root = new Group();
		addSimulationView();
		Node slider = controls.initSizeSlider(size);
		Node speed = controls.initSpeedSlider();
		Node buttons = controls.initActionButtons();
		Node back = controls.initBackButton();
		controlGroup = new VBox();
		controlGroup.getChildren().addAll(slider);
		controlGroup.setLayoutX(simu.getLayoutX() + pane.getWidth() + 20);
		controlGroup.setLayoutY(20);
		controlGroup.setSpacing(10);
		if (controls instanceof SchellingControls) {
			addSchellingControls((SLParameters) p);
		}
		if (controls instanceof WaTorControls) {
			addWaTorControls((WTParameters) p);
		}
		if (controls instanceof SpreadingFireControls){
			addFireControls((SFParameters) p);
		}
		
		buttons.setLayoutX(controlGroup.getLayoutX());
		buttons.setLayoutY(Initializer.SCENE_HEIGHT - 200);
		speed.setLayoutX(controlGroup.getLayoutX());
		speed.setLayoutY(Initializer.SCENE_HEIGHT - 150);
		back.setLayoutX(Initializer.SCENE_WIDTH - 80);
		back.setLayoutY(Initializer.SCENE_HEIGHT - 50);
		root.getChildren().addAll(controlGroup, buttons, speed, back);
		Scene scn = new Scene(root, width, height);

		return scn;
	}
	private void addSchellingControls(SLParameters p){//double ratio, double empty, double ideal) {
		controlGroup.getChildren().add(((SchellingControls) controls).initRatioSlider(p.getRatio()));
		System.out.println(p.getRatio());
		controlGroup.getChildren().add(((SchellingControls) controls).initEmptySlider(p.getEmptyRatio()));
		controlGroup.getChildren().add(((SchellingControls) controls).initIdealSlider(p.getIdealRatio()));
	}

	private void addWaTorControls(WTParameters p){
		controlGroup.getChildren().add(((WaTorControls) controls).initRatioSlider(p.getRatio()));
		controlGroup.getChildren().add(((WaTorControls) controls).initEmptySlider(p.getEmptyRatio()));
		controlGroup.getChildren().add(((WaTorControls) controls).sharkStarveRateSlider(p.getSharkStarve()));
		controlGroup.getChildren().add(((WaTorControls) controls).sharkEnergyGainedFromEatingSlider(p.getEnergyFromEating()));
		controlGroup.getChildren().add(((WaTorControls) controls).fishReproductionRateSlider(p.getFishRate()));
		controlGroup.getChildren().add(((WaTorControls) controls).sharkReproductionRateSlider(p.getSharkRate()));
	}
	
	private void addFireControls(SFParameters p){
		controlGroup.getChildren().add(((SpreadingFireControls) controls).flamabilitySlider(p.getProbCatch()));
	}
	
	

	private void addSimulationView() {
		simu = pane.getGroup();
		simu.setLayoutX(10);
		simu.setLayoutY((height - pane.getHeight())/2);
		root.getChildren().add(simu);
	}
}

