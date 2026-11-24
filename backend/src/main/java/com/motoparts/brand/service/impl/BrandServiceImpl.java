package com.motoparts.brand.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.motoparts.brand.dto.BrandRequest;
import com.motoparts.brand.dto.BrandResponse;
import com.motoparts.brand.entity.Brand;
import com.motoparts.brand.repository.BrandRepository;
import com.motoparts.brand.service.BrandService;
import com.motoparts.exception.BusinessException;
import com.motoparts.exception.ResourceNotFoundException;

@Service
public class BrandServiceImpl implements BrandService {

	private final BrandRepository brandRepository;

	public BrandServiceImpl(BrandRepository brandRepository) {

		this.brandRepository = brandRepository;
	}

	@Override
	public BrandResponse createBrand(BrandRequest request) {

		if (brandRepository.existsByName(request.getName())) {

			throw new BusinessException("Brand already exists");
		}

		Brand brand = new Brand();

		brand.setName(request.getName());
		brand.setCode(request.getCode());
		brand.setLogoUrl(request.getLogoUrl());
		brand.setDescription(request.getDescription());

		Brand savedBrand = brandRepository.save(brand);

		return mapToResponse(savedBrand);
	}

	@Override
	public List<BrandResponse> getAllBrands() {

		return brandRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public BrandResponse getBrandById(Long id) {

		Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

		return mapToResponse(brand);
	}

	private BrandResponse mapToResponse(Brand brand) {

		BrandResponse response = new BrandResponse();

		response.setId(brand.getId());
		response.setName(brand.getName());
		response.setCode(brand.getCode());
		response.setLogoUrl(brand.getLogoUrl());
		response.setDescription(brand.getDescription());
		response.setActive(brand.getActive());

		return response;
	}
}