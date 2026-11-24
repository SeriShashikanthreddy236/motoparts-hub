package com.motoparts.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motoparts.exception.BusinessException;
import com.motoparts.exception.ResourceNotFoundException;
import com.motoparts.product.dto.ProductFitmentRequest;
import com.motoparts.product.dto.ProductResponse;
import com.motoparts.product.entity.Product;
import com.motoparts.product.entity.ProductFitment;
import com.motoparts.product.repository.ProductFitmentRepository;
import com.motoparts.product.repository.ProductRepository;
import com.motoparts.product.service.ProductFitmentService;
import com.motoparts.vehicle.entity.VehicleVariant;
import com.motoparts.vehicle.repository.VehicleVariantRepository;

@Service
public class ProductFitmentServiceImpl implements ProductFitmentService {

	private final ProductRepository productRepository;
	private final VehicleVariantRepository vehicleVariantRepository;
	private final ProductFitmentRepository productFitmentRepository;

	public ProductFitmentServiceImpl(ProductRepository productRepository,
			VehicleVariantRepository vehicleVariantRepository, ProductFitmentRepository productFitmentRepository) {

		this.productRepository = productRepository;
		this.vehicleVariantRepository = vehicleVariantRepository;
		this.productFitmentRepository = productFitmentRepository;
	}

	@Override
	public ProductFitment createFitment(ProductFitmentRequest request) {

		Product product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		VehicleVariant variant = vehicleVariantRepository.findById(request.getVehicleVariantId())
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle variant not found"));

		if (request.getMinYear() > request.getMaxYear()) {

			throw new BusinessException("Invalid year range");
		}

		if (productFitmentRepository.existsByProductIdAndVehicleVariantIdAndMinYearAndMaxYear(request.getProductId(),
				request.getVehicleVariantId(), request.getMinYear(), request.getMaxYear())) {

			throw new BusinessException("Fitment already exists");
		}

		ProductFitment fitment = new ProductFitment();

		fitment.setProduct(product);
		fitment.setVehicleVariant(variant);
		fitment.setMinYear(request.getMinYear());
		fitment.setMaxYear(request.getMaxYear());
		fitment.setNotes(request.getNotes());

		return productFitmentRepository.save(fitment);
	}

	@Override
	public List<ProductFitment> getFitmentsByProduct(Long productId) {

		return productFitmentRepository.findByProductId(productId);
	}

	@Override
	public List<ProductFitment> getFitmentsByVariant(Long variantId) {

		return productFitmentRepository.findByVehicleVariantId(variantId);
	}

	@Override
	public void deleteFitment(Long id) {

		ProductFitment fitment = productFitmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Fitment not found"));

		productFitmentRepository.delete(fitment);
	}

}