package com.motoparts.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.motoparts.brand.entity.Brand;
import com.motoparts.brand.repository.BrandRepository;
import com.motoparts.category.entity.Category;
import com.motoparts.category.repository.CategoryRepository;
import com.motoparts.exception.BusinessException;
import com.motoparts.exception.ResourceNotFoundException;
import com.motoparts.product.dto.ProductRequest;
import com.motoparts.product.dto.ProductResponse;
import com.motoparts.product.entity.Product;
import com.motoparts.product.entity.ProductFitment;
import com.motoparts.product.repository.ProductFitmentRepository;
import com.motoparts.product.repository.ProductRepository;
import com.motoparts.product.service.ProductService;
import com.motoparts.product.specification.ProductSearchSpecification;
import com.motoparts.seller.entity.SellerProfile;
import com.motoparts.seller.enums.SellerStatus;
import com.motoparts.seller.repository.SellerProfileRepository;
import com.motoparts.user.entity.User;
import com.motoparts.user.repository.UserRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	private final CategoryRepository categoryRepository;

	private final BrandRepository brandRepository;

	private final SellerProfileRepository sellerProfileRepository;

	private final ProductFitmentRepository productFitmentRepository;

	private final UserRepository userRepository;

	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
			BrandRepository brandRepository, SellerProfileRepository sellerProfileRepository,
			ProductFitmentRepository productFitmentRepository, UserRepository userRepository) {

		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.brandRepository = brandRepository;
		this.sellerProfileRepository = sellerProfileRepository;
		this.productFitmentRepository = productFitmentRepository;
		this.userRepository = userRepository;
	}

	private SellerProfile getAuthenticatedSeller(String email) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return sellerProfileRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Seller profile not found"));
	}

	@Override
	public ProductResponse createProduct(ProductRequest request, String email) {

		if (productRepository.existsBySku(request.getSku())) {
			throw new BusinessException("Product SKU already exists");
		}

		Category category = categoryRepository.findById(request.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		Brand brand = brandRepository.findById(request.getBrandId())
				.orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		SellerProfile seller = sellerProfileRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Seller profile not found"));

		if (seller.getVerificationStatus() != SellerStatus.APPROVED) {
			throw new BusinessException("Only approved sellers can create products");
		}

		Product product = new Product();

		product.setName(request.getName());
		product.setSku(request.getSku());
		product.setDescription(request.getDescription());

		product.setCategory(category);
		product.setBrand(brand);

		product.setSeller(seller);

		product.setPartNumber(request.getPartNumber());

		product.setMrp(request.getMrp());
		product.setSellingPrice(request.getSellingPrice());

		product.setActive(true);

		Product savedProduct = productRepository.save(product);

		return mapToResponse(savedProduct);
	}

	@Override
	public List<ProductResponse> getAllProducts() {

		return productRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public ProductResponse getProductById(Long id) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		return mapToResponse(product);
	}

	private ProductResponse mapToResponse(Product product) {

		ProductResponse response = new ProductResponse();

		response.setId(product.getId());

		response.setName(product.getName());

		response.setSku(product.getSku());

		response.setDescription(product.getDescription());

		response.setPartNumber(product.getPartNumber());

		response.setMrp(product.getMrp());

		response.setSellingPrice(product.getSellingPrice());

		response.setActive(product.getActive());

		if (product.getCategory() != null) {
			response.setCategoryName(product.getCategory().getName());
		}

		if (product.getBrand() != null) {
			response.setBrandName(product.getBrand().getName());
		}

		if (product.getSeller() != null) {
			response.setSellerName(product.getSeller().getBusinessName());
		}

		return response;
	}

	@Override
	public List<ProductResponse> getProductsBySeller(Long sellerId) {

		SellerProfile seller = sellerProfileRepository.findById(sellerId)
				.orElseThrow(() -> new ResourceNotFoundException("Seller not found"));

		return productRepository.findBySeller(seller).stream().map(this::mapToResponse).toList();
	}

	@Override
	public List<ProductResponse> getMyProducts(String email) {

		SellerProfile seller = getAuthenticatedSeller(email);

		return productRepository.findBySellerId(seller.getId()).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public ProductResponse updateProduct(Long productId, ProductRequest request, String email) {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		SellerProfile currentSeller = getAuthenticatedSeller(email);

		if (!product.getSeller().getId().equals(currentSeller.getId())) {

			throw new BusinessException("You can update only your own products");
		}

		Category category = categoryRepository.findById(request.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		Brand brand = brandRepository.findById(request.getBrandId())
				.orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setCategory(category);
		product.setBrand(brand);
		product.setPartNumber(request.getPartNumber());
		product.setMrp(request.getMrp());
		product.setSellingPrice(request.getSellingPrice());

		Product updated = productRepository.save(product);

		return mapToResponse(updated);
	}

	@Override
	public void deleteProduct(Long productId, String email) {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		SellerProfile currentSeller = getAuthenticatedSeller(email);

		if (!product.getSeller().getId().equals(currentSeller.getId())) {

			throw new BusinessException("You can delete only your own products");
		}

		productRepository.delete(product);
	}

	@Override
	public List<ProductResponse> getCompatibleProducts(Long variantId) {

		return productFitmentRepository.findByVehicleVariantId(variantId).stream().map(ProductFitment::getProduct)
				.map(this::mapToResponse).toList();
	}

	@Override
	public Page<ProductResponse> searchProducts(String keyword, Long categoryId, Long brandId, Integer page,
			Integer size) {

		Pageable pageable = PageRequest.of(page, size);

		Specification<Product> specification = Specification.where(ProductSearchSpecification.hasKeyword(keyword))
				.and(ProductSearchSpecification.hasCategory(categoryId))
				.and(ProductSearchSpecification.hasBrand(brandId));

		return productRepository.findAll(specification, pageable).map(this::mapToResponse);
	}

	@Override
	public List<ProductResponse> getCompatibleProducts(
	        Long variantId,
	        Integer year) {

	    return productFitmentRepository
	            .findByVehicleVariantIdAndMinYearLessThanEqualAndMaxYearGreaterThanEqual(
	                    variantId,
	                    year,
	                    year)
	            .stream()
	            .map(ProductFitment::getProduct)
	            .distinct()
	            .map(this::mapToResponse)
	            .toList();
	}
}