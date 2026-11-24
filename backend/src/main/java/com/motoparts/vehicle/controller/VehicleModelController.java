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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/vehicle-models")
@Tag(name = "Vehicle Models", description = "Manage vehicle models associated with manufacturers")
public class VehicleModelController {

	private final VehicleModelService vehicleModelService;

	public VehicleModelController(VehicleModelService vehicleModelService) {

		this.vehicleModelService = vehicleModelService;
	}

	@PostMapping
	@Operation(summary = "Create a vehicle model", description = "Adds a vehicle model under a manufacturer.")
	public ApiResponse<VehicleModel> createModel(@Valid @RequestBody VehicleModelRequest request) {

		return new ApiResponse<>(true, "Vehicle model created successfully", vehicleModelService.createModel(request));
	}

	@GetMapping
	@Operation(summary = "List models by make", description = "Returns vehicle models belonging to the supplied manufacturer ID.")
	public ApiResponse<List<VehicleModel>> getModelsByMake(@RequestParam Long makeId) {

		return new ApiResponse<>(true, "Vehicle models fetched successfully",
				vehicleModelService.getModelsByMake(makeId));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get vehicle model", description = "Retrieves a vehicle model by ID.")
	public ApiResponse<VehicleModel> getModelById(@PathVariable Long id) {

		return new ApiResponse<>(true, "Vehicle model fetched successfully", vehicleModelService.getModelById(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a vehicle model", description = "Updates a vehicle model's details.")
	public ApiResponse<VehicleModel> updateModel(@PathVariable Long id, @Valid @RequestBody VehicleModelRequest request) {

		return new ApiResponse<>(true, "Vehicle model updated successfully",
				vehicleModelService.updateModel(id, request));
	}

	@PatchMapping("/{id}/status")
	@Operation(summary = "Update vehicle model status", description = "Activates or deactivates a vehicle model.")
	public ApiResponse<VehicleModel> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusRequest request) {

		return new ApiResponse<>(true, "Vehicle model status updated successfully",
				vehicleModelService.updateModelStatus(id, request.getActive()));
	}
}