package com.motoparts.inventory.dto;

public class InventoryRequest {

	private Long productId;

	private Integer quantityAvailable;

	private Integer reorderLevel;

	public InventoryRequest() {
	}

	public InventoryRequest(Long productId, Integer quantityAvailable, Integer reorderLevel) {

		this.productId = productId;
		this.quantityAvailable = quantityAvailable;
		this.reorderLevel = reorderLevel;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}
}