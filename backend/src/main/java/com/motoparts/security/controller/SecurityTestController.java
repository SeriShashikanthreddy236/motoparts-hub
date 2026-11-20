package com.motoparts.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

	@GetMapping("/api/v1/test/public")
	public String publicApi() {

		return "Public API";
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/api/v1/test/customer")
	public String customerApi() {

		return "Customer API";
	}

	@PreAuthorize("hasRole('SELLER')")
	@GetMapping("/api/v1/test/seller")
	public String sellerApi() {

		return "Seller API";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/api/v1/test/admin")
	public String adminApi() {

		return "Admin API";
	}

	@PreAuthorize("hasRole('DELIVERY_PARTNER')")
	@GetMapping("/api/v1/test/delivery")
	public String deliveryApi() {

		return "Delivery API";
	}
}