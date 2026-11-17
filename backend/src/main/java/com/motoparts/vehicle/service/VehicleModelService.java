package com.motoparts.vehicle.service;

import java.util.List;

import com.motoparts.vehicle.dto.VehicleModelRequest;
import com.motoparts.vehicle.entity.VehicleModel;

public interface VehicleModelService {

	VehicleModel createModel(VehicleModelRequest request);

	List<VehicleModel> getModelsByMake(Long makeId);

	VehicleModel getModelById(Long id);

	VehicleModel updateModel(Long id, VehicleModelRequest request);

	VehicleModel updateModelStatus(Long id, Boolean active);
}
