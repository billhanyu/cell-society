package global;

import SpreadingFire.SFParameters;
import SpreadingFire.SpreadingFireBuilder;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import schelling.SLParameters;
import schelling.SchellingBuilder;
import schelling.SchellingControls;
import ui.Controls;
import ui.ErrorPop;
import ui.SimulationScene;
import ui.StartScene;

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
	private AlgorithmType type;
	private SimulationScene scn;
	private Controls controls;
	
	class ExitAction implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			stage.close();
		}
    }
	
	public Initializer(Stage s) {
		stage = s;
	}
	
	public void start() {
		stage.setTitle("Cell Society");
		StartScene start = new StartScene(new ExitAction(), this);
        stage.setScene(start.initScene());
	}
	
	public void reset() {
		// TODO reset the whole thing
	}
	
	public void setParameters(Parameters param) {
		this.param = param;
	}
	
	public Parameters getParameters() {
		return param;
	}
	
	public void update() {
		runner = builder.init();
		scn.setSimulationPane(builder.getSimulationPane());
	}
	
	public Runner getRunner() {
		return runner;
	}
	
	public void initSimulation(String algorithm) {
		this.algorithm = algorithm;
		getType();
		switch (type) {
		case Schelling:
			initSchelling();
			break;
		case WaTor:
			break;
		case SpreadingFire:
			initFire();
			break;
		case Life:
			break;
		}
		stage.setTitle(algorithm);
		initSimulationScene();
	}

	private void initFire() {
		param = new SFParameters();
		param.setRows(20);// TODO read the numRows and numCols from XML
		param.setCols(20);
		((SFParameters) param).setProbCatch(0.6);
		builder = new SpreadingFireBuilder(param);
		runner = builder.init();
	}

	private void initSchelling() {
		param = new SLParameters();
		param.setRows(20);
		param.setCols(20);
		((SLParameters) param).setEmptyRatio(0.2);
		((SLParameters) param).setRatio(1.5);
		builder = new SchellingBuilder(param);
		runner = builder.init();
	}
	
	private void initSimulationScene() {
		switch (type) {
		case Schelling:
			controls = new SchellingControls(this);
			break;
		default:
			controls = new Controls(this);
			break;
		}
		scn = new SimulationScene(builder.getSimulationPane(), controls);
		stage.setScene(scn.initScene());
	}
	
	private void getType() {
		if (algorithm.equals(SEGREGATION)) {
			type = AlgorithmType.Schelling;
		}
		else if (algorithm.equals(PRED_PREY)) {
			type = AlgorithmType.WaTor;
		}
		else if (algorithm.equals(FIRE)) {
			type = AlgorithmType.SpreadingFire;
		}
		else if (algorithm.equals(LIFE)) {
			type = AlgorithmType.Life;
		}
		else {
			ErrorPop error = new ErrorPop(300, 200, "Simulation Initializing Error");
			error.popup();
		}
	}
}
