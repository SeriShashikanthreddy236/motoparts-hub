package com.motoparts.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.motoparts.order.enums.OrderStatus;

public class OrderResponse {

	private Long orderId;

	private BigDecimal totalAmount;

	private OrderStatus status;

	private LocalDateTime orderedAt;

	private List<OrderItemResponse> items;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(LocalDateTime orderedAt) {
		this.orderedAt = orderedAt;
	}

	public List<OrderItemResponse> getItems() {
		return items;
	}

	public void setItems(List<OrderItemResponse> items) {
		this.items = items;
	}

	// getters/setters

}