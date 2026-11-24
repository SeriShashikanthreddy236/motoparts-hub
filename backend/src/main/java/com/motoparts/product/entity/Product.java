package com.motoparts.product.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.motoparts.brand.entity.Brand;
import com.motoparts.category.entity.Category;
import com.motoparts.seller.entity.SellerProfile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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

	private Boolean active = true;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<ProductFitment> fitments = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "seller_id")
	private SellerProfile seller;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	private String partNumber;

	private BigDecimal mrp;

	private BigDecimal sellingPrice;
	
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
	
	public SellerProfile getSeller() {
	    return seller;
	}

	public void setSeller(SellerProfile seller) {
	    this.seller = seller;
	}

	public Category getCategory() {
	    return category;
	}

	public void setCategory(Category category) {
	    this.category = category;
	}

	public Brand getBrand() {
	    return brand;
	}

	public void setBrand(Brand brand) {
	    this.brand = brand;
	}

	public String getPartNumber() {
	    return partNumber;
	}

	public void setPartNumber(String partNumber) {
	    this.partNumber = partNumber;
	}

	public BigDecimal getMrp() {
	    return mrp;
	}

	public void setMrp(BigDecimal mrp) {
	    this.mrp = mrp;
	}

	public BigDecimal getSellingPrice() {
	    return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
	    this.sellingPrice = sellingPrice;
	}

}