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

@RestController
@RequestMapping("/api/v1/sellers")
@Validated
public class SellerController {

	private final SellerService sellerService;

	public SellerController(SellerService sellerService) {

		this.sellerService = sellerService;
	}

	@PostMapping("/apply")
	public ApiResponse<SellerResponse> applyAsSeller(@Valid @RequestBody SellerApplicationRequest request,
			Authentication authentication) {

		SellerResponse response = sellerService.applyAsSeller(request, authentication.getName());

		return new ApiResponse<>(true, "Seller application submitted successfully", response);
	}

	@GetMapping("/me")
	public ApiResponse<SellerResponse> getMyProfile(Authentication authentication) {

		SellerResponse response = sellerService.getMySellerProfile(authentication.getName());

		return new ApiResponse<>(true, "Seller profile retrieved successfully", response);
	}

	@PutMapping("/me")
	public ApiResponse<SellerResponse> updateMyProfile(@Valid @RequestBody SellerApplicationRequest request,
			Authentication authentication) {

		SellerResponse response = sellerService.updateMySellerProfile(request, authentication.getName());

		return new ApiResponse<>(true, "Seller profile updated successfully", response);
	}
}