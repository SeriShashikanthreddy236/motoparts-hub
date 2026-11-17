package com.motoparts.vehicle.dto;

public class VehicleVariantRequest {

	private String name;
	private Long modelId;
	private Integer engineCapacityCc;
	private String fuelType;
	private String transmission;
	private Integer startYear;
	private Integer endYear;

	public VehicleVariantRequest() {
	}

	// Generate Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public Integer getEngineCapacityCc() {
		return engineCapacityCc;
	}

	public void setEngineCapacityCc(Integer engineCapacityCc) {
		this.engineCapacityCc = engineCapacityCc;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public Integer getStartYear() {
		return startYear;
	}

	public void setStartYear(Integer startYear) {
		this.startYear = startYear;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}

}