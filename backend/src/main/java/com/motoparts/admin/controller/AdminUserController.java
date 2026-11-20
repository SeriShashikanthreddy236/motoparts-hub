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

@RestController
@RequestMapping("/api/v1/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

	private final AdminUserService adminUserService;

	public AdminUserController(AdminUserService adminUserService) {

		this.adminUserService = adminUserService;
	}

	@GetMapping
	public ApiResponse<List<UserResponse>> getAllUsers() {

		return new ApiResponse<>(true, "Users retrieved successfully", adminUserService.getAllUsers());
	}

	@GetMapping("/{id}")
	public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {

		return new ApiResponse<>(true, "User retrieved successfully", adminUserService.getUserById(id));
	}

	@PatchMapping("/{id}/status")
	public ApiResponse<UserResponse> updateAccountStatus(@PathVariable Long id,

			@RequestBody UpdateAccountStatusRequest request) {

		return new ApiResponse<>(true, "User status updated successfully",
				adminUserService.updateAccountStatus(id, request.getAccountStatus()));
	}

	@PatchMapping("/{id}/roles")
	public ApiResponse<UserResponse> updateRoles(@PathVariable Long id,

			@RequestBody UpdateRolesRequest request) {

		return new ApiResponse<>(true, "User roles updated successfully",
				adminUserService.updateRoles(id, request.getRoles()));
	}

}