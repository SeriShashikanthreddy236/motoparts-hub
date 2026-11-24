package com.motoparts.seller.service;

import com.motoparts.seller.dto.SellerApplicationRequest;
import com.motoparts.seller.dto.SellerResponse;

public interface SellerService {

	SellerResponse applyAsSeller(SellerApplicationRequest request, String email);

	SellerResponse getMySellerProfile(String email);

	SellerResponse updateMySellerProfile(SellerApplicationRequest request, String email);

	SellerResponse approveSeller(Long sellerId);

	SellerResponse rejectSeller(Long sellerId, String reason);

}