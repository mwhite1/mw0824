package com.rental.app;

import java.util.Objects;

public class Tool {
	private String toolCode;
	private ToolType type;
	private String brand;
	
	public Tool(String toolCode, ToolType type, String brand) {
		this.toolCode = toolCode;
		this.type = type;
		this.brand = brand;
	}

	public String getToolCode() {
		return toolCode;
	}

	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public ToolType getType() {
		return type;
	}

	public void setType(ToolType type) {
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
		return "Tool [toolCode=" + toolCode + ", type=" + type + ", brand=" + brand + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, toolCode, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tool other = (Tool) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(toolCode, other.toolCode)
				&& Objects.equals(type, other.type);
	}
}
