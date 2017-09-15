package com.vivestation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RootController implements Initializable {

	//MenuBar
	@FXML
	public ImageView IV_Logo;
	@FXML
	public ImageView IV_File;
	@FXML
	public ImageView IV_CloseButton;
	@FXML
	public ImageView IV_WindowSizeButton;
	@FXML
	public ImageView IV_MiniButton;
	@FXML
	public AnchorPane AP_FHD;
	
	
	public MenuBarController contMenuBar;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		contMenuBar = new MenuBarController(this);
	}
	
	public void finishProgram() {
		ViveStation.stage.close();
	}
	
	public void minimizeProgram() {
		ViveStation.stage.setResizable(false);
		ViveStation.stage.setIconified(true);
	}

	public int getPointAlpha(ImageView target, MouseEvent event) {
		try {
			return ((target.getImage().getPixelReader().getArgb((int)(event.getSceneX() - target.getLayoutX()),
				(int)(event.getSceneY() - target.getLayoutY())) >> 24) & 0xFF);
		} catch (Exception e) {
			return -1;
		}
	}
	
	public void setOnMouseHovered(ImageView target, String normal, String hovered) {
		target.setOnMouseMoved(event -> {
			if (getPointAlpha(target, event) > 0) {
				target.setImage(new Image(getClass().getResourceAsStream(hovered)));
			}
			else {
				target.setImage(new Image(getClass().getResourceAsStream(normal)));
			}
		});
		target.setOnMouseExited(event -> {
			target.setImage(new Image(getClass().getResourceAsStream(normal)));
		});
	}
	
}
