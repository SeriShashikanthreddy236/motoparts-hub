package com.motoparts.vehicle.service;

import java.util.List;

import com.motoparts.vehicle.dto.VehicleVariantRequest;
import com.motoparts.vehicle.entity.VehicleVariant;

public interface VehicleVariantService {

	VehicleVariant createVariant(VehicleVariantRequest request);

	List<VehicleVariant> getVariantsByModel(Long modelId);

	VehicleVariant getVariantById(Long id);

	VehicleVariant updateVariant(Long id, VehicleVariantRequest request);

	VehicleVariant updateVariantStatus(Long id, Boolean active);
}