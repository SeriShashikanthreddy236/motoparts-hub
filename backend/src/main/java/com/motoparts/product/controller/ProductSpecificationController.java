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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Product Specifications", description = "Manage technical specifications displayed for catalog products")
public class ProductSpecificationController {

	private final ProductSpecificationService service;

	public ProductSpecificationController(ProductSpecificationService service) {

		this.service = service;
	}

	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	@PostMapping("/products/{productId}/specifications")
	@Operation(summary = "Add a product specification", description = "Adds a technical attribute or specification to a product.")
	public ApiResponse<ProductSpecificationResponse> addSpecification(@PathVariable Long productId,
			@Valid @RequestBody ProductSpecificationRequest request) {

		return new ApiResponse<>(true, "Specification added successfully",
				service.addSpecification(productId, request));
	}

	@GetMapping("/products/{productId}/specifications")
	@Operation(summary = "List product specifications", description = "Retrieves all specifications associated with a product.")
	public ApiResponse<List<ProductSpecificationResponse>> getSpecifications(@PathVariable Long productId) {

		return new ApiResponse<>(true, "Specifications fetched successfully", service.getSpecifications(productId));
	}

	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	@DeleteMapping("/specifications/{id}")
	@Operation(summary = "Delete a specification", description = "Deletes a technical specification by its ID.")
	public ApiResponse<String> deleteSpecification(@PathVariable Long id) {

		service.deleteSpecification(id);

		return new ApiResponse<>(true, "Specification deleted successfully", null);
	}
}