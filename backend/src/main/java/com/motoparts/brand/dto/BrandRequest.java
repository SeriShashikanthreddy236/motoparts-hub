package com.motoparts.brand.dto;

public class BrandRequest {

	private String name;
	private String code;
	private String logoUrl;
	private String description;

	public BrandRequest() {
	}

	public BrandRequest(String name, String code, String logoUrl, String description) {
		this.name = name;
		this.code = code;
		this.logoUrl = logoUrl;
		this.description = description;
	}

	
	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
}