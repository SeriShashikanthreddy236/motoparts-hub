package com.motoparts.product.service;

import java.util.List;

import com.motoparts.product.dto.ProductRequest;
import com.motoparts.product.entity.Product;

public interface ProductService {

	Product createProduct(ProductRequest request);

	List<Product> getAllProducts();

	Product getProductById(Long id);
}