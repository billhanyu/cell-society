package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class StartScene extends ProgScene {

	public StartScene(double width, double height) {
		super(width, height);
	}

	@Override
	public Scene initScene() {
		BorderPane root = new BorderPane();
		Button btn = new Button("Shit");
		root.setCenter(btn);
		Scene scn = new Scene(root, width, height);
		return scn;
	}
}
