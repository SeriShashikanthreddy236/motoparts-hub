package com.motoparts.product.service;

import java.util.List;

import com.motoparts.product.dto.ProductSpecificationRequest;
import com.motoparts.product.dto.ProductSpecificationResponse;

public interface ProductSpecificationService {

	ProductSpecificationResponse addSpecification(Long productId, ProductSpecificationRequest request);

	List<ProductSpecificationResponse> getSpecifications(Long productId);

	void deleteSpecification(Long id);
}