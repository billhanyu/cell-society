package ui;

import java.util.ResourceBundle;

import init.Initializer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StartScene extends ProgScene {
	
	private EventHandler<ActionEvent> exitAction;
	private Initializer initializer;
	private ResourceBundle myResource;

	public StartScene(EventHandler<ActionEvent> exitAction, Initializer initializer) {
		super();
		this.exitAction = exitAction;
		this.initializer = initializer;
	}

	public Scene initScene(int size, ResourceBundle myResource) {
		this.myResource = myResource;
		Button exitButton = initExitButton();
		Button chooseFile = initFileButton();
		VBox box = new VBox();
		Label title = initTitle();
		box.getChildren().addAll(title, chooseFile, exitButton);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(100);
		BorderPane root = new BorderPane();
		root.setCenter(box);
		return new Scene(root, width, height);
	}

	private Button initFileButton() {
		Button chooseFile = new Button(this.myResource.getString("ChooseFile"));
		chooseFile.setOnAction(e ->{
			initializer.initSimulationFromFile();
		});
		return chooseFile;
	}

	private Label initTitle() {
		Label lbl = new Label(this.myResource.getString("Title"));
		lbl.setFont(new Font(20));
		return lbl;
	}

	private Button initExitButton() {
		Button exit = new Button(this.myResource.getString("Exit"));
		exit.setPrefWidth(100);
		exit.setOnAction(exitAction);
		return exit;
	}
}
