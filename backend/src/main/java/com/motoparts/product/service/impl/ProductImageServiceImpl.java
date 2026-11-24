package com.motoparts.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motoparts.exception.BusinessException;
import com.motoparts.exception.ResourceNotFoundException;
import com.motoparts.product.dto.ProductImageRequest;
import com.motoparts.product.dto.ProductImageResponse;
import com.motoparts.product.entity.Product;
import com.motoparts.product.entity.ProductImage;
import com.motoparts.product.repository.ProductImageRepository;
import com.motoparts.product.repository.ProductRepository;
import com.motoparts.product.service.ProductImageService;
import com.motoparts.seller.entity.SellerProfile;
import com.motoparts.seller.repository.SellerProfileRepository;
import com.motoparts.user.entity.User;
import com.motoparts.user.repository.UserRepository;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	private final ProductRepository productRepository;

	private final ProductImageRepository productImageRepository;
	
	private final UserRepository userRepository;

	private final SellerProfileRepository sellerProfileRepository;
	
	public ProductImageServiceImpl(ProductRepository productRepository, ProductImageRepository productImageRepository, UserRepository userRepository, SellerProfileRepository sellerProfileRepository) {

		this.productRepository = productRepository;
		this.productImageRepository = productImageRepository;
		this.userRepository = userRepository;
		this.sellerProfileRepository = sellerProfileRepository;
	}

	private SellerProfile getAuthenticatedSeller(String email) {

		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return sellerProfileRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Seller profile not found"));
	}

	@Override
	public ProductImageResponse addImage(Long productId, ProductImageRequest request, String email) {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		SellerProfile seller = getAuthenticatedSeller(email);

		if (!product.getSeller().getId().equals(seller.getId())) {

			throw new BusinessException("You can add images only to your own products");
		}

		ProductImage image = new ProductImage();

		image.setProduct(product);

		image.setImageUrl(request.getImageUrl());

		image.setAltText(request.getAltText());

		image.setDisplayOrder(request.getDisplayOrder());

		image.setPrimaryImage(request.getPrimaryImage());

		ProductImage saved = productImageRepository.save(image);

		return mapToResponse(saved);
	}

	@Override
	public List<ProductImageResponse> getImages(Long productId) {

		return productImageRepository.findByProductIdOrderByDisplayOrderAsc(productId).stream().map(this::mapToResponse)
				.toList();
	}

	@Override
	public void deleteImage(Long productId, Long imageId, String email) {

		ProductImage image = productImageRepository.findById(imageId)
				.orElseThrow(() -> new ResourceNotFoundException("Image not found"));

		if (!image.getProduct().getId().equals(productId)) {

			throw new BusinessException("Image does not belong to product");
		}

		productImageRepository.delete(image);
	}

	private ProductImageResponse mapToResponse(ProductImage image) {

		ProductImageResponse response = new ProductImageResponse();

		response.setId(image.getId());

		response.setImageUrl(image.getImageUrl());

		response.setAltText(image.getAltText());

		response.setDisplayOrder(image.getDisplayOrder());

		response.setPrimaryImage(image.getPrimaryImage());

		return response;
	}
}