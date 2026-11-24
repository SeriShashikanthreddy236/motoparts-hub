package com.motoparts.admin.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.motoparts.auth.dto.response.UserResponse;
import com.motoparts.exception.ResourceNotFoundException;
import com.motoparts.role.entity.Role;
import com.motoparts.role.enums.RoleName;
import com.motoparts.role.repository.RoleRepository;
import com.motoparts.user.entity.User;
import com.motoparts.user.enums.AccountStatus;
import com.motoparts.user.repository.UserRepository;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	public AdminUserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {

		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public List<UserResponse> getAllUsers() {

		return userRepository.findAll().stream().map(this::mapToResponse).toList();
	}

	@Override
	public UserResponse getUserById(Long userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return mapToResponse(user);
	}

	@Override
	public UserResponse updateAccountStatus(Long userId, String accountStatus) {

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		user.setAccountStatus(AccountStatus.valueOf(accountStatus));

		User savedUser = userRepository.save(user);

		return mapToResponse(savedUser);
	}

	@Override
	public UserResponse updateRoles(Long userId, Set<String> roles) {

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Set<Role> userRoles = new HashSet<>();

		for (String roleName : roles) {

			Role role = roleRepository.findByName(RoleName.valueOf(roleName))
					.orElseThrow(() -> new ResourceNotFoundException("Role not found"));

			userRoles.add(role);
		}

		user.setRoles(userRoles);

		User savedUser = userRepository.save(user);

		return mapToResponse(savedUser);
	}

	private UserResponse mapToResponse(User user) {

		UserResponse response = new UserResponse();

		response.setId(user.getId());

		response.setFirstName(user.getFirstName());

		response.setLastName(user.getLastName());

		response.setEmail(user.getEmail());

		response.setPhoneNumber(user.getPhoneNumber());

		response.setAccountStatus(user.getAccountStatus().name());

		response.setRoles(user.getRoles().stream().map(role -> role.getName().name())
				.collect(java.util.stream.Collectors.toSet()));

		return response;
	}
}
