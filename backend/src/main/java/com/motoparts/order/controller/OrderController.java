package com.motoparts.order.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.order.dto.OrderResponse;
import com.motoparts.order.enums.OrderStatus;
import com.motoparts.order.service.OrderService;
import com.motoparts.response.ApiResponse;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {

		this.orderService = orderService;
	}

	@PostMapping
	public ApiResponse<OrderResponse> placeOrder(Authentication authentication) {

		return new ApiResponse<>(true, "Order placed successfully", orderService.placeOrder(authentication.getName()));
	}

	@GetMapping
	public ApiResponse<List<OrderResponse>> getMyOrders(Authentication authentication) {

		return new ApiResponse<>(true, "Orders retrieved successfully",
				orderService.getMyOrders(authentication.getName()));
	}

	@GetMapping("/{orderId}")
	public ApiResponse<OrderResponse> getOrderById(@PathVariable Long orderId, Authentication authentication) {

		return new ApiResponse<>(true, "Order retrieved successfully",
				orderService.getOrderById(orderId, authentication.getName()));
	}

	@PreAuthorize("hasAnyRole('ADMIN','SELLER')")
	@PatchMapping("/{orderId}/status")
	public ApiResponse<OrderResponse> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status) {

		return new ApiResponse<>(true, "Order status updated successfully",
				orderService.updateOrderStatus(orderId, status));
	}

	@PreAuthorize("hasRole('SELLER')")
	@GetMapping("/seller")
	public ApiResponse<List<OrderResponse>> getSellerOrders(Authentication authentication) {

		return new ApiResponse<>(true, "Seller orders fetched successfully",
				orderService.getSellerOrders(authentication.getName()));
	}
}