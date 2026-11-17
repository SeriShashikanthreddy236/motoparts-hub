package com.motoparts.product.dto;

public class ProductRequest {

	private String name;
	private String sku;
	private String description;
	private String brand;

	// Constructor
	public ProductRequest() {
		
	}

	// Generate Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}