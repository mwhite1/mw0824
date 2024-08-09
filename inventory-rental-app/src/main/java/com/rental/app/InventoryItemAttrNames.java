package com.rental.app;

public enum InventoryItemAttrNames {
	CODE("code"),
	TYPE("type"),
	BRAND("brand");
	
	public final String label;
	
	private InventoryItemAttrNames(String label) {
		this.label = label;
	}
}
