package com.motoparts.category.service;

import java.util.List;

import com.motoparts.category.dto.CategoryRequest;
import com.motoparts.category.dto.CategoryResponse;

public interface CategoryService {

	CategoryResponse createCategory(CategoryRequest request);

	List<CategoryResponse> getAllCategories();

	CategoryResponse getCategoryById(Long id);
}