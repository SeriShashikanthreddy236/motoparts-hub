package com.motoparts.brand.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.brand.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	Optional<Brand> findByCode(String code);

	boolean existsByName(String name);
}