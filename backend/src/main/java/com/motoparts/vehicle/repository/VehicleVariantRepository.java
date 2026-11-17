package com.motoparts.vehicle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.vehicle.entity.VehicleVariant;

public interface VehicleVariantRepository extends JpaRepository<VehicleVariant, Long> {

	List<VehicleVariant> findByModelId(Long modelId);

	List<VehicleVariant> findByModelIdAndActiveTrue(Long modelId);
}