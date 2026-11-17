package com.motoparts.vehicle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.vehicle.entity.VehicleModel;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {

	List<VehicleModel> findByMakeId(Long makeId);

	List<VehicleModel> findByMakeIdAndActiveTrue(Long makeId);
}