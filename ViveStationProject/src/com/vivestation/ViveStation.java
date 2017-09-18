package com.vivestation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViveStation extends Application {

	static public Stage stage;
	
	public double WIDTH;
	public double HEIGHT;
	public double SCALE;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		ViveStation.stage = stage;
		stage.setTitle("Vive Station 0.0");
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setMinHeight(300);
		stage.setMinWidth(533);
		stage.setMaximized(false);
		
		Parent root;
		
		root = FXMLLoader.load(getClass().getResource("fxmls/FHD.fxml"));
		WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
		HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
		SCALE = (WIDTH / 1920.0 > HEIGHT / 1080.0) ? WIDTH / 1920.0 : HEIGHT / 1080.0; 
		
		Scale scale = new Scale();
		scale.setX(SCALE);
		scale.setY(SCALE);
		scale.setPivotX(0);
		scale.setPivotY(0);
		root.getTransforms().add(scale);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
		scene.getStylesheets().add(getClass().getResource("./csses/root.css").toString());
        stage.setScene(scene);
        stage.show();
	}

}
