

import global.Initializer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import ui.StartScene;

/**
 * This is the main program.
 * 
 * @author Bill Yu
 */

public class Main extends Application {
    private Stage stage;
    
    private Initializer initializer;
    
    class ExitAction implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			stage.close();
		}
    }

    /**
     * Set things up at the beginning.
     */
    @Override
    public void start (Stage s) {
    	this.stage = s;
        stage.setTitle("Cell Society");
        stage.show();
        
        initializer = new Initializer(stage);
        
        StartScene start = new StartScene(new ExitAction(), initializer);
        stage.setScene(start.initScene());
    }
    
	/**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
