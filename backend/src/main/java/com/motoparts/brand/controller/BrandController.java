package com.motoparts.brand.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.brand.dto.BrandRequest;
import com.motoparts.brand.dto.BrandResponse;
import com.motoparts.brand.service.BrandService;
import com.motoparts.response.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

	private final BrandService brandService;

	public BrandController(BrandService brandService) {

		this.brandService = brandService;
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<BrandResponse> createBrand(@Valid @RequestBody BrandRequest request) {

		BrandResponse response = brandService.createBrand(request);

		return new ApiResponse<>(true, "Brand created successfully", response);
	}

	@GetMapping
	public ApiResponse<List<BrandResponse>> getAllBrands() {

		return new ApiResponse<>(true, "Brands retrieved successfully", brandService.getAllBrands());
	}

	@GetMapping("/{id}")
	public ApiResponse<BrandResponse> getBrandById(@PathVariable Long id) {

		return new ApiResponse<>(true, "Brand retrieved successfully", brandService.getBrandById(id));
	}
}