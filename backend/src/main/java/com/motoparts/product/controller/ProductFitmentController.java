package com.motoparts.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.product.dto.ProductFitmentRequest;
import com.motoparts.product.dto.ProductResponse;
import com.motoparts.product.entity.ProductFitment;
import com.motoparts.product.service.ProductFitmentService;
import com.motoparts.response.ApiResponse;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/fitments")
@Tag(name = "Product Fitments", description = "Map catalog products to compatible vehicle variants")
public class ProductFitmentController {

	private final ProductFitmentService productFitmentService;

	public ProductFitmentController(ProductFitmentService productFitmentService) {

		this.productFitmentService = productFitmentService;
	}

	@PostMapping
	@Operation(summary = "Create a product fitment", description = "Associates a product with a vehicle variant, optional model year, or fitment details.")
	public ApiResponse<ProductFitment> createFitment(@Valid @RequestBody ProductFitmentRequest request) {

		return new ApiResponse<>(true, "Product fitment created successfully",
				productFitmentService.createFitment(request));
	}

	@GetMapping("/product/{productId}")
	@Operation(summary = "List fitments by product", description = "Retrieves every vehicle fitment associated with a product.")
	public ApiResponse<List<ProductFitment>> getFitmentsByProduct(@PathVariable Long productId) {

		return new ApiResponse<>(true, "Product fitments fetched successfully",
				productFitmentService.getFitmentsByProduct(productId));
	}

	@GetMapping("/variant/{variantId}")
	@Operation(summary = "List products by vehicle variant", description = "Retrieves fitments that apply to a specific vehicle variant.")
	public ApiResponse<List<ProductFitment>> getFitmentsByVariant(@PathVariable Long variantId) {

		return new ApiResponse<>(true, "Variant fitments fetched successfully",
				productFitmentService.getFitmentsByVariant(variantId));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a fitment", description = "Removes a product-to-vehicle compatibility mapping.")
	public ApiResponse<String> deleteFitment(@PathVariable Long id) {

		productFitmentService.deleteFitment(id);

		return new ApiResponse<>(true, "Fitment deleted successfully", null);
	}

}