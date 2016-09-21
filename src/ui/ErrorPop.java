package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ErrorPop {
	
	private double width;
	private double height;
	private String description;
	private Stage s;

	public ErrorPop(double width, double height, String description) {
		this.width = width;
		this.height = height;
		this.description = description;
	}
	
	public void popup() {
		s = new Stage();
		s.setTitle("Cell Society");
		Scene scn = initScene();
		s.setScene(scn);
		s.show();
	}
	
	private Scene initScene() {
		Label lbl = new Label("Error!");
		lbl.setFont(new Font(20));
		Label des = new Label(description);
		Button btn = new Button("OK");
		btn.setOnAction(new Close());
		VBox box = new VBox();
		box.setSpacing(15);
		box.getChildren().addAll(lbl, des, btn);
		box.setAlignment(Pos.CENTER);
		Scene scn = new Scene(box, width, height);
		return scn;
	}
	
	class Close implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			s.close();
		}
	}
}
