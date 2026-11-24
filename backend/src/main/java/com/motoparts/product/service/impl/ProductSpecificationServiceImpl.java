package com.motoparts.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motoparts.exception.ResourceNotFoundException;
import com.motoparts.product.dto.ProductSpecificationRequest;
import com.motoparts.product.dto.ProductSpecificationResponse;
import com.motoparts.product.entity.Product;
import com.motoparts.product.entity.ProductSpecification;
import com.motoparts.product.repository.ProductRepository;
import com.motoparts.product.repository.ProductSpecificationRepository;
import com.motoparts.product.service.ProductSpecificationService;

@Service
public class ProductSpecificationServiceImpl
        implements ProductSpecificationService {

    private final ProductRepository productRepository;
    private final ProductSpecificationRepository specificationRepository;

    public ProductSpecificationServiceImpl(
            ProductRepository productRepository,
            ProductSpecificationRepository specificationRepository) {

        this.productRepository = productRepository;
        this.specificationRepository = specificationRepository;
    }

    @Override
    public ProductSpecificationResponse addSpecification(
            Long productId,
            ProductSpecificationRequest request) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        ProductSpecification specification =
                new ProductSpecification();

        specification.setProduct(product);
        specification.setSpecificationName(
                request.getSpecificationName());

        specification.setSpecificationValue(
                request.getSpecificationValue());

        ProductSpecification saved =
                specificationRepository.save(specification);

        return map(saved);
    }

    @Override
    public List<ProductSpecificationResponse> getSpecifications(
            Long productId) {

        return specificationRepository
                .findByProductId(productId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public void deleteSpecification(Long id) {

        ProductSpecification specification =
                specificationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Specification not found"));

        specificationRepository.delete(specification);
    }

    private ProductSpecificationResponse map(
            ProductSpecification specification) {

        ProductSpecificationResponse response =
                new ProductSpecificationResponse();

        response.setId(specification.getId());

        response.setSpecificationName(
                specification.getSpecificationName());

        response.setSpecificationValue(
                specification.getSpecificationValue());

        return response;
    }
}