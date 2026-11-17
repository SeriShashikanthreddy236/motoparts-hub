package com.motoparts.product.service;

import java.util.List;

import com.motoparts.product.dto.ProductFitmentRequest;
import com.motoparts.product.entity.ProductFitment;

public interface ProductFitmentService {

	ProductFitment createFitment(ProductFitmentRequest request);

	List<ProductFitment> getFitmentsByProduct(Long productId);

	List<ProductFitment> getFitmentsByVariant(Long variantId);

	void deleteFitment(Long id);
}