package com.motoparts.vehicle.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.response.ApiResponse;
import com.motoparts.vehicle.dto.StatusRequest;
import com.motoparts.vehicle.dto.VehicleModelRequest;
import com.motoparts.vehicle.entity.VehicleModel;
import com.motoparts.vehicle.service.VehicleModelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/vehicle-models")
public class VehicleModelController {

	private final VehicleModelService vehicleModelService;

	public VehicleModelController(VehicleModelService vehicleModelService) {

		this.vehicleModelService = vehicleModelService;
	}

	@PostMapping
	public ApiResponse<VehicleModel> createModel(@Valid @RequestBody VehicleModelRequest request) {

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
	public ApiResponse<VehicleModel> updateModel(@PathVariable Long id, @Valid @RequestBody VehicleModelRequest request) {

		return new ApiResponse<>(true, "Vehicle model updated successfully",
				vehicleModelService.updateModel(id, request));
	}

	@PatchMapping("/{id}/status")
	public ApiResponse<VehicleModel> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusRequest request) {

		return new ApiResponse<>(true, "Vehicle model status updated successfully",
				vehicleModelService.updateModelStatus(id, request.getActive()));
	}
}