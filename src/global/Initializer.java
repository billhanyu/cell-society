package global;

import java.io.File;
import java.util.ResourceBundle;

import SpreadingFire.SpreadingFireBuilder;
import SpreadingFire.SpreadingFireControls;
import WaTor.WaTorBuilder;
import WaTor.WaTorControls;
import gameOfLife.GameOfLifeBuilder;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import schelling.SchellingBuilder;
import schelling.SchellingControls;
import ui.Controls;
import ui.ErrorPop;
import ui.SimulationScene;
import ui.StartScene;
import xml.Decoder;
import xml.GameOfLifeSimulationFactory;
import xml.GeneralSimulationFactory;
import xml.SchellingSimulationFactory;
import xml.SimulationFactory;
import xml.SpreadingFireSimulationFactory;
import xml.WaTorSimulationFactory;
import xmlExceptions.InvalidSimulationTypeException;

public class Initializer {
	private Stage stage;
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 500;
	public static final String SEGREGATION = "Segregation";
	public static final String PRED_PREY = "Predator-Prey";
	public static final String FIRE = "Fire";
	public static final String LIFE = "GameOfLife";
	private static final String RESOURCE_PATH = "resource/";

	private Builder builder;
	private Parameters param;
	private Runner runner;
	private SimulationScene scn;
	private Controls controls;
	private File xmlFile;
	private Decoder xmlParser;
	private ResourceBundle myResources;
	private SimulationFactory mySimulation;

	class ExitAction implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			stage.close();
		}
	}

	public Initializer(Stage s) {
		stage = s;
	}

	public void start(){
		myResources = ResourceBundle.getBundle(RESOURCE_PATH + "GUItext");
		stage.setTitle(myResources.getString("Title"));
		StartScene start = new StartScene(new ExitAction(), this);
		stage.setScene(start.initScene(0, myResources));
		xmlParser = new Decoder();
	}

	public void reset() {
		builder.reset();
		runner.pause();
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

	/*
	 * This method is used to initialize the simulation from an arbitrary XML file
	 */
	public void initSimulationFromFile(){

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(myResources.getString("FileChooser"));
		xmlFile = fileChooser.showOpenDialog(stage);
		GeneralSimulationFactory generalSimulation = 
				new GeneralSimulationFactory(xmlParser.getRootElement(xmlFile.toString()));
		String simType = generalSimulation.getSimulationName();
		if (simType.equals(FIRE)){
		    mySimulation = new SpreadingFireSimulationFactory(xmlParser.getRootElement(xmlFile.toString()));
		    controls = new SpreadingFireControls(this, myResources);
		    param = mySimulation.getSimulationParameters();
		    builder = new SpreadingFireBuilder(param, myResources);
		}
		else if (simType.equals(LIFE)){
		    mySimulation = new GameOfLifeSimulationFactory(xmlParser.getRootElement(xmlFile.toString()));
		    controls = new Controls(this, myResources);
		    param = mySimulation.getSimulationParameters();
		    builder = new GameOfLifeBuilder(param, myResources);
		}
		else if (simType.equals(PRED_PREY)){
		    mySimulation = new WaTorSimulationFactory(xmlParser.getRootElement(xmlFile.toString()));
		    controls = new WaTorControls(this, myResources);
		    param = mySimulation.getSimulationParameters();
		    builder = new WaTorBuilder(param, myResources);
		}
		else if (simType.equals(SEGREGATION)){
		    mySimulation = new SchellingSimulationFactory(xmlParser.getRootElement(xmlFile.toString()));
		    controls = new SchellingControls(this, myResources);
		    param = mySimulation.getSimulationParameters();
		    builder = new SchellingBuilder(param, myResources);
		}
		
		
		runner = builder.init();
		scn = new SimulationScene(builder.getSimulationPane(), controls);
                stage.setScene(scn.initScene(param.getRows(), param));
		stage.setTitle(myResources.getString(simType));
	}
	
}
