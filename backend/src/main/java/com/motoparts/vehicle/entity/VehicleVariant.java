package com.motoparts.vehicle.entity;

import com.motoparts.vehicle.constant.FuelType;

import com.motoparts.vehicle.constant.TransmissionType;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vehicle_variants")
public class VehicleVariant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer engineCapacityCc;

	@Enumerated(EnumType.STRING)
	private FuelType fuelType;

	@Enumerated(EnumType.STRING)
	private TransmissionType transmission;

	private Integer startYear;

	private Integer endYear;

	private Boolean active = true;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "model_id", nullable = false)
	private VehicleModel model;

	
	
	
	// Constructor
	public VehicleVariant() {
		
	}
	
	
	
	
	// Generate Getters & Setters
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

	public Integer getEngineCapacityCc() {
		return engineCapacityCc;
	}

	public void setEngineCapacityCc(Integer engineCapacityCc) {
		this.engineCapacityCc = engineCapacityCc;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public TransmissionType getTransmission() {
		return transmission;
	}

	public void setTransmission(TransmissionType transmission) {
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public VehicleModel getModel() {
		return model;
	}

	public void setModel(VehicleModel model) {
		this.model = model;
	}

}