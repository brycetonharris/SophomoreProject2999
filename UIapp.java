package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

public class UIapp extends Application{
	
	public void start(Stage mainstage) {
		mainstage.setTitle("");
        Button btn = new Button();
        btn.setText("'Chop Tree Button'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	System.out.println("whatever function goes after this");
            }});
            	
            	
           StackPane root = new StackPane();
           root.getChildren().add(btn);
           mainstage.setScene(new Scene(root, 1000, 700));
           mainstage.show();
            }

	public static void main(String[] args) {
			launch(args);
	}
	
	}
	

