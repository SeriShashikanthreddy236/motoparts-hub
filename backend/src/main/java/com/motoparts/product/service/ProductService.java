package com.motoparts.product.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.motoparts.product.dto.ProductRequest;
import com.motoparts.product.dto.ProductResponse;

public interface ProductService {

	ProductResponse createProduct(ProductRequest request, String email);

	List<ProductResponse> getAllProducts();

	ProductResponse getProductById(Long id);

	/**
	 * Admin only
	 */
	List<ProductResponse> getProductsBySeller(Long sellerId);

	/**
	 * Seller's own products
	 */
	List<ProductResponse> getMyProducts(String email);

	/**
	 * Seller can update own product Admin can update any product
	 */
	ProductResponse updateProduct(Long productId, ProductRequest request, String email);

	/**
	 * Seller can delete own product Admin can delete any product
	 */
	void deleteProduct(Long productId, String email);

	List<ProductResponse> getCompatibleProducts(Long variantId);

	List<ProductResponse> getCompatibleProducts(Long variantId, Integer year);

	Page<ProductResponse> searchProducts(String keyword, Long categoryId, Long brandId, Integer page, Integer size);
}