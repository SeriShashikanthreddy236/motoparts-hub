package com.motoparts.order.service;

import java.util.List;

import com.motoparts.order.dto.OrderResponse;
import com.motoparts.order.enums.OrderStatus;

public interface OrderService {

	OrderResponse placeOrder(String email);

	List<OrderResponse> getMyOrders(String email);

	OrderResponse getOrderById(Long orderId, String email);

	OrderResponse updateOrderStatus(Long orderId, OrderStatus status);

	List<OrderResponse> getSellerOrders(String email);
}