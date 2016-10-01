package ui;

import java.util.ResourceBundle;

import init.Initializer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StartScene extends ProgScene {
	
	private EventHandler<ActionEvent> exitAction;
	private Initializer initializer;
	private ResourceBundle myResource;
	
	private static final int BUTTON_WIDTH = 100;

	public StartScene(EventHandler<ActionEvent> exitAction, Initializer initializer) {
		super();
		this.exitAction = exitAction;
		this.initializer = initializer;
	}

	public Scene initScene(int size, ResourceBundle myResource) {
		this.myResource = myResource;
		Button exitButton = initExitButton();
		Button chooseFile = initFileButton();
		HBox languageSelection = initLanguageSelectionBox();
		
		VBox box = new VBox();
		Label title = initTitle();
		box.getChildren().addAll(title, chooseFile, exitButton);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(50);
		BorderPane root = new BorderPane();
		root.setCenter(box);
		root.setBottom(languageSelection);
		BorderPane.setAlignment(languageSelection, Pos.BOTTOM_RIGHT);
		return new Scene(root, width, height);
	}

	private Button initFileButton() {
		Button chooseFile = new Button(this.myResource.getString("ChooseFile"));
		chooseFile.setPrefWidth(BUTTON_WIDTH);
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
		exit.setPrefWidth(BUTTON_WIDTH);
		exit.setOnAction(exitAction);
		return exit;
	}
	
	private HBox initLanguageSelectionBox() {
		HBox selections = new HBox();
		Button englishButton = makeButton(
				this.myResource.getString("English"),
				BUTTON_WIDTH,
				e-> {
					initializer.setCurrentLanguage(Initializer.ENGLISH_FILE);
					initializer.start();
				});
		Button chineseButton = makeButton(
				this.myResource.getString("Chinese"),
				BUTTON_WIDTH,
				e-> {
					initializer.setCurrentLanguage(Initializer.CHINESE_FILE);
					initializer.start();
				});
		selections.setSpacing(20);
		selections.getChildren().addAll(englishButton, chineseButton);
		selections.setPadding(new Insets(10, 10, 10, 10));
		return selections;
	}
	
	private Button makeButton(String text, double width, EventHandler<ActionEvent> handler) {
		Button btn = new Button(text);
		btn.setPrefWidth(width);
		btn.setOnAction(handler);
		return btn;
	}
}
