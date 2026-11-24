package com.motoparts.product.dto;

public class ProductSpecificationResponse {

	private Long id;

	private String specificationName;

	private String specificationValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpecificationName() {
		return specificationName;
	}

	public void setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
	}

	public String getSpecificationValue() {
		return specificationValue;
	}

	public void setSpecificationValue(String specificationValue) {
		this.specificationValue = specificationValue;
	}
}