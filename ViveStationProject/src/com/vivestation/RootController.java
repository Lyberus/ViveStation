package com.vivestation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class RootController implements Initializable {

	//MenuBar
	@FXML
	public ImageView IV_Logo;
	@FXML
	public ImageView IV_File;
	@FXML
	public ImageView IV_FileHovered;
	
	
	public MenuBarController contMenuBar;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		contMenuBar = new MenuBarController(this);
	}
	
	public void finishProgram() {
		Platform.exit();
	}

}
