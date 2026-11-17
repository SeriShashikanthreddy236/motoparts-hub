package com.motoparts.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.motoparts.product.dto.ProductFitmentRequest;
import com.motoparts.product.entity.ProductFitment;
import com.motoparts.product.service.ProductFitmentService;
import com.motoparts.response.ApiResponse;

@RestController
@RequestMapping("/api/v1/fitments")
public class ProductFitmentController {

	private final ProductFitmentService productFitmentService;

	public ProductFitmentController(ProductFitmentService productFitmentService) {

		this.productFitmentService = productFitmentService;
	}

	@PostMapping
	public ApiResponse<ProductFitment> createFitment(@RequestBody ProductFitmentRequest request) {

		return new ApiResponse<>(true, "Product fitment created successfully",
				productFitmentService.createFitment(request));
	}

	@GetMapping("/product/{productId}")
	public ApiResponse<List<ProductFitment>> getFitmentsByProduct(@PathVariable Long productId) {

		return new ApiResponse<>(true, "Product fitments fetched successfully",
				productFitmentService.getFitmentsByProduct(productId));
	}

	@GetMapping("/variant/{variantId}")
	public ApiResponse<List<ProductFitment>> getFitmentsByVariant(@PathVariable Long variantId) {

		return new ApiResponse<>(true, "Variant fitments fetched successfully",
				productFitmentService.getFitmentsByVariant(variantId));
	}

	@DeleteMapping("/{id}")
	public ApiResponse<String> deleteFitment(@PathVariable Long id) {

		productFitmentService.deleteFitment(id);

		return new ApiResponse<>(true, "Fitment deleted successfully", null);
	}
}