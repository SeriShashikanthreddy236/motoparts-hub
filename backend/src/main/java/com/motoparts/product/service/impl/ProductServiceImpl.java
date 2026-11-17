package com.motoparts.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motoparts.product.dto.ProductRequest;
import com.motoparts.product.entity.Product;
import com.motoparts.product.repository.ProductRepository;
import com.motoparts.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {

		this.productRepository = productRepository;
	}

	@Override
	public Product createProduct(ProductRequest request) {

		if (productRepository.existsBySku(request.getSku())) {

			throw new RuntimeException("Product SKU already exists");
		}

		Product product = new Product();

		product.setName(request.getName());
		product.setSku(request.getSku());
		product.setDescription(request.getDescription());
		product.setBrand(request.getBrand());
		product.setActive(true);

		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {

		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}
}