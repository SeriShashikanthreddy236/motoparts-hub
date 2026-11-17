package com.motoparts.product.dto;

public class ProductFitmentRequest {

	private Long productId;
	private Long vehicleVariantId;

	private Integer minYear;
	private Integer maxYear;

	private String notes;

	// Generate Getters & Setters
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getVehicleVariantId() {
		return vehicleVariantId;
	}

	public void setVehicleVariantId(Long vehicleVariantId) {
		this.vehicleVariantId = vehicleVariantId;
	}

	public Integer getMinYear() {
		return minYear;
	}

	public void setMinYear(Integer minYear) {
		this.minYear = minYear;
	}

	public Integer getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(Integer maxYear) {
		this.maxYear = maxYear;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}