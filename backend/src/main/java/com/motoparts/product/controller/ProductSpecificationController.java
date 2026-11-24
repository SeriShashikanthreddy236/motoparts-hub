package com.motoparts.product.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.product.dto.ProductSpecificationRequest;
import com.motoparts.product.dto.ProductSpecificationResponse;
import com.motoparts.product.service.ProductSpecificationService;
import com.motoparts.response.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ProductSpecificationController {

	private final ProductSpecificationService service;

	public ProductSpecificationController(ProductSpecificationService service) {

		this.service = service;
	}

	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	@PostMapping("/products/{productId}/specifications")
	public ApiResponse<ProductSpecificationResponse> addSpecification(@PathVariable Long productId,
			@Valid @RequestBody ProductSpecificationRequest request) {

		return new ApiResponse<>(true, "Specification added successfully",
				service.addSpecification(productId, request));
	}

	@GetMapping("/products/{productId}/specifications")
	public ApiResponse<List<ProductSpecificationResponse>> getSpecifications(@PathVariable Long productId) {

		return new ApiResponse<>(true, "Specifications fetched successfully", service.getSpecifications(productId));
	}

	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	@DeleteMapping("/specifications/{id}")
	public ApiResponse<String> deleteSpecification(@PathVariable Long id) {

		service.deleteSpecification(id);

		return new ApiResponse<>(true, "Specification deleted successfully", null);
	}
}