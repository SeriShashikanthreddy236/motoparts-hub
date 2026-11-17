package com.motoparts.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.product.entity.ProductFitment;

public interface ProductFitmentRepository extends JpaRepository<ProductFitment, Long> {

	List<ProductFitment> findByVehicleVariantId(Long vehicleVariantId);

	List<ProductFitment> findByProductId(Long productId);

	boolean existsByProductIdAndVehicleVariantIdAndMinYearAndMaxYear(Long productId, Long vehicleVariantId,
			Integer minYear, Integer maxYear);
}
