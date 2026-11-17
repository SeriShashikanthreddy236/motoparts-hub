package com.motoparts.vehicle.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.motoparts.vehicle.constant.VehicleType;

@Entity
@Table(
	    name = "vehicle_models",
	    uniqueConstraints = {
	        @UniqueConstraint(columnNames = {"code", "make_id"})
	 }
)
public class VehicleModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String code;

	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;

	private Boolean active = true;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "make_id", nullable = false)
	private VehicleMake make;

	@JsonIgnore
	@OneToMany(mappedBy = "model")
	private List<VehicleVariant> variants = new ArrayList<>();

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

	
	
	// Constructor
	public VehicleModel() {
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public VehicleMake getMake() {
		return make;
	}

	public void setMake(VehicleMake make) {
		this.make = make;
	}

	public List<VehicleVariant> getVariants() {
		return variants;
	}

	public void setVariants(List<VehicleVariant> variants) {
		this.variants = variants;
	}

}