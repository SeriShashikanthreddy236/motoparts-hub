package com.motoparts.vehicle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.vehicle.entity.VehicleMake;

public interface VehicleMakeRepository extends JpaRepository<VehicleMake, Long> {

	boolean existsByCode(String code);

	List<VehicleMake> findByActiveTrue();
}