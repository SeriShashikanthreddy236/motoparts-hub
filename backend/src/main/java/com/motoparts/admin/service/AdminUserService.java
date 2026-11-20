package com.motoparts.admin.service;

import java.util.List;
import java.util.Set;

import com.motoparts.auth.dto.response.UserResponse;

public interface AdminUserService {

	List<UserResponse> getAllUsers();

	UserResponse getUserById(Long userId);

	UserResponse updateAccountStatus(Long userId, String accountStatus);

	UserResponse updateRoles(Long userId, Set<String> roles);
}