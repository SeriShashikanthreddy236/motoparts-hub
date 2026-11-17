package com.motoparts.vehicle.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motoparts.vehicle.constant.FuelType;
import com.motoparts.vehicle.constant.TransmissionType;
import com.motoparts.vehicle.dto.VehicleVariantRequest;
import com.motoparts.vehicle.entity.VehicleModel;
import com.motoparts.vehicle.entity.VehicleVariant;
import com.motoparts.vehicle.repository.VehicleModelRepository;
import com.motoparts.vehicle.repository.VehicleVariantRepository;
import com.motoparts.vehicle.service.VehicleVariantService;

@Service
public class VehicleVariantServiceImpl implements VehicleVariantService {

	private final VehicleVariantRepository vehicleVariantRepository;
	private final VehicleModelRepository vehicleModelRepository;

	public VehicleVariantServiceImpl(VehicleVariantRepository vehicleVariantRepository,
			VehicleModelRepository vehicleModelRepository) {

		this.vehicleVariantRepository = vehicleVariantRepository;
		this.vehicleModelRepository = vehicleModelRepository;
	}

	@Override
	public VehicleVariant createVariant(VehicleVariantRequest request) {

		VehicleModel model = vehicleModelRepository.findById(request.getModelId())
				.orElseThrow(() -> new RuntimeException("Vehicle model not found"));

		VehicleVariant variant = new VehicleVariant();

		variant.setName(request.getName());
		variant.setEngineCapacityCc(request.getEngineCapacityCc());

		variant.setFuelType(FuelType.valueOf(request.getFuelType()));

		variant.setTransmission(TransmissionType.valueOf(request.getTransmission()));

		variant.setStartYear(request.getStartYear());

		variant.setEndYear(request.getEndYear());

		variant.setActive(true);
		variant.setModel(model);

		return vehicleVariantRepository.save(variant);
	}

	@Override
	public List<VehicleVariant> getVariantsByModel(Long modelId) {

		return vehicleVariantRepository.findByModelIdAndActiveTrue(modelId);
	}

	@Override
	public VehicleVariant getVariantById(Long id) {

		return vehicleVariantRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle variant not found"));
	}

	@Override
	public VehicleVariant updateVariant(Long id, VehicleVariantRequest request) {

		VehicleVariant variant = vehicleVariantRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle variant not found"));

		variant.setName(request.getName());
		variant.setEngineCapacityCc(request.getEngineCapacityCc());

		variant.setFuelType(FuelType.valueOf(request.getFuelType()));

		variant.setTransmission(TransmissionType.valueOf(request.getTransmission()));

		variant.setStartYear(request.getStartYear());

		variant.setEndYear(request.getEndYear());

		return vehicleVariantRepository.save(variant);
	}

	@Override
	public VehicleVariant updateVariantStatus(Long id, Boolean active) {

		VehicleVariant variant = vehicleVariantRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle variant not found"));

		variant.setActive(active);

		return vehicleVariantRepository.save(variant);
	}
}