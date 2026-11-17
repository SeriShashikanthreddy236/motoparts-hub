package com.motoparts.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.motoparts.product.dto.ProductRequest;
import com.motoparts.product.entity.Product;
import com.motoparts.product.service.ProductService;
import com.motoparts.response.ApiResponse;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {

		this.productService = productService;
	}

	@PostMapping
	public ApiResponse<Product> createProduct(@RequestBody ProductRequest request) {

		return new ApiResponse<>(true, "Product created successfully", productService.createProduct(request));
	}

	@GetMapping
	public ApiResponse<List<Product>> getAllProducts() {

		return new ApiResponse<>(true, "Products fetched successfully", productService.getAllProducts());
	}

	@GetMapping("/{id}")
	public ApiResponse<Product> getProductById(@PathVariable Long id) {

		return new ApiResponse<>(true, "Product fetched successfully", productService.getProductById(id));
	}
}