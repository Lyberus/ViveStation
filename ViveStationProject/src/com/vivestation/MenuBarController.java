package com.vivestation;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MenuBarController {

	public Initializable root;
	
	@FXML
	private ImageView IV_Logo;
	
	public MenuBarController(Initializable root) {
		this.root = root;
	}
	
	public void initializable(URL location, ResourceBundle resources) {
		IV_Logo.setOnMouseClicked(event -> FinishProgram(event));
	}
	
	public void FinishProgram(MouseEvent event) {
		Platform.exit();
	}
	
}
