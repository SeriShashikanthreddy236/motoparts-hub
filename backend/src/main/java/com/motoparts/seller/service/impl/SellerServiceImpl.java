package com.motoparts.seller.service.impl;

import org.springframework.stereotype.Service;

import com.motoparts.exception.BusinessException;
import com.motoparts.exception.ResourceNotFoundException;
import com.motoparts.seller.dto.SellerApplicationRequest;
import com.motoparts.seller.dto.SellerResponse;
import com.motoparts.seller.entity.SellerProfile;
import com.motoparts.seller.enums.SellerStatus;
import com.motoparts.seller.repository.SellerProfileRepository;
import com.motoparts.seller.service.SellerService;
import com.motoparts.user.entity.User;
import com.motoparts.user.repository.UserRepository;

@Service
public class SellerServiceImpl implements SellerService {

	private final SellerProfileRepository sellerProfileRepository;

	private final UserRepository userRepository;

	public SellerServiceImpl(SellerProfileRepository sellerProfileRepository, UserRepository userRepository) {

		this.sellerProfileRepository = sellerProfileRepository;
		this.userRepository = userRepository;
	}

	@Override
	public SellerResponse applyAsSeller(SellerApplicationRequest request, String email) {

		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		if (sellerProfileRepository.existsByUser(user)) {
			throw new BusinessException("Seller profile already exists");
		}

		SellerProfile sellerProfile = new SellerProfile();

		sellerProfile.setUser(user);
		sellerProfile.setBusinessName(request.getBusinessName());
		sellerProfile.setBusinessEmail(request.getBusinessEmail());
		sellerProfile.setBusinessPhone(request.getBusinessPhone());
		sellerProfile.setGstNumber(request.getGstNumber());
		sellerProfile.setBusinessAddress(request.getBusinessAddress());
		sellerProfile.setCity(request.getCity());
		sellerProfile.setState(request.getState());
		sellerProfile.setPincode(request.getPincode());

		sellerProfile.setVerificationStatus(SellerStatus.PENDING);

		SellerProfile savedSeller = sellerProfileRepository.save(sellerProfile);

		SellerResponse response = new SellerResponse();

		response.setId(savedSeller.getId());
		response.setBusinessName(savedSeller.getBusinessName());
		response.setBusinessEmail(savedSeller.getBusinessEmail());
		response.setBusinessPhone(savedSeller.getBusinessPhone());
		response.setGstNumber(savedSeller.getGstNumber());
		response.setBusinessAddress(savedSeller.getBusinessAddress());
		response.setCity(savedSeller.getCity());
		response.setState(savedSeller.getState());
		response.setPincode(savedSeller.getPincode());
		response.setVerificationStatus(savedSeller.getVerificationStatus());
		response.setRejectionReason(savedSeller.getRejectionReason());

		return response;
	}

	private SellerResponse mapToResponse(SellerProfile seller) {

		SellerResponse response = new SellerResponse();

		response.setId(seller.getId());
		response.setBusinessName(seller.getBusinessName());
		response.setBusinessEmail(seller.getBusinessEmail());
		response.setBusinessPhone(seller.getBusinessPhone());
		response.setGstNumber(seller.getGstNumber());
		response.setBusinessAddress(seller.getBusinessAddress());
		response.setCity(seller.getCity());
		response.setState(seller.getState());
		response.setPincode(seller.getPincode());
		response.setVerificationStatus(seller.getVerificationStatus());
		response.setRejectionReason(seller.getRejectionReason());

		return response;
	}

	@Override
	public SellerResponse getMySellerProfile(String email) {

		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		SellerProfile seller = sellerProfileRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Seller profile not found"));

		return mapToResponse(seller);
	}

	@Override
	public SellerResponse updateMySellerProfile(SellerApplicationRequest request, String email) {

		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		SellerProfile seller = sellerProfileRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Seller profile not found"));

		seller.setBusinessName(request.getBusinessName());

		seller.setBusinessEmail(request.getBusinessEmail());

		seller.setBusinessPhone(request.getBusinessPhone());

		seller.setGstNumber(request.getGstNumber());

		seller.setBusinessAddress(request.getBusinessAddress());

		seller.setCity(request.getCity());

		seller.setState(request.getState());

		seller.setPincode(request.getPincode());

		SellerProfile updatedSeller = sellerProfileRepository.save(seller);

		return mapToResponse(updatedSeller);
	}

	@Override
	public SellerResponse approveSeller(Long sellerId) {

		SellerProfile seller = sellerProfileRepository.findById(sellerId)
				.orElseThrow(() -> new ResourceNotFoundException("Seller not found"));

		seller.setVerificationStatus(SellerStatus.APPROVED);

		seller.setRejectionReason(null);

		SellerProfile updatedSeller = sellerProfileRepository.save(seller);

		return mapToResponse(updatedSeller);
	}

	@Override
	public SellerResponse rejectSeller(Long sellerId, String reason) {

		SellerProfile seller = sellerProfileRepository.findById(sellerId)
				.orElseThrow(() -> new ResourceNotFoundException("Seller not found"));

		seller.setVerificationStatus(SellerStatus.REJECTED);

		seller.setRejectionReason(reason);

		SellerProfile updatedSeller = sellerProfileRepository.save(seller);

		return mapToResponse(updatedSeller);
	}
}