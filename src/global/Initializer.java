    package global;
    
    import java.io.File;
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
    
    	private Builder builder;
    	private Parameters param;
    	private Runner runner;
    	private String algorithm;
    	private AlgorithmType type;
    	private SimulationScene scn;
    	private Controls controls;
    	private File xmlFile;
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
    		xmlParser = new Decoder();
    //	        FileChooser fileChooser = new FileChooser();
    //	        fileChooser.setTitle("Open Resource File");
    //	        File file = fileChooser.showOpenDialog(stage);
    //	        xmlParser = new Decoder();
    //	        GeneralSimulationFactory test = new GeneralSimulationFactory(xmlParser.getRootElement(file.toString()));
    //	        
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
              fileChooser.setTitle("Open Resource File");
              xmlFile = fileChooser.showOpenDialog(stage);
              System.out.println(xmlFile.toString());
              GeneralSimulationFactory test = 
                      new GeneralSimulationFactory(xmlParser.getRootElement(xmlFile.toString()));
              String simType = test.getSimulationName();
              System.out.println(simType);
              switch (simType){
                  case "Schelling":
                      initSchelling(xmlFile.toString());
                      this.algorithm = SEGREGATION;
                      break;
                  case "WaTor":
                      initWaTor(xmlFile.toString());
                      this.algorithm = PRED_PREY;
                      break;
                  case "GameOfLife":
                      initLife(xmlFile.toString());
                      this.algorithm = LIFE;
                      break;
                  case "Spreading Fire":
                      initFire(xmlFile.toString());
                      this.algorithm = FIRE;
                      break;
              }
              getType();
              stage.setTitle(simType);
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
    			break;
    		case WaTor:
    			initWaTor("data/xml/WaTor.xml");
    			break;
    		case SpreadingFire:
    			initFire("data/xml/SpreadingFire.xml");
    			break;
    		case Life:
    			initLife("data/xml/GameOfLifePleaseWork.xml");
    			break;
    		}
    		stage.setTitle(algorithm);
    		initSimulationScene();
    	}
    
    	private void initFire(String file) {
    		SpreadingFireSimulationFactory fireSimulation = 
    				new SpreadingFireSimulationFactory(xmlParser.getRootElement(file));
    		param = fireSimulation.getSimulationParameters();
    		builder = new SpreadingFireBuilder(param);
    		runner = builder.init();
    	}
    
    	private void initSchelling(String file) {
    		SchellingSimulationFactory schellingSimulation = 
    				new SchellingSimulationFactory(xmlParser.getRootElement(file));
    		param = schellingSimulation.getSimulationParameters();
    		builder = new SchellingBuilder(param);
    		runner = builder.init();
    	}
    
    	private void initWaTor(String file) {
    		WaTorSimulationFactory waTorSimulation = 
    				new WaTorSimulationFactory(xmlParser.getRootElement(file));
    		param = waTorSimulation.getSimulationParameters();
    		builder = new WaTorBuilder(param);
    		runner = builder.init();
    	}
    
    	private void initLife(String file) {
    		GameOfLifeSimulationFactory gameSimulation = 
    		        new GameOfLifeSimulationFactory(xmlParser.getRootElement(file));
    		param = gameSimulation.getSimulationParameters();
    		builder = new GameOfLifeBuilder(param);
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
    		case Life:
    			//break;
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
