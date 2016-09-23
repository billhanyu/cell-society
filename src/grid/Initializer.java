package grid;

import SpreadingFire.SFParameters;
import SpreadingFire.SpreadingFireBuilder;
import javafx.stage.Stage;
import ui.Controls;
import ui.ErrorPop;
import ui.SimulationScene;

public class Initializer {
	private Stage stage;
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 500;
	public static final String SEGREGATION = "Segregation";
	public static final String PRED_PREY = "Predator-Prey";
	public static final String FIRE = "Fire";
	public static final String LIFE = "Game of Life";
	
	private Builder builder;
	private Parameters param;
	private Runner runner;
	private String algorithm;
	private SimulationScene scn;
	
	public Initializer(Stage s) {
		stage = s;
	}
	
	public void setParameters(Parameters param) {
		this.param = param;
	}
	
	public Parameters getParameters() {
		return param;
	}
	
	public void initSimulation(String algorithm) {
		this.algorithm = algorithm;
		if (algorithm.equals(SEGREGATION)) {
			
		}
		else if (algorithm.equals(PRED_PREY)) {
			
		}
		else if (algorithm.equals(FIRE)) {
			param = new SFParameters();
			param.setRows(20);// TODO read the numRows and numCols from XML
			param.setCols(20);
			builder = new SpreadingFireBuilder(param);
			runner = builder.init();
		}
		else if (algorithm.equals(LIFE)) {
			
		}
		else {
			ErrorPop error = new ErrorPop(300, 200, "Simulation Initializing Error");
			error.popup();
		}
		stage.setTitle(algorithm);
		initSimulationScene();
	}
	
	public void update() {
		runner = builder.init();
		scn.setSimulationPane(builder.getSimulationPane());
	}
	
	private void initSimulationScene() {
		Controls controls = new Controls(this);
		scn = new SimulationScene(builder.getSimulationPane(), controls);
		stage.setScene(scn.initScene());
	}
}
