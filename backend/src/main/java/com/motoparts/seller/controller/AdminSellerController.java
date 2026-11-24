package com.motoparts.seller.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.response.ApiResponse;
import com.motoparts.seller.dto.SellerRejectionRequest;
import com.motoparts.seller.dto.SellerResponse;
import com.motoparts.seller.service.SellerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/sellers")
@PreAuthorize("hasRole('ADMIN')")
public class AdminSellerController {

	private final SellerService sellerService;

	public AdminSellerController(SellerService sellerService) {

		this.sellerService = sellerService;
	}

	@PatchMapping("/{id}/approve")
	public ApiResponse<SellerResponse> approveSeller(@PathVariable Long id) {

		SellerResponse response = sellerService.approveSeller(id);

		return new ApiResponse<>(true, "Seller approved successfully", response);
	}

	@PatchMapping("/{id}/reject")
	public ApiResponse<SellerResponse> rejectSeller(@PathVariable Long id,
			@Valid @RequestBody SellerRejectionRequest request) {

		SellerResponse response = sellerService.rejectSeller(id, request.getReason());

		return new ApiResponse<>(true, "Seller rejected successfully", response);
	}
}
