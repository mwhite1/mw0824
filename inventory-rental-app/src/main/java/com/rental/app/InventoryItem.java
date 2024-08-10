package com.rental.app;

import java.io.Serializable;
import java.util.Objects;

/**
 * Simple class that represents an inventory item
 * @author Malcolm White
 *
 */
public class InventoryItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private InventoryItemType type;
	private String brand;
	
	public InventoryItem(String code, InventoryItemType type, String brand) {
		this.code = code;
		this.type = type;
		this.brand = brand;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String toolCode) {
		this.code = toolCode;
	}

	public InventoryItemType getType() {
		return type;
	}

	public void setType(InventoryItemType type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Tool [toolCode=" + code + ", type=" + type + ", brand=" + brand + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, code, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryItem other = (InventoryItem) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(code, other.code)
				&& Objects.equals(type, other.type);
	}
}
