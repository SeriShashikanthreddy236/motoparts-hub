package com.motoparts.product.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String sku;

	@Column(length = 1000)
	private String description;

	private String brand;

	private Boolean active = true;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<ProductFitment> fitments = new ArrayList<>();

	public Product() {
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<ProductFitment> getFitments() {
		return fitments;
	}

	public void setFitments(List<ProductFitment> fitments) {
		this.fitments = fitments;
	}

}