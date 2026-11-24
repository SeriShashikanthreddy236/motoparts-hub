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

@RestController
@RequestMapping("/api/v1/vehicle-variants")
public class VehicleVariantController {

	private final VehicleVariantService vehicleVariantService;

	public VehicleVariantController(VehicleVariantService vehicleVariantService) {

		this.vehicleVariantService = vehicleVariantService;
	}

	@PostMapping
	public ApiResponse<VehicleVariant> createVariant(@Valid @RequestBody VehicleVariantRequest request) {

		return new ApiResponse<>(true, "Vehicle variant created successfully",
				vehicleVariantService.createVariant(request));
	}

	@GetMapping
	public ApiResponse<List<VehicleVariant>> getVariantsByModel(@RequestParam Long modelId) {

		return new ApiResponse<>(true, "Vehicle variants fetched successfully",
				vehicleVariantService.getVariantsByModel(modelId));
	}

	@GetMapping("/{id}")
	public ApiResponse<VehicleVariant> getVariantById(@PathVariable Long id) {

		return new ApiResponse<>(true, "Vehicle variant fetched successfully",
				vehicleVariantService.getVariantById(id));
	}

	@PutMapping("/{id}")
	public ApiResponse<VehicleVariant> updateVariant(@PathVariable Long id,
			@Valid @RequestBody VehicleVariantRequest request) {

		return new ApiResponse<>(true, "Vehicle variant updated successfully",
				vehicleVariantService.updateVariant(id, request));
	}

	@PatchMapping("/{id}/status")
	public ApiResponse<VehicleVariant> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusRequest request) {

		return new ApiResponse<>(true, "Vehicle variant status updated successfully",
				vehicleVariantService.updateVariantStatus(id, request.getActive()));
	}
}
