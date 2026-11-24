package com.motoparts.inventory.dto;

import java.time.LocalDateTime;

public class StockMovementResponse {

	private Long id;

	private String movementType;

	private Integer quantity;

	private Integer previousQuantity;

	private Integer newQuantity;

	private String reason;

	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPreviousQuantity() {
		return previousQuantity;
	}

	public void setPreviousQuantity(Integer previousQuantity) {
		this.previousQuantity = previousQuantity;
	}

	public Integer getNewQuantity() {
		return newQuantity;
	}

	public void setNewQuantity(Integer newQuantity) {
		this.newQuantity = newQuantity;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	// getters setters
}