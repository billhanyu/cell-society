    package global;
    
    import java.io.File;
import java.util.ResourceBundle;
import SpreadingFire.SpreadingFireBuilder;
    import SpreadingFire.SpreadingFireControls;
    import WaTor.WaTorBuilder;
    import WaTor.WaTorControls;
    import gameOfLife.GLParameters;
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
    import xml.*;
    
    public class Initializer {
    	private Stage stage;
    	public static final int SCENE_WIDTH = 800;
    	public static final int SCENE_HEIGHT = 500;
    	public static final String SEGREGATION = "Segregation";
    	public static final String PRED_PREY = "Predator-Prey";
    	public static final String FIRE = "Fire";
    	public static final String LIFE = "Game of Life";
    	private static final String RESOURCE_PATH = "resource/";
    
    	private Builder builder;
    	private Parameters param;
    	private Runner runner;
    	private String algorithm;
    	private AlgorithmType type;
    	private SimulationScene scn;
    	private Controls controls;
    	private File xmlFile;
    	private Decoder xmlParser;
    	private ResourceBundle myResources;
    
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
              GeneralSimulationFactory test = 
                      new GeneralSimulationFactory(xmlParser.getRootElement(xmlFile.toString()));
              String simType = test.getSimulationName();
              switch (simType){
                  case "Schelling":
                      initSchelling(xmlFile.toString());
                      this.algorithm = SEGREGATION;
                      stage.setTitle(myResources.getString(SEGREGATION));
                      break;
                  case "WaTor":
                      initWaTor(xmlFile.toString());
                      this.algorithm = PRED_PREY;
                      stage.setTitle(myResources.getString(PRED_PREY));
                      break;
                  case "GameOfLife":
                      initLife(xmlFile.toString());
                      this.algorithm = LIFE;
                      stage.setTitle(myResources.getString("GameOfLife"));
                      break;
                  case "Spreading Fire":
                      initFire(xmlFile.toString());
                      this.algorithm = FIRE;
                      stage.setTitle(myResources.getString(FIRE));
                      break;
              }
              getType();
              initSimulationScene();
    	}
    
    	/*
    	 * This method is used to pick a default simulation from the combobox
    	 */
    	public void initSimulation(String algorithm) {
    		this.algorithm = algorithm;
    		getType();
    		switch (type) { // these are default test files
    		case Schelling:
    			initSchelling("data/xml/Schelling.xml");
    			stage.setTitle(myResources.getString(SEGREGATION));
    			break;
    		case WaTor:
    			initWaTor("data/xml/WaTor.xml");
    			stage.setTitle(myResources.getString(PRED_PREY));
    			break;
    		case SpreadingFire:
    			initFire("data/xml/SpreadingFire.xml");
    			stage.setTitle(myResources.getString(FIRE));
    			break;
    		case Life:
    			initLife("data/xml/GameOfLifePleaseWork.xml");
    			stage.setTitle(myResources.getString("GameOfLife"));
    			break;
    		}
    		initSimulationScene();
    	}
    
    	private void initFire(String file) {
    		SpreadingFireSimulationFactory fireSimulation = 
    				new SpreadingFireSimulationFactory(xmlParser.getRootElement(file));
    		param = fireSimulation.getSimulationParameters();
    		builder = new SpreadingFireBuilder(param, myResources);
    		runner = builder.init();
    	}
    
    	private void initSchelling(String file) {
    		SchellingSimulationFactory schellingSimulation = 
    				new SchellingSimulationFactory(xmlParser.getRootElement(file));
    		param = schellingSimulation.getSimulationParameters();
    		builder = new SchellingBuilder(param, myResources);
    		runner = builder.init();
    	}
    
    	private void initWaTor(String file) {
    		WaTorSimulationFactory waTorSimulation = 
    				new WaTorSimulationFactory(xmlParser.getRootElement(file));
    		param = waTorSimulation.getSimulationParameters();
    		builder = new WaTorBuilder(param, myResources);
    		runner = builder.init();
    	}
    
    	private void initLife(String file) {
    		GameOfLifeSimulationFactory gameSimulation = 
    		        new GameOfLifeSimulationFactory(xmlParser.getRootElement(file));
    		param = gameSimulation.getSimulationParameters();
    		builder = new GameOfLifeBuilder(param, myResources);
    		runner = builder.init();
    	}
    
    	private void initSimulationScene() {
    		switch (type) {
    		case Schelling:
    			controls = new SchellingControls(this, myResources);
    			break;
    		case WaTor:
    			controls = new WaTorControls(this, myResources);
    			break;
    		case SpreadingFire:
    			controls = new SpreadingFireControls(this, myResources);
    			break;
    		case Life:
    			//break;
    		default:
    			controls = new Controls(this, myResources);
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
    			ErrorPop error = new ErrorPop(300, 200, myResources.getString("ErrorMessage"), myResources);
    			error.popup();
    		}
    	}
    }
