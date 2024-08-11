package com.rental.app;

/**
 * Enumerator that contains mappings of InventoryItem instance variable names.
 * Typically used for InventoryItem serialization and deserialization
 * 
 * @author Malcolm White
 *
 */
public enum InventoryItemAttrNames {
	CODE("code"),
	TYPE("type"),
	BRAND("brand");

	public final String label;

	private InventoryItemAttrNames(String label) {
		this.label = label;
	}
}
