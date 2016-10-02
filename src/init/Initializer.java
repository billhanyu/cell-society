package init;

import java.io.File;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import SpreadingFire.SpreadingFireBuilder;
import SpreadingFire.SpreadingFireControls;
import Sugarscape.SugarscapeBuilder;
import WaTor.WaTorBuilder;
import WaTor.WaTorControls;
import ants.AntBuilder;
import gameOfLife.GameOfLifeBuilder;
import grid.Builder;
import grid.Parameters;
import grid.Runner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import langton.LangtonBuilder;
import langton.LangtonControls;
import schelling.SchellingBuilder;
import schelling.SchellingControls;
import ui.Controls;
import ui.ErrorPop;
import ui.SimulationScene;
import ui.StartScene;
import xml.AntSimulationFactory;
import xml.Decoder;
import xml.GameOfLifeSimulationFactory;
import xml.GeneralSimulationFactory;
import xml.LangtonSimulationFactory;
import xml.SchellingSimulationFactory;
import xml.SimulationFactory;
import xml.SpreadingFireSimulationFactory;
import xml.SugarSimulationFactory;
import xml.WaTorSimulationFactory;
import xmlExceptions.InvalidXMLFileException;

public class Initializer {
    private Stage stage;
    public static final int SCENE_WIDTH = 800;
    public static final int SCENE_HEIGHT = 500;

    public static final String SEGREGATION = "Segregation";
    public static final String PRED_PREY = "Predator-Prey";
    public static final String FIRE = "Fire";
    public static final String LIFE = "GameOfLife";
    public static final String LANGTON = "Langton";
    public static final String ANT = "Ant";
    public static final String SUGARSCAPE = "Sugarscape";

    private static final String RESOURCE_PATH = "resource/";
    public static final String ENGLISH_FILE = "GUItext-English";
    public static final String CHINESE_FILE = "GUItext-Chinese";
    private String currentLanguage = ENGLISH_FILE;

    private Builder builder;
    private Parameters param;
    private Runner runner;
    private SimulationScene scn;
    private Controls controls;
    private File xmlFile;
    private FileChooser fileChooser;
    private Decoder xmlParser;
    private ResourceBundle myResources;
    private SimulationFactory mySimulation;
    private String simType;

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
        myResources = ResourceBundle.getBundle(RESOURCE_PATH + currentLanguage);
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

    /**
     * This method will control the saving of the XML File
     * @throws ParserConfigurationException 
     */
    public void saveFile() throws ParserConfigurationException{
        File xmlFile = fileChooser.showSaveDialog(stage);
        //    XMLSaveFile saveFile = new XMLSaveFile(param, runner, simType);
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

    public String getCurrentLanguage() {
        return this.currentLanguage;
    }

    public void setCurrentLanguage(String languageFileName) {
        this.currentLanguage = languageFileName;
    }

    /*
     * This method is used to initialize the simulation from an arbitrary XML file
     */
    public void initSimulationFromFile(){
        try{
            chooseFile();
            GeneralSimulationFactory generalSimulation = 
                    new GeneralSimulationFactory(xmlParser.getRootElement(xmlFile.toString()));
            simType = generalSimulation.getSimulationName();
            initWithType(simType);
            runner = builder.init();
            scn = new SimulationScene(builder.getSimulationPane(), controls);
            stage.setScene(scn.initScene(param.getRows(), param));
            stage.setTitle(myResources.getString(simType));
        } catch (InvalidXMLFileException e){
            ErrorPop fileError = new ErrorPop(300, 200, myResources.getString("FileChooserError"), myResources);
            fileError.popup();
        } catch (NullPointerException e) {
            ErrorPop pop = new ErrorPop(300, 200, myResources.getString("SimTypeError"), myResources);
            pop.popup();
            e.printStackTrace();
        }
    }

    private void initWithType(String simType) {
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
        else if (simType.equals(LANGTON)) {
            mySimulation = new LangtonSimulationFactory(xmlParser.getRootElement(xmlFile.toString()));
            controls = new LangtonControls(this, myResources);
            param = mySimulation.getSimulationParameters();
            builder = new LangtonBuilder(param, myResources);
        }
        else if (simType.equals(ANT)){
            mySimulation = new AntSimulationFactory(xmlParser.getRootElement(xmlFile.toString()));
            controls = new Controls(this, myResources);
            param = mySimulation.getSimulationParameters();
            builder = new AntBuilder(param, myResources);
        }
        else if (simType.equals(SUGARSCAPE)) {
            mySimulation = new SugarSimulationFactory(xmlParser.getRootElement(xmlFile.toString()));
            controls = new Controls(this, myResources);
            param = mySimulation.getSimulationParameters();
            builder = new SugarscapeBuilder(param, myResources);
        }
    }

    private void chooseFile() throws InvalidXMLFileException{
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                                                 new ExtensionFilter("xml files (*.xml)", "*.xml"),
                                                 new ExtensionFilter("All files (*.)", "*."));
        fileChooser.setTitle(myResources.getString("FileChooser"));
        xmlFile = fileChooser.showOpenDialog(null);
        if (xmlFile == null){
            throw new InvalidXMLFileException();
        }
        else{
            String fileExtension = xmlFile.toString().substring(xmlFile.toString().lastIndexOf("."));
            if (!fileExtension.equals(".xml")){
                throw new InvalidXMLFileException();
            }
        }

    }

}