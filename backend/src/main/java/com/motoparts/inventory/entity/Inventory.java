package com.motoparts.inventory.entity;

import java.time.LocalDateTime;

import com.motoparts.product.entity.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "product_id", nullable = false, unique = true)
	private Product product;

	@Column(nullable = false)
	private Integer quantityAvailable;

	@Column(nullable = false)
	private Integer reservedQuantity = 0;

	@Column(nullable = false)
	private Integer reorderLevel;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	public Inventory() {
	}

	@PrePersist
	@PreUpdate
	public void updateTimestamp() {
		updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
}