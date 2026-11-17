package com.motoparts.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsBySku(String sku);
}