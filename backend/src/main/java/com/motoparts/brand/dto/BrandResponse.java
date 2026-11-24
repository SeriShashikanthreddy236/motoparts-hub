package com.motoparts.brand.dto;

public class BrandResponse {

	private Long id;
	private String name;
	private String code;
	private String logoUrl;
	private String description;
	private Boolean active;

	// Constructor

	public BrandResponse() {
	}

	public BrandResponse(Long id, String name, String code, String logoUrl, String description, Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.logoUrl = logoUrl;
		this.description = description;
		this.active = active;
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}