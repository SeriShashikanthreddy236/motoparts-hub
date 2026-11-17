package com.motoparts.vehicle.service;

import java.util.List;

import com.motoparts.vehicle.dto.VehicleMakeRequest;
import com.motoparts.vehicle.entity.VehicleMake;

public interface VehicleMakeService {

	VehicleMake createMake(VehicleMakeRequest request);

	List<VehicleMake> getAllMakes();

	VehicleMake getMakeById(Long id);

	VehicleMake updateMake(Long id, VehicleMakeRequest request);

	VehicleMake updateMakeStatus(Long id, Boolean active);
}