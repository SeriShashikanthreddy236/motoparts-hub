package com.motoparts.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.motoparts.product.entity.Product;
import com.motoparts.seller.entity.SellerProfile;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

	boolean existsBySku(String sku);

	List<Product> findBySellerId(Long sellerId);

	List<Product> findBySeller(SellerProfile seller);

	boolean existsBySellerAndSku(SellerProfile seller, String sku);
}