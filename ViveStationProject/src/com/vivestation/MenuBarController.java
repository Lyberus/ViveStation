package com.vivestation;

import com.vivestation.menubar.ContextMenuFile;

import javafx.scene.control.ContextMenu;

public class MenuBarController {

	public RootController root;
	
	public ContextMenuFile CM_File;
	
	public MenuBarController(RootController root) {
		this.root = root;
		
		CM_File = new ContextMenuFile(root);
		
		init();
	}
	
	public void init() {
		root.IV_Logo.setOnMouseClicked(event -> root.finishProgram());
		root.IV_File.setOnMouseClicked(event -> {
			if(!CM_File.isShowing())
				CM_File.show(root.IV_File,
						root.IV_File.getLayoutX(),
						root.IV_File.getLayoutY() + root.IV_File.getFitHeight());
		});
		root.IV_CloseButton.setOnMouseClicked(event -> root.finishProgram());
	}
	
}
