package com.motoparts.vehicle.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vehicle_makes", uniqueConstraints = { @UniqueConstraint(columnNames = "code") })
public class VehicleMake {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String code;

	private String logoUrl;

	@Column(nullable = false)
	private Boolean active = true;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@JsonIgnore
	@OneToMany(mappedBy = "make")
	private List<VehicleModel> models = new ArrayList<>();

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
	public VehicleMake() {
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

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
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

	public List<VehicleModel> getModels() {
		return models;
	}

	public void setModels(List<VehicleModel> models) {
		this.models = models;
	}

}