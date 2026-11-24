package com.motoparts.product.dto;

import java.math.BigDecimal;

public class ProductResponse {

	private Long id;

	private String name;

	private String sku;

	private String description;

	private String categoryName;

	private String brandName;

	private String sellerName;

	private String partNumber;

	private BigDecimal mrp;

	private BigDecimal sellingPrice;

	private Boolean active;

	public ProductResponse() {
	}

	public ProductResponse(Long id, String name, String sku, String description, String categoryName, String brandName,
			String sellerName, String partNumber, BigDecimal mrp, BigDecimal sellingPrice, Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.sku = sku;
		this.description = description;
		this.categoryName = categoryName;
		this.brandName = brandName;
		this.sellerName = sellerName;
		this.partNumber = partNumber;
		this.mrp = mrp;
		this.sellingPrice = sellingPrice;
		this.active = active;
	}

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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	// Generate Constructors
	// Generate Getters & Setters
}