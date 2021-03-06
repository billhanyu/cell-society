import javax.xml.parsers.ParserConfigurationException;

import init.Initializer;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the main program.
 * 
 * @author Bill Yu
 */
public class Main extends Application {
	private Stage stage;
	private Initializer initializer;
	/**
	 * Set things up at the beginning.
	 */
	@Override
	public void start (Stage s) {
		this.stage = s;
		stage.show();

		initializer = new Initializer(stage);
		initializer.start();
	}

	/**
	 * Start the program.
	 *      * 
	 *  * @throws TransformerException 
	 * @throws ParserConfigurationException 
	 */
	public static void main (String[] args) {
		launch(args);
	}
}