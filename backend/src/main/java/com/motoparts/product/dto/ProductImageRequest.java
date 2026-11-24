package com.motoparts.product.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductImageRequest {

	@NotBlank(message = "Image URL is required")
	private String imageUrl;

	private String altText;

	private Integer displayOrder;

	private Boolean primaryImage;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Boolean getPrimaryImage() {
		return primaryImage;
	}

	public void setPrimaryImage(Boolean primaryImage) {
		this.primaryImage = primaryImage;
	}

	// Getters & Setters
}