package com.vivestation;

import java.io.FileInputStream;

import com.vivestation.menubar.ContextMenuFile;
import com.vivestation.menubar.ContextMenuFilter;
import com.vivestation.menubar.ContextMenuWindow;
import com.vivestation.menubar.ContextMenuEdit;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	
	public Stage tempstage = new Stage();
	private int shadowsize = 15;
	private int windowlocation = 0;
	
	
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
			windowlocation = 1;
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
			if(event.getScreenX()<WIDTH/80) {
				if(event.getScreenY()<HEIGHT/30) {
					if(!tempstage.isShowing())
					{
						tempWindow(WIDTH/2, HEIGHT/2, 0, 0);
					} else {
						tempstage.setX(0);
						tempstage.setY(0);
						tempstage.setWidth(WIDTH/2);
						tempstage.setHeight(HEIGHT/2);
					}
					windowlocation = 1;
				} else if(event.getScreenY()>HEIGHT-HEIGHT/30) {
					if(!tempstage.isShowing())
					{
						tempWindow(WIDTH/2, HEIGHT/2, 0, HEIGHT/2);
					} else {
						tempstage.setX(0);
						tempstage.setY(HEIGHT/2);
						tempstage.setWidth(WIDTH/2);
						tempstage.setHeight(HEIGHT/2);
					}
					windowlocation = 2;
				} else {
					if(!tempstage.isShowing())
					{
						tempWindow(WIDTH/2, HEIGHT, 0, 0);
					} else {
						tempstage.setX(0);
						tempstage.setY(0);
						tempstage.setWidth(WIDTH/2);
						tempstage.setHeight(HEIGHT);
					}
					windowlocation = 3;
				}
			} else if(event.getScreenX()>WIDTH-WIDTH/80) {
				if(event.getScreenY()<HEIGHT/30) {
					if(!tempstage.isShowing())
					{
						tempWindow(WIDTH/2, HEIGHT/2, WIDTH/2, 0);
					} else {
						tempstage.setX(WIDTH/2);
						tempstage.setY(0);
						tempstage.setWidth(WIDTH/2);
						tempstage.setHeight(HEIGHT/2);
					}
					windowlocation = 4;
				} else if(event.getScreenY()>HEIGHT-HEIGHT/30) {
					if(!tempstage.isShowing())
					{
						tempWindow(WIDTH/2, HEIGHT/2, WIDTH/2, HEIGHT/2);
					} else {
						tempstage.setX(WIDTH/2);
						tempstage.setY(HEIGHT/2);
						tempstage.setWidth(WIDTH/2);
						tempstage.setHeight(HEIGHT/2);
					}
					windowlocation = 5;
				} else {
					if(!tempstage.isShowing())
					{
						tempWindow(WIDTH/2, HEIGHT, WIDTH/2, 0);
					} else {
						tempstage.setX(WIDTH/2);
						tempstage.setY(0);
						tempstage.setWidth(WIDTH/2);
						tempstage.setHeight(HEIGHT);
					}
					windowlocation = 6;
				}
			} else if(event.getScreenY()<HEIGHT/30) {
				if(!tempstage.isShowing())
				{
					tempWindow(WIDTH, HEIGHT, 0, 0);
				} else {
					tempstage.setX(0);
					tempstage.setY(0);
					tempstage.setWidth(WIDTH);
					tempstage.setHeight(HEIGHT);
				}
				windowlocation = 7;
			} else {
				ViveStation.stage.setWidth(screenwidth);
				ViveStation.stage.setHeight(screenheight);
				screenwidth = ViveStation.stage.getWidth();
				screenheight = ViveStation.stage.getHeight();
				ViveStation.stage.setMaximized(false);
				root.IV_WindowSizeButton.setImage(IM_SmallWindowBar);
				WindowSizeButtonHover();
				try {
					tempstage.close();
				} catch(Exception e) {}
				windowlocation = 0;
			}
			
		});
		root.IV_Logo.setOnMouseReleased(event -> {
			try {
			tempstage.close();
			} catch(Exception e) {}
			if(ViveStation.stage.getX()==0
					&&ViveStation.stage.getY()==0
					&&ViveStation.stage.getWidth()==WIDTH
					&&ViveStation.stage.getHeight()==HEIGHT) {
				ViveStation.stage.setMaximized(true);
				WindowSizeButtonHover();
			} else {
				WindowSizeButtonHover();
			}
			switch(windowlocation) {
			case 1:
				windowTemplates(0, 0, WIDTH/2, HEIGHT/2, false);
				windowlocation = 0;
				break;
			case 2:
				windowTemplates(0, HEIGHT/2, WIDTH/2, HEIGHT/2, false);
				windowlocation = 0;
				break;
			case 3:
				windowTemplates(0, 0, WIDTH/2, HEIGHT, false);
				windowlocation = 0;
				break;
			case 4:
				windowTemplates(WIDTH/2, 0, WIDTH/2, HEIGHT/2, false);
				windowlocation = 0;
				break;
			case 5:
				windowTemplates(WIDTH/2, HEIGHT/2, WIDTH/2, HEIGHT/2, false);
				windowlocation = 0;
				break;
			case 6:
				windowTemplates(WIDTH/2, 0, WIDTH/2, HEIGHT, false);
				windowlocation = 0;
				break;
			case 7:
				windowTemplates(0, 0, WIDTH, HEIGHT, true);
				windowlocation = 0;
				break;
			default:
			}
		});
	}
	
	public void WindowSizeButtonHover(){
		root.setOnMouseHovered(root.IV_WindowSizeButton, IM_BigWindowBar, IM_BigWindowBarHovered,
				IM_SmallWindowBar, IM_SmallWindowBarHovered, ViveStation.stage.isMaximized());
	}

	private void tempWindow(double width, double height, double x, double y) {
		try {
			tempstage.initStyle(StageStyle.TRANSPARENT);
			} catch(Exception e) {}

	        StackPane stackPane = new StackPane(createShadowPane());
	        stackPane.setStyle(
	                "-fx-background-color: rgba(255, 255, 255, 0.0);" +
	                "-fx-background-insets: " + shadowsize + ";"
	        );

	        Scene scene = new Scene(stackPane, width, height);
	        scene.setFill(Color.TRANSPARENT);
	        tempstage.setScene(scene);
	        tempstage.setX(x);
	        tempstage.setY(y);
	        tempstage.setAlwaysOnTop(true);
	        tempstage.show();
	}
	
    private Pane createShadowPane() {
        Pane shadowPane = new Pane();
        shadowPane.setStyle(
                "-fx-background-color: white;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), " + shadowsize + ", 0, 0, 0);" +
                "-fx-background-insets: " + shadowsize + ";"
        );

        Rectangle innerRect = new Rectangle();
        Rectangle outerRect = new Rectangle();
        shadowPane.layoutBoundsProperty().addListener(
                (observable, oldBounds, newBounds) -> {
                    innerRect.relocate(
                            newBounds.getMinX() + shadowsize,
                            newBounds.getMinY() + shadowsize
                    );
                    innerRect.setWidth(newBounds.getWidth() - shadowsize * 2);
                    innerRect.setHeight(newBounds.getHeight() - shadowsize * 2);

                    outerRect.setWidth(newBounds.getWidth());
                    outerRect.setHeight(newBounds.getHeight());

                    Shape clip = Shape.subtract(outerRect, innerRect);
                    shadowPane.setClip(clip);
                }
        );

        return shadowPane;
    }
    
    public void windowTemplates(double x, double y, double width, double height, boolean bool) {
    	if(!bool) {
    	ViveStation.stage.setX(x);
		ViveStation.stage.setY(y);
		ViveStation.stage.setWidth(width);
		ViveStation.stage.setHeight(height);
		ViveStation.stage.setMaximized(false);
		root.IV_WindowSizeButton.setImage(IM_SmallWindowBar);
		WindowSizeButtonHover();
    	} else {
    		try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		ViveStation.stage.setMaximized(true);
    		root.IV_WindowSizeButton.setImage(IM_BigWindowBar);
    		WindowSizeButtonHover();
    	}
    }
	
}
