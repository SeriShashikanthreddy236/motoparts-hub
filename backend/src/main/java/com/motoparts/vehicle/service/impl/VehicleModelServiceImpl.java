package com.motoparts.vehicle.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motoparts.vehicle.constant.VehicleType;
import com.motoparts.vehicle.dto.VehicleModelRequest;
import com.motoparts.vehicle.entity.VehicleMake;
import com.motoparts.vehicle.entity.VehicleModel;
import com.motoparts.vehicle.repository.VehicleMakeRepository;
import com.motoparts.vehicle.repository.VehicleModelRepository;
import com.motoparts.vehicle.service.VehicleModelService;

@Service
public class VehicleModelServiceImpl implements VehicleModelService {

	private final VehicleModelRepository vehicleModelRepository;
	private final VehicleMakeRepository vehicleMakeRepository;

	public VehicleModelServiceImpl(VehicleModelRepository vehicleModelRepository,
			VehicleMakeRepository vehicleMakeRepository) {

		this.vehicleModelRepository = vehicleModelRepository;
		this.vehicleMakeRepository = vehicleMakeRepository;
	}

	@Override
	public VehicleModel createModel(VehicleModelRequest request) {

		VehicleMake make = vehicleMakeRepository.findById(request.getMakeId())
				.orElseThrow(() -> new RuntimeException("Vehicle make not found"));

		VehicleModel model = new VehicleModel();

		model.setName(request.getName());
		model.setCode(request.getCode());
		model.setVehicleType(VehicleType.valueOf(request.getVehicleType()));
		model.setMake(make);
		model.setActive(true);

		return vehicleModelRepository.save(model);
	}

	@Override
	public List<VehicleModel> getModelsByMake(Long makeId) {

		return vehicleModelRepository.findByMakeIdAndActiveTrue(makeId);
	}

	@Override
	public VehicleModel getModelById(Long id) {

		return vehicleModelRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle model not found"));
	}

	@Override
	public VehicleModel updateModel(Long id, VehicleModelRequest request) {

		VehicleModel model = vehicleModelRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle model not found"));

		model.setName(request.getName());
		model.setCode(request.getCode());

		model.setVehicleType(VehicleType.valueOf(request.getVehicleType()));

		return vehicleModelRepository.save(model);
	}

	@Override
	public VehicleModel updateModelStatus(Long id, Boolean active) {

		VehicleModel model = vehicleModelRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle model not found"));

		model.setActive(active);

		return vehicleModelRepository.save(model);
	}
}
