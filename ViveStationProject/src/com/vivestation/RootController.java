package com.vivestation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RootController implements Initializable {

	//MenuBar
	@FXML
	public ImageView IV_MainLogo;
	@FXML
	public ImageView IV_FileMenu;
	@FXML
	public ImageView IV_EditMenu;
	@FXML
	public ImageView IV_FilterMenu;
	@FXML
	public ImageView IV_WindowMenu;
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
	
	
	//For ContextMenu
	public void setOnMouseHovered(ImageView target, Image normal, Image hovered,
			ContextMenu currentMenu, ContextMenu... contextMenus) {
		target.setOnMouseMoved(event -> {
				target.setImage(hovered);
				for(ContextMenu contextMenu : contextMenus) {
					if(contextMenu.isShowing())
					{
						contextMenu.hide();
						showContextMenu(currentMenu ,target);
					}
				}
		});
		target.setOnMouseExited(event -> {
			if(!currentMenu.isShowing()) {
			target.setImage(normal);
			}
		});
	}
	//For Normal
	public void setOnMouseHovered(ImageView target, Image normal, Image hovered) {
		target.setOnMouseMoved(event -> {
				target.setImage(hovered);
		});
		target.setOnMouseExited(event -> {
			target.setImage(normal);
		});
	}
	//For if
	public void setOnMouseHovered(ImageView target, Image normal1,
			Image hovered1, Image normal2, Image hovered2, Boolean bool) {
		if(bool) {
			target.setOnMouseMoved(event -> {
				target.setImage(hovered1);
			});
			target.setOnMouseExited(event -> {
				target.setImage(normal1);
			});
		} else {
			target.setOnMouseMoved(event -> {
				target.setImage(hovered2);
			});
			target.setOnMouseExited(event -> {
				target.setImage(normal2);
			});
		}
	}
	
	
	public void showContextMenu(ContextMenu target, ImageView image) {
		if(!target.isShowing()) {
			target.show(image,
					image.getLayoutX() + ViveStation.stage.getX(),
					image.getLayoutY() + image.getFitHeight() + ViveStation.stage.getY());
		}
	}
	
}
