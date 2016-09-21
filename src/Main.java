

import grid.Runner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.StartScene;

/**
 * This is the main program.
 * 
 * @author Bill Yu
 */

public class Main extends Application {
    private static final int SIZE = 680;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Stage stage;
    private KeyFrame frame;
    private Timeline animation;
    
    private Runner runner;

    /**
     * Set things up at the beginning.
     */
    @Override
    public void start (Stage s) {
    	this.stage = s;
        stage.setTitle("Cell Society");
        stage.show();

        // sets the program loop
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> this.step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
        
        stage.setScene(new StartScene(SIZE, SIZE).initScene());
    }

    public void step (double elapsedTime) {
    	
    }

	/**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
