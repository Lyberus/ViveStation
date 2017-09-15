package com.vivestation.menubar;

import com.vivestation.RootController;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PopupControl;

public class ContextMenuFile extends ContextMenu {
	
	public RootController root;
	
	public MenuItem MI_Close;
	public MenuItem MI_MinimizeAndMaximize;
	
	public ContextMenuFile(RootController root)
	{
		super();
		this.root = root;
		
		MI_Close = new MenuItem("Exit");
		MI_Close.setOnAction(event -> root.finishProgram());
		MI_MinimizeAndMaximize = new MenuItem("Minimize/Maximize");
		MI_MinimizeAndMaximize.setOnAction(event -> root.minimizeProgram());
		
		getItems().addAll(MI_Close, MI_MinimizeAndMaximize);
	}
}
