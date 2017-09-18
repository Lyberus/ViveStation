package com.vivestation;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.FileInputStream;

import com.vivestation.menubar.ContextMenuFile;
import com.vivestation.menubar.ContextMenuFilter;
import com.vivestation.menubar.ContextMenuWindow;
import com.vivestation.menubar.ContextMenuEdit;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Parent;

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
	private double screenfx;
	private double screenfy;
	private double screenx;
	private double screeny;
	private double screenw;
	private double screenh;
	private double lastX = WIDTH/10;
	private double lastY = HEIGHT/10;
	private double lastWidth = WIDTH/1.5;
	private double lastHeight = HEIGHT/1.5;
	private boolean maxim = true;
	private boolean resize = false;
	private int resizetype = 0;
	
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
		
		//메뉴바 이미지 교체
		root.IV_File.setOnMouseClicked(event -> root.showContextMenu(CM_File, root.IV_File));
		root.setOnMouseHovered(root.IV_File, IM_File, IM_FileHovered, CM_File, CM_Edit, CM_Filter, CM_Window);
		CM_File.setOnShowing(event -> {
			root.IV_File.setImage(IM_FileHovered);
		});
		CM_File.setOnHiding(event -> {
			root.IV_File.setImage(IM_File);
		});
		
		root.IV_Edit.setOnMouseClicked(event -> root.showContextMenu(CM_Edit, root.IV_Edit));
		root.setOnMouseHovered(root.IV_Edit, IM_Edit, IM_EditHovered, CM_Edit, CM_File, CM_Filter, CM_Window);
		CM_Edit.setOnShowing(event -> {
			root.IV_Edit.setImage(IM_EditHovered);
		});
		CM_Edit.setOnHiding(event -> {
			root.IV_Edit.setImage(IM_Edit);
		});
		
		root.IV_Filter.setOnMouseClicked(event -> root.showContextMenu(CM_Filter, root.IV_Filter));
		root.setOnMouseHovered(root.IV_Filter, IM_Filter, IM_FilterHovered, CM_Filter, CM_File, CM_Edit, CM_Window);
		CM_Filter.setOnShowing(event -> {
			root.IV_Filter.setImage(IM_FilterHovered);
		});
		CM_Filter.setOnHiding(event -> {
			root.IV_Filter.setImage(IM_Filter);
		});
		
		root.IV_Window.setOnMouseClicked(event -> root.showContextMenu(CM_Window, root.IV_Window));
		root.setOnMouseHovered(root.IV_Window, IM_Window, IM_WindowHovered, CM_Window, CM_File, CM_Edit, CM_Filter);
		CM_Window.setOnShowing(event -> {
			root.IV_Window.setImage(IM_WindowHovered);
		});
		CM_Window.setOnHiding(event -> {
			root.IV_Window.setImage(IM_Window);
		});
		
		
		//
		root.IV_CloseButton.setOnMouseClicked(event -> root.finishProgram());
		
		root.IV_WindowSizeButton.setOnMouseClicked(event -> {
			/*if (ViveStation.stage.isMaximized()) {
				ViveStation.stage.setMaximized(false);
				WindowSizeButtonHover();
			} else {
				ViveStation.stage.setMaximized(true);
				WindowSizeButtonHover();
			}*/
			if (isMaximized()) {
				setMaximized(false);
				root.IV_WindowSizeButton.setImage(IM_SmallWindowBarHovered);
				WindowSizeButtonHover();
			} else {
				setMaximized(true);
				root.IV_WindowSizeButton.setImage(IM_BigWindowBarHovered);
				WindowSizeButtonHover();
			}
			windowlocation = 1;
		});
		WindowSizeButtonHover();
		
		root.IV_MiniButton.setOnMouseClicked(event -> root.minimizeProgram());

		
		root.AP_FHD.setOnMouseMoved(mouseevent -> {
			Point p = MouseInfo.getPointerInfo().getLocation();
			if(!isMaximized()) {
				if(p.x<ViveStation.stage.getX()+4) {
					if(p.y<ViveStation.stage.getY()+4) {
						ViveStation.stage.getScene().setCursor(Cursor.NW_RESIZE);
					} else if(p.y>ViveStation.stage.getY()+ViveStation.stage.getHeight()-4) {
						ViveStation.stage.getScene().setCursor(Cursor.SW_RESIZE);
					} else {
						ViveStation.stage.getScene().setCursor(Cursor.W_RESIZE);
					}
				} else if(p.x>ViveStation.stage.getX()+ViveStation.stage.getWidth()-4) {
					if(p.y<ViveStation.stage.getY()+4) {
						ViveStation.stage.getScene().setCursor(Cursor.NE_RESIZE);
					} else if(p.y>ViveStation.stage.getY()+ViveStation.stage.getHeight()-4) {
						ViveStation.stage.getScene().setCursor(Cursor.SE_RESIZE);
					} else {
						ViveStation.stage.getScene().setCursor(Cursor.E_RESIZE);
					}
				} else if(p.y<ViveStation.stage.getY()+4) {
					ViveStation.stage.getScene().setCursor(Cursor.N_RESIZE);
				} else if(p.y>ViveStation.stage.getY()+ViveStation.stage.getHeight()-4) {
					ViveStation.stage.getScene().setCursor(Cursor.S_RESIZE);
				} else {
					if(root.IV_Logo.isHover()) {
						ViveStation.stage.getScene().setCursor(Cursor.HAND);
					} else {
						ViveStation.stage.getScene().setCursor(Cursor.DEFAULT);
					}
				}
			}
		});
		root.AP_FHD.setOnMousePressed(event -> {
			Point p = MouseInfo.getPointerInfo().getLocation();
			if(!isMaximized()) {
				if(p.x<ViveStation.stage.getX()+4||
						p.x>ViveStation.stage.getX()+ViveStation.stage.getWidth()-4||
						p.y<ViveStation.stage.getY()+4||
						p.y>ViveStation.stage.getY()+ViveStation.stage.getHeight()-4) {
					screenx = p.x - ViveStation.stage.getX();
					screeny = p.y - ViveStation.stage.getY();
					screenfx = ViveStation.stage.getX();
					screenfy = ViveStation.stage.getY();
					screenw = ViveStation.stage.getWidth();
					screenh = ViveStation.stage.getHeight();
					resize = true;
				}
			} else {
				resize = false;
				resizetype = 0;
			}
		});
		root.AP_FHD.setOnMouseReleased(event -> {
			resize = false;
		});
		root.AP_FHD.setOnMouseDragged(event -> {
			Point p = MouseInfo.getPointerInfo().getLocation();
			if(!isMaximized()) {
				if(p.x<ViveStation.stage.getX()+4) {
					if(p.y<ViveStation.stage.getY()+4) {
						ViveStation.stage.getScene().setCursor(Cursor.NW_RESIZE);
						ViveStation.stage.setX(p.x - screenx);
						ViveStation.stage.setY(p.y - screeny);
						ViveStation.stage.setWidth(screenw + screenfx - ViveStation.stage.getX());
						ViveStation.stage.setHeight(screenh + screenfy - ViveStation.stage.getY());
						resizetype = 1;
					} else if(p.y>ViveStation.stage.getY()+ViveStation.stage.getHeight()-4) {
						ViveStation.stage.getScene().setCursor(Cursor.SW_RESIZE);
						ViveStation.stage.setX(p.x - screenx);
						ViveStation.stage.setWidth(screenw + screenfx - ViveStation.stage.getX());
						ViveStation.stage.setHeight(p.y-screenfy);
						resizetype = 3;
					} else {
						ViveStation.stage.getScene().setCursor(Cursor.W_RESIZE);
						resizetype = 2;
					}
				} else if(p.x>ViveStation.stage.getX()+ViveStation.stage.getWidth()-4) {
					if(p.y<ViveStation.stage.getY()+4) {
						ViveStation.stage.getScene().setCursor(Cursor.NE_RESIZE);
						resizetype = 4;
					} else if(p.y>ViveStation.stage.getY()+ViveStation.stage.getHeight()-4) {
						ViveStation.stage.getScene().setCursor(Cursor.SE_RESIZE);
						resizetype = 6;
					} else {
						ViveStation.stage.getScene().setCursor(Cursor.E_RESIZE);
						resizetype = 5;
					}
				} else if(p.y<ViveStation.stage.getY()+4) {
					ViveStation.stage.getScene().setCursor(Cursor.N_RESIZE);
					resizetype = 7;
				} else if(p.y>ViveStation.stage.getY()+ViveStation.stage.getHeight()-4) {
					ViveStation.stage.getScene().setCursor(Cursor.S_RESIZE);
					resizetype = 8;
				} else {
					if(root.IV_Logo.isHover()) {
						ViveStation.stage.getScene().setCursor(Cursor.HAND);
					} else {
						ViveStation.stage.getScene().setCursor(Cursor.DEFAULT);
					}
				}
				if(resizetype != 0)
				{
					switch(resizetype) {
					case 1:
						ViveStation.stage.getScene().setCursor(Cursor.NW_RESIZE);
						ViveStation.stage.setX(p.x - screenx);
						ViveStation.stage.setY(p.y - screeny);
						ViveStation.stage.setWidth(screenw + screenfx - ViveStation.stage.getX());
						ViveStation.stage.setHeight(screenh + screenfy - ViveStation.stage.getY());
						break;
					case 2:
						break;
					case 3:
						ViveStation.stage.getScene().setCursor(Cursor.SW_RESIZE);
						ViveStation.stage.setX(p.x - screenx);
						ViveStation.stage.setWidth(screenw + screenfx - ViveStation.stage.getX());
						ViveStation.stage.setHeight(p.y-screenfy);
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						break;
					case 7:
						break;
					case 8:
						break;
					default:
					}
				}
			}
		});
		
		
		
		root.IV_Logo.setOnMouseEntered(event -> {
			Point p = MouseInfo.getPointerInfo().getLocation();
			if(p.x > ViveStation.stage.getX() + 4 || p.y > ViveStation.stage.getY() + 4 && !resize) {
			ViveStation.stage.getScene().setCursor(Cursor.HAND);
			}
		});
		root.IV_Logo.setOnMouseExited(event -> {
			ViveStation.stage.getScene().setCursor(Cursor.DEFAULT);
		});
		//창 이동 및 스냅
		root.IV_Logo.setOnMousePressed(event -> {
			Point p = MouseInfo.getPointerInfo().getLocation();
			if(p.x > ViveStation.stage.getX() + 4 && p.y > ViveStation.stage.getY() + 4 && !resize) {
				mousex = event.getScreenX() - ViveStation.stage.getX();
				mousey = event.getScreenY() - ViveStation.stage.getY();
				screenwidth = ViveStation.stage.getWidth();
				screenheight = ViveStation.stage.getHeight();
			}
		});
		root.IV_Logo.setOnMouseDragged(event -> {
			Point p = MouseInfo.getPointerInfo().getLocation();
			if(p.x > ViveStation.stage.getX() + 4 && p.y > ViveStation.stage.getY() + 4 && !resize) {
				if(isMaximized()) {
					screenwidth = lastWidth;
					screenheight = lastHeight;
					setMaximized(false);
				}
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
//					setMaximized(false);
					ViveStation.stage.setWidth(screenwidth);
					ViveStation.stage.setHeight(screenheight);
					screenwidth = ViveStation.stage.getWidth();
					screenheight = ViveStation.stage.getHeight();
//					ViveStation.stage.setMaximized(false);
					root.IV_WindowSizeButton.setImage(IM_SmallWindowBar);
					WindowSizeButtonHover();
					try {
						tempstage.close();
					} catch(Exception e) {}
					windowlocation = 0;
				}
			}
		});
		root.IV_Logo.setOnMouseReleased(event -> {
			Point p = MouseInfo.getPointerInfo().getLocation();
			if(p.x > ViveStation.stage.getX() + 4 &&  p.y > ViveStation.stage.getY() + 4 && !resize) {
			try {
			tempstage.close();
			} catch(Exception e) {}
			if(ViveStation.stage.getX()==0
					&&ViveStation.stage.getY()==0
					&&ViveStation.stage.getWidth()==WIDTH
					&&ViveStation.stage.getHeight()==HEIGHT) {
//				ViveStation.stage.setMaximized(true);
				setMaximized(true);
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
			}
		});
		
		

		
	}
	
	
	public void WindowSizeButtonHover(){
//		root.setOnMouseHovered(root.IV_WindowSizeButton, IM_BigWindowBar, IM_BigWindowBarHovered,
//				IM_SmallWindowBar, IM_SmallWindowBarHovered, ViveStation.stage.isMaximized());
		root.setOnMouseHovered(root.IV_WindowSizeButton, IM_BigWindowBar, IM_BigWindowBarHovered,
		IM_SmallWindowBar, IM_SmallWindowBarHovered, isMaximized());
	}

	private void tempWindow(double width, double height, double x, double y) {
		try {
			tempstage.initStyle(StageStyle.TRANSPARENT);
			} catch(Exception e) {}

	        StackPane stackPane = new StackPane(createShadowPane());
	        stackPane.setStyle(
	                "-fx-background-color: rgba(0, 0, 0, 0.0);" +
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
    		setMaximized(false);
    		ViveStation.stage.setX(x);
    		ViveStation.stage.setY(y);
    		ViveStation.stage.setWidth(width);
    		ViveStation.stage.setHeight(height);
//			ViveStation.stage.setMaximized(false);
    		root.IV_WindowSizeButton.setImage(IM_SmallWindowBar);
    		WindowSizeButtonHover();
    	} else {
    		try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//    		ViveStation.stage.setMaximized(true);
    		setMaximized(true);
    		root.IV_WindowSizeButton.setImage(IM_BigWindowBar);
    		WindowSizeButtonHover();
    	}
    }
    
    public void setMaximized(boolean bool) {
    	if(bool) {
    		lastX = ViveStation.stage.getX();
    		lastY = ViveStation.stage.getY();
    		lastWidth = ViveStation.stage.getWidth();
    		lastHeight = ViveStation.stage.getHeight();
    		ViveStation.stage.setX(0);
    		ViveStation.stage.setY(0);
    		ViveStation.stage.setWidth(WIDTH);
    		ViveStation.stage.setHeight(HEIGHT);
    		ViveStation.stage.setMaxWidth(WIDTH);
    		ViveStation.stage.setMaxHeight(HEIGHT);
    		ViveStation.stage.setMinWidth(WIDTH);
    		ViveStation.stage.setMinHeight(HEIGHT);
    		maxim = true;
    	} else {
    		ViveStation.stage.setX(lastX);
    		ViveStation.stage.setY(lastY);
    		ViveStation.stage.setWidth(lastWidth);
    		ViveStation.stage.setHeight(lastHeight);
    		ViveStation.stage.setMaxWidth(WIDTH);
    		ViveStation.stage.setMaxHeight(HEIGHT);
    		ViveStation.stage.setMinWidth(533);
    		ViveStation.stage.setMinHeight(300);
    		maxim = false;
    	}
    }
    
    public boolean isMaximized() {
    	return maxim;
    }
	
}
