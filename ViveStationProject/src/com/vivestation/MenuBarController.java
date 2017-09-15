package com.vivestation;

import java.io.FileInputStream;

import com.vivestation.menubar.ContextMenuFile;

import javafx.scene.image.Image;
import javafx.stage.Screen;

public class MenuBarController {

	public RootController root;
	
	public ContextMenuFile CM_File;
	
	private double mousex;
	private double mousey;
	final private double WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
	final private double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
	private double screenwidth;
	private double screenheight;
	
	
	public MenuBarController(RootController root) {
		this.root = root;
		
		CM_File = new ContextMenuFile(root);
		
		init();
	}
	
	public void init() {
		
		root.IV_File.setOnMouseClicked(event -> {
			if (root.getPointAlpha(root.IV_File, event) > 0) {
				if(!CM_File.isShowing()) {
					CM_File.show(root.IV_File,
							root.IV_File.getLayoutX() + ViveStation.stage.getX(),
							root.IV_File.getLayoutY() + root.IV_File.getFitHeight() + ViveStation.stage.getY());
				}
			}
		});
		root.setOnMouseHovered(root.IV_File, "./resources/File.png", "./resources/FileHovered.png");
		
		
		root.IV_CloseButton.setOnMouseClicked(event -> root.finishProgram());
		
		root.IV_WindowSizeButton.setOnMouseClicked(event -> {
			if (ViveStation.stage.isMaximized()) {
				ViveStation.stage.setMaximized(false);
			}
			else {
				ViveStation.stage.setMaximized(true);
			}
		});
		
		root.IV_MiniButton.setOnMouseClicked(event -> root.minimizeProgram());

		
		root.IV_Logo.setOnMousePressed(event -> {
			mousex = event.getScreenX() - ViveStation.stage.getX();
			mousey = event.getScreenY() - ViveStation.stage.getY();
			screenwidth = ViveStation.stage.getWidth();
			screenheight = ViveStation.stage.getHeight();
		});
		root.IV_Logo.setOnMouseDragged(event -> {
			ViveStation.stage.setX(event.getScreenX() - mousex);
			ViveStation.stage.setY(event.getScreenY() - mousey);
			if(event.getScreenX()<WIDTH/40) {
				if(event.getScreenY()<HEIGHT/30) {
					ViveStation.stage.setX(0);
					ViveStation.stage.setY(0);
					ViveStation.stage.setWidth(WIDTH/2);
					ViveStation.stage.setHeight(HEIGHT/2);
				} else if(event.getScreenY()>HEIGHT-HEIGHT/30) {
					ViveStation.stage.setX(0);
					ViveStation.stage.setY(HEIGHT/2);
					ViveStation.stage.setWidth(WIDTH/2);
					ViveStation.stage.setHeight(HEIGHT/2);
				} else {
					ViveStation.stage.setX(0);
					ViveStation.stage.setY(0);
					ViveStation.stage.setWidth(WIDTH/2);
					ViveStation.stage.setHeight(HEIGHT);
				}
			} else if(event.getScreenX()>WIDTH-WIDTH/40) {
				if(event.getScreenY()<HEIGHT/30) {
					ViveStation.stage.setX(WIDTH/2);
					ViveStation.stage.setY(0);
					ViveStation.stage.setWidth(WIDTH/2);
					ViveStation.stage.setHeight(HEIGHT/2);
				} else if(event.getScreenY()>HEIGHT-HEIGHT/30) {
					ViveStation.stage.setX(WIDTH/2);
					ViveStation.stage.setY(HEIGHT/2);
					ViveStation.stage.setWidth(WIDTH/2);
					ViveStation.stage.setHeight(HEIGHT/2);
				} else {
					ViveStation.stage.setX(WIDTH/2);
					ViveStation.stage.setY(0);
					ViveStation.stage.setWidth(WIDTH/2);
					ViveStation.stage.setHeight(HEIGHT);
				}
			} else if(event.getScreenY()<HEIGHT/30) {
				ViveStation.stage.setX(0);
				ViveStation.stage.setY(0);
				ViveStation.stage.setWidth(WIDTH);
				ViveStation.stage.setHeight(HEIGHT);
			} else {
				ViveStation.stage.setWidth(screenwidth);
				ViveStation.stage.setHeight(screenheight);
				screenwidth = ViveStation.stage.getWidth();
				screenheight = ViveStation.stage.getHeight();
			}
		});
	}
	
}
