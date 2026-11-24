package com.motoparts.inventory.entity;

import java.time.LocalDateTime;

import com.motoparts.inventory.enums.MovementType;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_movements")
public class StockMovement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;

	@Enumerated(EnumType.STRING)
	private MovementType movementType;

	private Integer quantity;

	private Integer previousQuantity;

	private Integer newQuantity;

	private String reason;

	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public MovementType getMovementType() {
		return movementType;
	}

	public void setMovementType(MovementType movementType) {
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