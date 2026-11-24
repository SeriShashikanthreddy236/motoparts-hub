package com.motoparts.category.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.motoparts.category.dto.CategoryRequest;
import com.motoparts.category.dto.CategoryResponse;
import com.motoparts.category.entity.Category;
import com.motoparts.category.repository.CategoryRepository;
import com.motoparts.category.service.CategoryService;
import com.motoparts.exception.BusinessException;
import com.motoparts.exception.ResourceNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {

		this.categoryRepository = categoryRepository;
	}

	@Override
	public CategoryResponse createCategory(CategoryRequest request) {

		if (categoryRepository.existsByName(request.getName())) {

			throw new BusinessException("Category already exists");
		}

		Category category = new Category();

		category.setName(request.getName());
		category.setSlug(request.getSlug());
		category.setDescription(request.getDescription());

		if (request.getParentId() != null) {

			Category parent = categoryRepository.findById(request.getParentId())
					.orElseThrow(() -> new ResourceNotFoundException("Parent category not found"));

			category.setParent(parent);
		}

		Category savedCategory = categoryRepository.save(category);

		return mapToResponse(savedCategory);
	}

	@Override
	public List<CategoryResponse> getAllCategories() {

		return categoryRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public CategoryResponse getCategoryById(Long id) {

		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		return mapToResponse(category);
	}

	private CategoryResponse mapToResponse(Category category) {

		CategoryResponse response = new CategoryResponse();

		response.setId(category.getId());
		response.setName(category.getName());
		response.setSlug(category.getSlug());
		response.setDescription(category.getDescription());

		response.setActive(category.getActive());

		if (category.getParent() != null) {
			response.setParentId(category.getParent().getId());
		}

		return response;
	}
}