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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Browse, search, create, update, and delete catalog products")
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
	@Operation(summary = "Create a product", description = "Creates a catalog product for the authenticated seller or an administrator.")
	public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request,
			Authentication authentication) {

		return new ApiResponse<>(true, "Product created successfully",
				productService.createProduct(request, authentication.getName()));
	}

	/**
	 * PUBLIC
	 */
	@GetMapping
	@Operation(summary = "List all products", description = "Returns the complete public product catalog.")
	public ApiResponse<List<ProductResponse>> getAllProducts() {

		return new ApiResponse<>(true, "Products fetched successfully", productService.getAllProducts());
	}

	/**
	 * PUBLIC
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Get product details", description = "Retrieves a product and its catalog information by product ID.")
	public ApiResponse<ProductResponse> getProductById(@PathVariable Long id) {

		return new ApiResponse<>(true, "Product fetched successfully", productService.getProductById(id));
	}

	/**
	 * SELLER - own products
	 */
	@PreAuthorize("hasRole('SELLER')")
	@GetMapping("/my")
	@Operation(summary = "List my products", description = "Returns products owned by the authenticated seller.")
	public ApiResponse<List<ProductResponse>> getMyProducts(Authentication authentication) {

		return new ApiResponse<>(true, "Products fetched successfully",
				productService.getMyProducts(authentication.getName()));
	}

	/**
	 * ADMIN ONLY
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/seller/{sellerId}")
	@Operation(summary = "List a seller's products", description = "Returns all catalog products belonging to a specific seller.")
	public ApiResponse<List<ProductResponse>> getProductsBySeller(@PathVariable Long sellerId) {

		return new ApiResponse<>(true, "Seller products fetched successfully",
				productService.getProductsBySeller(sellerId));
	}

	/**
	 * SELLER and ADMIN
	 */
	@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
	@PutMapping("/{id}")
	@Operation(summary = "Update a product", description = "Updates product information. Sellers may update only their own products.")
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
	@Operation(summary = "Delete a product", description = "Deletes a product owned by the authenticated seller or by an administrator.")
	public ApiResponse<String> deleteProduct(@PathVariable Long id, Authentication authentication) {

		productService.deleteProduct(id, authentication.getName());

		return new ApiResponse<>(true, "Product deleted successfully", null);
	}

	/**
	 * PUBLIC
	 */
	@GetMapping("/compatible")
	@Operation(summary = "Find compatible products", description = "Lists products that fit the supplied vehicle variant.")
	public ApiResponse<List<ProductResponse>> getCompatibleProducts(@RequestParam Long variantId) {

		return new ApiResponse<>(true, "Compatible products fetched successfully",
				productService.getCompatibleProducts(variantId));
	}

	@GetMapping("/search")
	@Operation(summary = "Search and filter products", description = "Searches products by keyword, category, brand, and pagination parameters.")
	public ApiResponse<Page<ProductResponse>> searchProducts(

			@Parameter(description = "Optional text to match against product names or descriptions") @RequestParam(required = false) String keyword,

			@Parameter(description = "Optional category ID filter") @RequestParam(required = false) Long categoryId,

			@Parameter(description = "Optional brand ID filter") @RequestParam(required = false) Long brandId,

			@Parameter(description = "Zero-based page number") @RequestParam(defaultValue = "0") Integer page,

			@Parameter(description = "Number of products per page") @RequestParam(defaultValue = "10") Integer size) {

		return new ApiResponse<>(true, "Products fetched successfully",
				productService.searchProducts(keyword, categoryId, brandId, page, size));
	}

	@GetMapping("/compatible-products")
	@Operation(summary = "Find year-compatible products", description = "Lists products compatible with a vehicle variant and manufacturing year.")
	public ApiResponse<List<ProductResponse>> getCompatibleProducts(@RequestParam Long variantId,
			@RequestParam Integer year) {

		return new ApiResponse<>(true, "Compatible products fetched successfully",
				productService.getCompatibleProducts(variantId, year));
	}
}