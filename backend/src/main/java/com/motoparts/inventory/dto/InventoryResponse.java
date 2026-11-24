package com.motoparts.inventory.dto;

import java.time.LocalDateTime;

public class InventoryResponse {

	private Long id;

	private Long productId;

	private String productName;

	private Integer quantityAvailable;

	private Integer reservedQuantity;

	private Integer reorderLevel;

	private LocalDateTime updatedAt;

	public InventoryResponse() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Integer getReservedQuantity() {
		return reservedQuantity;
	}

	public void setReservedQuantity(Integer reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}