package com.vivestation;


public class MenuBarController {

	public RootController root;
	
	public MenuBarController(RootController root) {
		this.root = root;
		init();
	}
	
	public void init() {
		root.IV_Logo.setOnMouseClicked(event -> root.finishProgram());
	}
	
}
