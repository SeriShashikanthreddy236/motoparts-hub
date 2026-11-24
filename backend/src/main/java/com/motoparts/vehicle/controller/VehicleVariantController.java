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
import com.motoparts.vehicle.dto.VehicleVariantRequest;
import com.motoparts.vehicle.entity.VehicleVariant;
import com.motoparts.vehicle.service.VehicleVariantService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/vehicle-variants")
@Tag(name = "Vehicle Variants", description = "Manage specific vehicle variants used to determine product compatibility")
public class VehicleVariantController {

	private final VehicleVariantService vehicleVariantService;

	public VehicleVariantController(VehicleVariantService vehicleVariantService) {

		this.vehicleVariantService = vehicleVariantService;
	}

	@PostMapping
	@Operation(summary = "Create a vehicle variant", description = "Adds a specific variant, trim, engine, or configuration under a vehicle model.")
	public ApiResponse<VehicleVariant> createVariant(@Valid @RequestBody VehicleVariantRequest request) {

		return new ApiResponse<>(true, "Vehicle variant created successfully",
				vehicleVariantService.createVariant(request));
	}

	@GetMapping
	@Operation(summary = "List variants by model", description = "Returns vehicle variants belonging to the supplied model ID.")
	public ApiResponse<List<VehicleVariant>> getVariantsByModel(@RequestParam Long modelId) {

		return new ApiResponse<>(true, "Vehicle variants fetched successfully",
				vehicleVariantService.getVariantsByModel(modelId));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get vehicle variant", description = "Retrieves a vehicle variant by ID.")
	public ApiResponse<VehicleVariant> getVariantById(@PathVariable Long id) {

		return new ApiResponse<>(true, "Vehicle variant fetched successfully",
				vehicleVariantService.getVariantById(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a vehicle variant", description = "Updates a vehicle variant's details.")
	public ApiResponse<VehicleVariant> updateVariant(@PathVariable Long id,
			@Valid @RequestBody VehicleVariantRequest request) {

		return new ApiResponse<>(true, "Vehicle variant updated successfully",
				vehicleVariantService.updateVariant(id, request));
	}

	@PatchMapping("/{id}/status")
	@Operation(summary = "Update vehicle variant status", description = "Activates or deactivates a vehicle variant.")
	public ApiResponse<VehicleVariant> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusRequest request) {

		return new ApiResponse<>(true, "Vehicle variant status updated successfully",
				vehicleVariantService.updateVariantStatus(id, request.getActive()));
	}
}
