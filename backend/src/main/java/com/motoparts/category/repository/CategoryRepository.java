package com.motoparts.category.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findBySlug(String slug);

	boolean existsByName(String name);

}