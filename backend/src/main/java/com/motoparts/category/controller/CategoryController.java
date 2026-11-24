package com.motoparts.category.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.category.dto.CategoryRequest;
import com.motoparts.category.dto.CategoryResponse;
import com.motoparts.category.service.CategoryService;
import com.motoparts.response.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {

		this.categoryService = categoryService;
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {

		CategoryResponse response = categoryService.createCategory(request);

		return new ApiResponse<>(true, "Category created successfully", response);
	}

	@GetMapping
	public ApiResponse<List<CategoryResponse>> getAllCategories() {

		return new ApiResponse<>(true, "Categories retrieved successfully", categoryService.getAllCategories());
	}

	@GetMapping("/{id}")
	public ApiResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {

		return new ApiResponse<>(true, "Category retrieved successfully", categoryService.getCategoryById(id));
	}
}