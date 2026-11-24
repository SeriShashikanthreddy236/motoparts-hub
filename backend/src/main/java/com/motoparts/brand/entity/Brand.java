package com.motoparts.brand.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "brands")
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false, unique = true)
	private String code;

	private String logoUrl;

	private String description;

	@Column(nullable = false)
	private Boolean active = true;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}

	// Generate constructors
	public Brand() {
	}

	public Brand(Long id, String name, String code, String logoUrl, String description, Boolean active,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.logoUrl = logoUrl;
		this.description = description;
		this.active = active;
		this.createdAt = createdAt;
	}

	// Generate getters/setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
