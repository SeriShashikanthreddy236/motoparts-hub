package com.motoparts.category.dto;

public class CategoryRequest {

	private String name;
	private String slug;
	private String description;
	private Long parentId;

	public CategoryRequest() {
	}

	public CategoryRequest(String name, String slug, String description, Long parentId) {
		this.name = name;
		this.slug = slug;
		this.description = description;
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}