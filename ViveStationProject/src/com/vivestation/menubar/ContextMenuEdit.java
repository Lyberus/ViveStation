package com.vivestation.menubar;

import com.vivestation.RootController;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PopupControl;

public class ContextMenuEdit extends ContextMenu {
	
	public RootController root;
	
	public MenuItem MI_Close;
	public MenuItem MI_MinimizeAndMaximize;
	
	public ContextMenuEdit(RootController root)
	{
		super();
		this.root = root;
		
		MI_Close = new MenuItem("Edit");
		MI_Close.setOnAction(event -> root.finishProgram());
		MI_MinimizeAndMaximize = new MenuItem("Minimize/Maximize");
		MI_MinimizeAndMaximize.setOnAction(event -> root.minimizeProgram());
		
		getItems().addAll(MI_Close, MI_MinimizeAndMaximize);
	}
}
