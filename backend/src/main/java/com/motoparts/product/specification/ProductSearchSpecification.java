package com.motoparts.product.specification;

import org.springframework.data.jpa.domain.Specification;

import com.motoparts.product.entity.Product;

public class ProductSearchSpecification {

	public static Specification<Product> hasKeyword(String keyword) {

		return (root, query, cb) -> {

			if (keyword == null || keyword.isBlank()) {
				return null;
			}

			return cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
		};
	}

	public static Specification<Product> hasCategory(Long categoryId) {

		return (root, query, cb) -> {

			if (categoryId == null) {
				return null;
			}

			return cb.equal(root.get("category").get("id"), categoryId);
		};
	}

	public static Specification<Product> hasBrand(Long brandId) {

		return (root, query, cb) -> {

			if (brandId == null) {
				return null;
			}

			return cb.equal(root.get("brand").get("id"), brandId);
		};
	}
}