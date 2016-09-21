package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StartScene extends ProgScene {
	
	private ComboBox<String> comboBox;
	private String algorithm;
	private String goString = "Go";
	private EventHandler<ActionEvent> exitAction;

	public StartScene(double width, double height) {
		super(width, height);
	}

	@Override
	public Scene initScene() {
		HBox selectionBox = initSelectionBox();
		Button exitButton = initExitButton();
		VBox box = new VBox();
		Label title = initTitle();
		box.getChildren().addAll(title, selectionBox, exitButton);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(100);
		BorderPane root = new BorderPane();
		root.setCenter(box);
		return new Scene(root, width, height);
	}
	
	private ComboBox<String> initComboBox() {
		ObservableList<String> options = 
				FXCollections.observableArrayList(
						"Segregation",
						"Predator-Prey",
						"Fire",
						"Game of Life"
						);
		comboBox = new ComboBox<String>(options);
		comboBox.setPromptText("Select a Simulation");
		comboBox.setPrefWidth(200);
		comboBox.setOnAction((e) -> {
		    algorithm =  comboBox.getSelectionModel().getSelectedItem().toString();    
		});
		return comboBox;
	}

	//drop down and go button
	private HBox initSelectionBox() {
		ComboBox<String> selection = initComboBox();
		Button goButton = initGoButton();
		HBox box = new HBox();
		box.getChildren().addAll(selection, goButton);
		box.setAlignment(Pos.CENTER);
		return box;
	}
	
	private Button initGoButton() {
		Button go = new Button(goString);
		go.setOnAction(e->{
			if (algorithm == null) {
				ErrorPop pop = new ErrorPop(300, 200, "Please select a simulation");
				pop.popup();
				return;
			}
			System.out.println(algorithm);
		});
		return go;
	}
	
	private Label initTitle() {
		Label lbl = new Label("Cell Society");
		lbl.setFont(new Font(20));
		return lbl;
	}

	private Button initExitButton() {
		Button exit = new Button("Exit");
		exit.setPrefWidth(100);
		exit.setOnAction(exitAction);
		return exit;
	}
}
