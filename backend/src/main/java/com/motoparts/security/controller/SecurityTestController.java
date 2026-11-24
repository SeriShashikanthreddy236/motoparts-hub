package com.motoparts.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Security Test", description = "Role-based endpoints used to verify API authorization")
public class SecurityTestController {

	@GetMapping("/api/v1/test/public")
	@Operation(summary = "Test public access", description = "Verifies that an endpoint is accessible without authentication.")
	public String publicApi() {

		return "Public API";
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/api/v1/test/customer")
	@Operation(summary = "Test customer access", description = "Verifies that a JWT with the CUSTOMER role can access the endpoint.")
	public String customerApi() {

		return "Customer API";
	}

	@PreAuthorize("hasRole('SELLER')")
	@GetMapping("/api/v1/test/seller")
	@Operation(summary = "Test seller access", description = "Verifies that a JWT with the SELLER role can access the endpoint.")
	public String sellerApi() {

		return "Seller API";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/api/v1/test/admin")
	@Operation(summary = "Test administrator access", description = "Verifies that a JWT with the ADMIN role can access the endpoint.")
	public String adminApi() {

		return "Admin API";
	}

	@PreAuthorize("hasRole('DELIVERY_PARTNER')")
	@GetMapping("/api/v1/test/delivery")
	@Operation(summary = "Test delivery partner access", description = "Verifies that a JWT with the DELIVERY_PARTNER role can access the endpoint.")
	public String deliveryApi() {

		return "Delivery API";
	}
}