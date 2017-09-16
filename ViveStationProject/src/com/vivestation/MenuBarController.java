package com.vivestation;

import java.io.FileInputStream;

import com.vivestation.menubar.ContextMenuFile;
import com.vivestation.menubar.ContextMenuFilter;
import com.vivestation.menubar.ContextMenuWindow;
import com.vivestation.menubar.ContextMenuEdit;

import javafx.scene.image.Image;
import javafx.stage.Screen;

public class MenuBarController {

	public RootController root;
	
	public ContextMenuFile CM_File;
	public ContextMenuEdit CM_Edit;
	public ContextMenuFilter CM_Filter;
	public ContextMenuWindow CM_Window;
	
	public Image IM_File = new Image(getClass().getResourceAsStream("./resources/File.png"));
	public Image IM_FileHovered = new Image(getClass().getResourceAsStream("./resources/FileHovered.png"));
	public Image IM_Edit = new Image(getClass().getResourceAsStream("./resources/Edit.png"));
	public Image IM_EditHovered = new Image(getClass().getResourceAsStream("./resources/EditHovered.png"));
	public Image IM_Filter = new Image(getClass().getResourceAsStream("./resources/Filter.png"));
	public Image IM_FilterHovered = new Image(getClass().getResourceAsStream("./resources/FilterHovered.png"));
	public Image IM_Window = new Image(getClass().getResourceAsStream("./resources/Window.png"));
	public Image IM_WindowHovered = new Image(getClass().getResourceAsStream("./resources/WindowHovered.png"));
	
	public Image IM_SmallWindowBar = new Image(getClass().getResourceAsStream("./resources/SmallWindowBar.png"));
	public Image IM_SmallWindowBarHovered = 
			new Image(getClass().getResourceAsStream("./resources/SmallWindowBarHovered.png"));
	public Image IM_BigWindowBar = new Image(getClass().getResourceAsStream("./resources/BigWindowBar.png"));
	public Image IM_BigWindowBarHovered = 
			new Image(getClass().getResourceAsStream("./resources/BigWindowBarHovered.png"));
	
	
	private double mousex;
	private double mousey;
	final private double WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
	final private double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
	private double screenwidth;
	private double screenheight;
	
	
	public MenuBarController(RootController root) {
		this.root = root;
		
		CM_File = new ContextMenuFile(root);
		CM_Edit = new ContextMenuEdit(root);
		CM_Filter = new ContextMenuFilter(root);
		CM_Window = new ContextMenuWindow(root);
		
		init();
	}
	
	public void init() {
		
		root.IV_File.setOnMouseClicked(event -> root.showContextMenu(CM_File, root.IV_File));
		root.setOnMouseHovered(root.IV_File, IM_File, IM_FileHovered, CM_File, CM_Edit, CM_Filter, CM_Window);
		root.IV_Edit.setOnMouseClicked(event -> root.showContextMenu(CM_Edit, root.IV_Edit));
		root.setOnMouseHovered(root.IV_Edit, IM_Edit, IM_EditHovered, CM_Edit, CM_File, CM_Filter, CM_Window);
		root.IV_Filter.setOnMouseClicked(event -> root.showContextMenu(CM_Filter, root.IV_Filter));
		root.setOnMouseHovered(root.IV_Filter, IM_Filter, IM_FilterHovered, CM_Filter, CM_File, CM_Edit, CM_Window);
		root.IV_Window.setOnMouseClicked(event -> root.showContextMenu(CM_Window, root.IV_Window));
		root.setOnMouseHovered(root.IV_Window, IM_Window, IM_WindowHovered, CM_Window, CM_File, CM_Edit, CM_Filter);
		
		
		root.IV_CloseButton.setOnMouseClicked(event -> root.finishProgram());
		
		root.IV_WindowSizeButton.setOnMouseClicked(event -> {
			if (ViveStation.stage.isMaximized()) {
				ViveStation.stage.setMaximized(false);
				WindowSizeButtonHover();
			}
			else {
				ViveStation.stage.setMaximized(true);
				WindowSizeButtonHover();
			}
		});
		WindowSizeButtonHover();
		if(ViveStation.stage.isMaximized()) {
			root.IV_WindowSizeButton.setImage(IM_BigWindowBar);
		} else {
			root.IV_WindowSizeButton.setImage(IM_SmallWindowBar);
		}
		
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
					ViveStation.stage.setMaximized(false);
				} else if(event.getScreenY()>HEIGHT-HEIGHT/30) {
					ViveStation.stage.setX(0);
					ViveStation.stage.setY(HEIGHT/2);
					ViveStation.stage.setWidth(WIDTH/2);
					ViveStation.stage.setHeight(HEIGHT/2);
					ViveStation.stage.setMaximized(false);
				} else {
					ViveStation.stage.setX(0);
					ViveStation.stage.setY(0);
					ViveStation.stage.setWidth(WIDTH/2);
					ViveStation.stage.setHeight(HEIGHT);
					ViveStation.stage.setMaximized(false);
				}
			} else if(event.getScreenX()>WIDTH-WIDTH/40) {
				if(event.getScreenY()<HEIGHT/30) {
					ViveStation.stage.setX(WIDTH/2);
					ViveStation.stage.setY(0);
					ViveStation.stage.setWidth(WIDTH/2);
					ViveStation.stage.setHeight(HEIGHT/2);
					ViveStation.stage.setMaximized(false);
				} else if(event.getScreenY()>HEIGHT-HEIGHT/30) {
					ViveStation.stage.setX(WIDTH/2);
					ViveStation.stage.setY(HEIGHT/2);
					ViveStation.stage.setWidth(WIDTH/2);
					ViveStation.stage.setHeight(HEIGHT/2);
					ViveStation.stage.setMaximized(false);
				} else {
					ViveStation.stage.setX(WIDTH/2);
					ViveStation.stage.setY(0);
					ViveStation.stage.setWidth(WIDTH/2);
					ViveStation.stage.setHeight(HEIGHT);
					ViveStation.stage.setMaximized(false);
				}
			} else if(event.getScreenY()<HEIGHT/30) {
				ViveStation.stage.setX(0);
				ViveStation.stage.setY(0);
				ViveStation.stage.setWidth(WIDTH);
				ViveStation.stage.setHeight(HEIGHT);
				ViveStation.stage.setMaximized(true);
			} else {
				ViveStation.stage.setWidth(screenwidth);
				ViveStation.stage.setHeight(screenheight);
				screenwidth = ViveStation.stage.getWidth();
				screenheight = ViveStation.stage.getHeight();
				ViveStation.stage.setMaximized(false);
			}
		});
		root.IV_Logo.setOnMouseReleased(event -> {
			if(ViveStation.stage.getX()==0
					&&ViveStation.stage.getY()==0
					&&ViveStation.stage.getWidth()==WIDTH
					&&ViveStation.stage.getHeight()==HEIGHT) {
				ViveStation.stage.setMaximized(true);
				WindowSizeButtonHover();
				root.IV_WindowSizeButton.setImage(IM_BigWindowBar);
			} else {
				ViveStation.stage.setMaximized(false);
				WindowSizeButtonHover();
				root.IV_WindowSizeButton.setImage(IM_SmallWindowBar);
			}
		});
	}
	
	public void WindowSizeButtonHover(){
		root.setOnMouseHovered(root.IV_WindowSizeButton, IM_BigWindowBar, IM_BigWindowBarHovered,
				IM_SmallWindowBar, IM_SmallWindowBarHovered, ViveStation.stage.isMaximized());
	}
	
}
