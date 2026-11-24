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

@RestController
@RequestMapping("/api/v1/vehicle-makes")
public class VehicleMakeController {

	private final VehicleMakeService vehicleMakeService;

	public VehicleMakeController(VehicleMakeService vehicleMakeService) {

		this.vehicleMakeService = vehicleMakeService;
	}

	@PostMapping
	public ApiResponse<VehicleMake> createMake(@Valid @RequestBody VehicleMakeRequest request) {

		VehicleMake make = vehicleMakeService.createMake(request);

		return new ApiResponse<>(true, "Vehicle make created successfully", make);
	}

	@GetMapping
	public ApiResponse<List<VehicleMake>> getAllMakes() {

		return new ApiResponse<>(true, "Vehicle makes fetched successfully", vehicleMakeService.getAllMakes());
	}

	@GetMapping("/{id}")
	public ApiResponse<VehicleMake> getMakeById(@PathVariable Long id) {

		return new ApiResponse<>(true, "Vehicle make fetched successfully", vehicleMakeService.getMakeById(id));
	}

	@PutMapping("/{id}")
	public ApiResponse<VehicleMake> updateMake(@PathVariable Long id, @Valid @RequestBody VehicleMakeRequest request) {

		return new ApiResponse<>(true, "Vehicle make updated successfully", vehicleMakeService.updateMake(id, request));
	}

	@PatchMapping("/{id}/status")
	public ApiResponse<VehicleMake> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusRequest request) {

		return new ApiResponse<>(true, "Vehicle make status updated successfully",
				vehicleMakeService.updateMakeStatus(id, request.getActive()));
	}
}