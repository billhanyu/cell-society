package global;

import SpreadingFire.SFParameters;
import SpreadingFire.SpreadingFireBuilder;
import SpreadingFire.SpreadingFireControls;
import WaTor.WTParameters;
import WaTor.WaTorBuilder;
import WaTor.WaTorControls;
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
import xml.*;

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
	
	private Decoder xmlParser;
	
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
        stage.setScene(start.initScene(0));
	}
	
	public void reset() {
		builder.reset();
		runner.updateCellGrid();
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
	        xmlParser = new Decoder();
	        this.algorithm = algorithm;
		getType();
		switch (type) {
		case Schelling:
			initSchelling();
			break;
		case WaTor:
			initWaTor();
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
	        SpreadingFireSimulationFactory fireSimulation = 
	                new SpreadingFireSimulationFactory(xmlParser.getRootElement("data/xml/SpreadingFire.xml"));
		param = fireSimulation.getSimulationParameters();
		builder = new SpreadingFireBuilder(param);
		runner = builder.init();
	}

	private void initSchelling() {
	        SchellingSimulationFactory schellingSimulation = 
	                new SchellingSimulationFactory(xmlParser.getRootElement("data/xml/Schelling.xml"));
	        param = schellingSimulation.getSimulationParameters();
		builder = new SchellingBuilder(param);
		runner = builder.init();
	}
	
	private void initWaTor() {
	        WaTorSimulationFactory waTorSimulation = 
	                new WaTorSimulationFactory(xmlParser.getRootElement("data/xml/WaTor.xml"));
		param = waTorSimulation.getSimulationParameters();
		builder = new WaTorBuilder(param);
		runner = builder.init();
	}
	
	private void initSimulationScene() {
		switch (type) {
		case Schelling:
			controls = new SchellingControls(this);
			break;
		case WaTor:
			controls = new WaTorControls(this);
			break;
		case SpreadingFire:
			controls = new SpreadingFireControls(this);
			break;
		default:
			controls = new Controls(this);
			break;
		}
		scn = new SimulationScene(builder.getSimulationPane(), controls);
		stage.setScene(scn.initScene(param.getRows(), param));
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
