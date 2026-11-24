package com.motoparts.seller.controller;

import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.response.ApiResponse;
import com.motoparts.seller.dto.SellerApplicationRequest;
import com.motoparts.seller.dto.SellerResponse;
import com.motoparts.seller.service.SellerService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/sellers")
@Validated
@Tag(name = "Seller Profiles", description = "Apply to become a seller and maintain the seller profile")
public class SellerController {

	private final SellerService sellerService;

	public SellerController(SellerService sellerService) {

		this.sellerService = sellerService;
	}

	@PostMapping("/apply")
	@Operation(summary = "Submit a seller application", description = "Creates a seller application for the authenticated account.")
	public ApiResponse<SellerResponse> applyAsSeller(@Valid @RequestBody SellerApplicationRequest request,
			Authentication authentication) {

		SellerResponse response = sellerService.applyAsSeller(request, authentication.getName());

		return new ApiResponse<>(true, "Seller application submitted successfully", response);
	}

	@GetMapping("/me")
	@Operation(summary = "Get my seller profile", description = "Retrieves the authenticated seller's profile and application status.")
	public ApiResponse<SellerResponse> getMyProfile(Authentication authentication) {

		SellerResponse response = sellerService.getMySellerProfile(authentication.getName());

		return new ApiResponse<>(true, "Seller profile retrieved successfully", response);
	}

	@PutMapping("/me")
	@Operation(summary = "Update my seller profile", description = "Updates business and contact information for the authenticated seller.")
	public ApiResponse<SellerResponse> updateMyProfile(@Valid @RequestBody SellerApplicationRequest request,
			Authentication authentication) {

		SellerResponse response = sellerService.updateMySellerProfile(request, authentication.getName());

		return new ApiResponse<>(true, "Seller profile updated successfully", response);
	}
}