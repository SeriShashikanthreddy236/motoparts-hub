package com.motoparts.vehicle.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.motoparts.response.ApiResponse;
import com.motoparts.vehicle.dto.StatusRequest;
import com.motoparts.vehicle.dto.VehicleModelRequest;
import com.motoparts.vehicle.entity.VehicleModel;
import com.motoparts.vehicle.service.VehicleModelService;

@RestController
@RequestMapping("/api/v1/vehicle-models")
public class VehicleModelController {

	private final VehicleModelService vehicleModelService;

	public VehicleModelController(VehicleModelService vehicleModelService) {

		this.vehicleModelService = vehicleModelService;
	}

	@PostMapping
	public ApiResponse<VehicleModel> createModel(@RequestBody VehicleModelRequest request) {

		return new ApiResponse<>(true, "Vehicle model created successfully", vehicleModelService.createModel(request));
	}

	@GetMapping
	public ApiResponse<List<VehicleModel>> getModelsByMake(@RequestParam Long makeId) {

		return new ApiResponse<>(true, "Vehicle models fetched successfully",
				vehicleModelService.getModelsByMake(makeId));
	}

	@GetMapping("/{id}")
	public ApiResponse<VehicleModel> getModelById(@PathVariable Long id) {

		return new ApiResponse<>(true, "Vehicle model fetched successfully", vehicleModelService.getModelById(id));
	}

	@PutMapping("/{id}")
	public ApiResponse<VehicleModel> updateModel(@PathVariable Long id, @RequestBody VehicleModelRequest request) {

		return new ApiResponse<>(true, "Vehicle model updated successfully",
				vehicleModelService.updateModel(id, request));
	}

	@PatchMapping("/{id}/status")
	public ApiResponse<VehicleModel> updateStatus(@PathVariable Long id, @RequestBody StatusRequest request) {

		return new ApiResponse<>(true, "Vehicle model status updated successfully",
				vehicleModelService.updateModelStatus(id, request.getActive()));
	}
}