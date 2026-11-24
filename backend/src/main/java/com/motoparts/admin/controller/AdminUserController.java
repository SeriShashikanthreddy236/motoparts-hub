package com.motoparts.admin.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.admin.dto.UpdateAccountStatusRequest;
import com.motoparts.admin.dto.UpdateRolesRequest;
import com.motoparts.admin.service.AdminUserService;
import com.motoparts.auth.dto.response.UserResponse;
import com.motoparts.response.ApiResponse;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/admin/users")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "User Administration", description = "Administrator operations for listing users and managing account status and roles")
public class AdminUserController {

	private final AdminUserService adminUserService;

	public AdminUserController(AdminUserService adminUserService) {

		this.adminUserService = adminUserService;
	}

	@GetMapping
	@Operation(summary = "List users", description = "Returns all registered users for administrator review.")
	public ApiResponse<List<UserResponse>> getAllUsers() {

		return new ApiResponse<>(true, "Users retrieved successfully", adminUserService.getAllUsers());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get user details", description = "Retrieves a registered user's account information by user ID.")
	public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {

		return new ApiResponse<>(true, "User retrieved successfully", adminUserService.getUserById(id));
	}

	@PatchMapping("/{id}/status")
	@Operation(summary = "Update account status", description = "Enables or disables a user account.")
	public ApiResponse<UserResponse> updateAccountStatus(@PathVariable Long id,

			@Valid @RequestBody UpdateAccountStatusRequest request) {

		return new ApiResponse<>(true, "User status updated successfully",
				adminUserService.updateAccountStatus(id, request.getAccountStatus()));
	}

	@PatchMapping("/{id}/roles")
	@Operation(summary = "Update user roles", description = "Replaces or updates the role assignments for a user account.")
	public ApiResponse<UserResponse> updateRoles(@PathVariable Long id,

			@Valid @RequestBody UpdateRolesRequest request) {

		return new ApiResponse<>(true, "User roles updated successfully",
				adminUserService.updateRoles(id, request.getRoles()));
	}

}