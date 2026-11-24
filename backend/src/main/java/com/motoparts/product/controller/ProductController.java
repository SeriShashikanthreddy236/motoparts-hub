package com.motoparts.product.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.product.dto.ProductRequest;
import com.motoparts.product.dto.ProductResponse;
import com.motoparts.product.service.ProductService;
import com.motoparts.response.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * SELLER and ADMIN
	 */
	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	@PostMapping
	public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request,
			Authentication authentication) {

		return new ApiResponse<>(true, "Product created successfully",
				productService.createProduct(request, authentication.getName()));
	}

	/**
	 * PUBLIC
	 */
	@GetMapping
	public ApiResponse<List<ProductResponse>> getAllProducts() {

		return new ApiResponse<>(true, "Products fetched successfully", productService.getAllProducts());
	}

	/**
	 * PUBLIC
	 */
	@GetMapping("/{id}")
	public ApiResponse<ProductResponse> getProductById(@PathVariable Long id) {

		return new ApiResponse<>(true, "Product fetched successfully", productService.getProductById(id));
	}

	/**
	 * SELLER - own products
	 */
	@PreAuthorize("hasRole('SELLER')")
	@GetMapping("/my")
	public ApiResponse<List<ProductResponse>> getMyProducts(Authentication authentication) {

		return new ApiResponse<>(true, "Products fetched successfully",
				productService.getMyProducts(authentication.getName()));
	}

	/**
	 * ADMIN ONLY
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/seller/{sellerId}")
	public ApiResponse<List<ProductResponse>> getProductsBySeller(@PathVariable Long sellerId) {

		return new ApiResponse<>(true, "Seller products fetched successfully",
				productService.getProductsBySeller(sellerId));
	}

	/**
	 * SELLER and ADMIN
	 */
	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	@PutMapping("/{id}")
	public ApiResponse<ProductResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request,
			Authentication authentication) {

		return new ApiResponse<>(true, "Product updated successfully",
				productService.updateProduct(id, request, authentication.getName()));
	}

	/**
	 * SELLER and ADMIN
	 */
	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	@DeleteMapping("/{id}")
	public ApiResponse<String> deleteProduct(@PathVariable Long id, Authentication authentication) {

		productService.deleteProduct(id, authentication.getName());

		return new ApiResponse<>(true, "Product deleted successfully", null);
	}

	/**
	 * PUBLIC
	 */
	@GetMapping("/compatible")
	public ApiResponse<List<ProductResponse>> getCompatibleProducts(@RequestParam Long variantId) {

		return new ApiResponse<>(true, "Compatible products fetched successfully",
				productService.getCompatibleProducts(variantId));
	}

	@GetMapping("/search")
	public ApiResponse<Page<ProductResponse>> searchProducts(

			@RequestParam(required = false) String keyword,

			@RequestParam(required = false) Long categoryId,

			@RequestParam(required = false) Long brandId,

			@RequestParam(defaultValue = "0") Integer page,

			@RequestParam(defaultValue = "10") Integer size) {

		return new ApiResponse<>(true, "Products fetched successfully",
				productService.searchProducts(keyword, categoryId, brandId, page, size));
	}

	@GetMapping("/compatible-products")
	public ApiResponse<List<ProductResponse>> getCompatibleProducts(@RequestParam Long variantId,
			@RequestParam Integer year) {

		return new ApiResponse<>(true, "Compatible products fetched successfully",
				productService.getCompatibleProducts(variantId, year));
	}
}