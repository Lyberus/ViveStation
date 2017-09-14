package com.vivestation.menubar;

import com.vivestation.RootController;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PopupControl;

public class ContextMenuFile extends ContextMenu {
	
	public RootController root;
	
	public MenuItem MI_Close;
	
	public ContextMenuFile(RootController root)
	{
		super();
		this.root = root;
		
		MI_Close = new MenuItem("Close		");
		MI_Close.setOnAction(event -> root.finishProgram());
		
		getItems().addAll(MI_Close);
	}
}
