package com.motoparts.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductRequest {

	@NotBlank(message = "Product name is required")
	@Size(min = 3, max = 100, message = "Product name must be between 3 and 100 characters")
	private String name;

	@NotBlank(message = "SKU is required")
	private String sku;

	@NotBlank(message = "Description is required")
	@Size(min = 10, message = "Description must contain at least 10 characters")
	private String description;

	@NotNull(message = "Category is required")
	private Long categoryId;

	@NotNull(message = "Brand is required")
	private Long brandId;

	@NotBlank(message = "Part number is required")
	private String partNumber;

	@NotNull(message = "MRP is required")
	@Positive(message = "MRP must be greater than zero")
	private BigDecimal mrp;

	@NotNull(message = "Selling price is required")
	@Positive(message = "Selling price must be greater than zero")
	private BigDecimal sellingPrice;

	public ProductRequest() {
	}

	public ProductRequest(String name, String sku, String description, Long categoryId, Long brandId, String partNumber,
			BigDecimal mrp, BigDecimal sellingPrice) {
		super();
		this.name = name;
		this.sku = sku;
		this.description = description;
		this.categoryId = categoryId;
		this.brandId = brandId;
		this.partNumber = partNumber;
		this.mrp = mrp;
		this.sellingPrice = sellingPrice;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
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

	// Generate Constructors
	// Generate Getters & Setters

}