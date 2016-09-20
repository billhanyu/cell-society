package ui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ErrorScene extends ProgScene {
	
	private String description;

	public ErrorScene(double width, double height) {
		super(width, height);
	}
	
	public void setDescription(String s) {
		description = s;
	}

	@Override
	public Scene initScene() {
		BorderPane root = new BorderPane();
		VBox box = new VBox();
		Text error = new Text("Error");
		Text des = new Text(description);
		error.setFont(new Font(20));
		des.setFont(new Font(20));
		box.getChildren().addAll(error, des);
		root.setCenter(box);
		Scene errorScn = new Scene(root, width, height);
		return errorScn;
	}

}
