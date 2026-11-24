package com.motoparts.product.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.product.dto.ProductImageRequest;
import com.motoparts.product.dto.ProductImageResponse;
import com.motoparts.product.service.ProductImageService;
import com.motoparts.response.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ProductImageController {

	private final ProductImageService productImageService;

	public ProductImageController(ProductImageService productImageService) {

		this.productImageService = productImageService;
	}

	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	@PostMapping("/products/{productId}/images")
	public ApiResponse<ProductImageResponse> addImage(@PathVariable Long productId,
			@Valid @RequestBody ProductImageRequest request, Authentication authentication) {

		return new ApiResponse<>(true, "Image added successfully",
				productImageService.addImage(productId, request, authentication.getName()));
	}

	@GetMapping("/products/{productId}/images")
	public ApiResponse<List<ProductImageResponse>> getImages(@PathVariable Long productId) {

		return new ApiResponse<>(true, "Images fetched successfully", productImageService.getImages(productId));
	}

	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	@DeleteMapping("/products/{productId}/images/{imageId}")
	public ApiResponse<String> deleteImage(@PathVariable Long productId, @PathVariable Long imageId,
			Authentication authentication) {

		productImageService.deleteImage(productId, imageId, authentication.getName());

		return new ApiResponse<>(true, "Image deleted successfully", null);
	}
}