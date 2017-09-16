package com.vivestation.menubar;

import com.vivestation.RootController;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PopupControl;

public class ContextMenuFilter extends ContextMenu {
	
	public RootController root;
	
	public MenuItem MI_Close;
	public MenuItem MI_MinimizeAndMaximize;
	
	public ContextMenuFilter(RootController root)
	{
		super();
		this.root = root;
		
		MI_Close = new MenuItem("Filter");
		MI_Close.setOnAction(event -> root.finishProgram());
		MI_MinimizeAndMaximize = new MenuItem("Minimize/Maximize");
		MI_MinimizeAndMaximize.setOnAction(event -> root.minimizeProgram());
		
		getItems().addAll(MI_Close, MI_MinimizeAndMaximize);
	}
}