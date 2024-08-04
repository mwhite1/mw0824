package com.rental.app;

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
}
