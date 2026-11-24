package com.motoparts.product.service;

import java.util.List;

import com.motoparts.product.dto.ProductImageRequest;
import com.motoparts.product.dto.ProductImageResponse;

public interface ProductImageService {

	ProductImageResponse addImage(Long productId, ProductImageRequest request, String email);

	List<ProductImageResponse> getImages(Long productId);

	void deleteImage(Long productId, Long imageId, String email);
}
