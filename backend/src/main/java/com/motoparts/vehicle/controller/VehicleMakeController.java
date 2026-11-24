package com.motoparts.vehicle.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.response.ApiResponse;
import com.motoparts.vehicle.dto.StatusRequest;
import com.motoparts.vehicle.dto.VehicleMakeRequest;
import com.motoparts.vehicle.entity.VehicleMake;
import com.motoparts.vehicle.service.VehicleMakeService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/vehicle-makes")
@Tag(name = "Vehicle Makes", description = "Manage vehicle manufacturers used for catalog fitment")
public class VehicleMakeController {

	private final VehicleMakeService vehicleMakeService;

	public VehicleMakeController(VehicleMakeService vehicleMakeService) {

		this.vehicleMakeService = vehicleMakeService;
	}

	@PostMapping
	@Operation(summary = "Create a vehicle make", description = "Adds a vehicle manufacturer such as a car or motorcycle brand.")
	public ApiResponse<VehicleMake> createMake(@Valid @RequestBody VehicleMakeRequest request) {

		VehicleMake make = vehicleMakeService.createMake(request);

		return new ApiResponse<>(true, "Vehicle make created successfully", make);
	}

	@GetMapping
	@Operation(summary = "List vehicle makes", description = "Returns all configured vehicle manufacturers.")
	public ApiResponse<List<VehicleMake>> getAllMakes() {

		return new ApiResponse<>(true, "Vehicle makes fetched successfully", vehicleMakeService.getAllMakes());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get vehicle make", description = "Retrieves a vehicle manufacturer by ID.")
	public ApiResponse<VehicleMake> getMakeById(@PathVariable Long id) {

		return new ApiResponse<>(true, "Vehicle make fetched successfully", vehicleMakeService.getMakeById(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a vehicle make", description = "Updates the name or configuration of a vehicle manufacturer.")
	public ApiResponse<VehicleMake> updateMake(@PathVariable Long id, @Valid @RequestBody VehicleMakeRequest request) {

		return new ApiResponse<>(true, "Vehicle make updated successfully", vehicleMakeService.updateMake(id, request));
	}

	@PatchMapping("/{id}/status")
	@Operation(summary = "Update vehicle make status", description = "Activates or deactivates a vehicle manufacturer.")
	public ApiResponse<VehicleMake> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusRequest request) {

		return new ApiResponse<>(true, "Vehicle make status updated successfully",
				vehicleMakeService.updateMakeStatus(id, request.getActive()));
	}
}