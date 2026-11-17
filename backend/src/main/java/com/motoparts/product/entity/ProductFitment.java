package com.motoparts.product.entity;

import com.motoparts.vehicle.entity.VehicleVariant;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_fitments", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "product_id", "vehicle_variant_id", "min_year", "max_year" }) })
public class ProductFitment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "vehicle_variant_id", nullable = false)
	private VehicleVariant vehicleVariant;

	private Integer minYear;

	private Integer maxYear;

	private String notes;

	public ProductFitment() {
	}

	// Generate Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public VehicleVariant getVehicleVariant() {
		return vehicleVariant;
	}

	public void setVehicleVariant(VehicleVariant vehicleVariant) {
		this.vehicleVariant = vehicleVariant;
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