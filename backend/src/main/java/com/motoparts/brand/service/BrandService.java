package com.motoparts.brand.service;

import java.util.List;

import com.motoparts.brand.dto.BrandRequest;
import com.motoparts.brand.dto.BrandResponse;

public interface BrandService {

	BrandResponse createBrand(BrandRequest request);

	List<BrandResponse> getAllBrands();

	BrandResponse getBrandById(Long id);
}