package com.motoparts.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.motoparts.auth.dto.request.LoginRequest;
import com.motoparts.auth.dto.request.LogoutRequest;
import com.motoparts.auth.dto.request.RefreshTokenRequest;
import com.motoparts.auth.dto.request.RegisterRequest;
import com.motoparts.auth.dto.response.AuthResponse;
import com.motoparts.auth.dto.response.RefreshTokenResponse;
import com.motoparts.auth.dto.response.UserResponse;
import com.motoparts.auth.service.AuthService;
import com.motoparts.response.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Registration, login, current-user, token refresh, and logout operations")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {

		this.authService = authService;
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Register a customer", description = "Creates a new user account and returns the created user profile.")
	public ApiResponse<UserResponse> register(@Valid @RequestBody RegisterRequest request) {

		return new ApiResponse<>(true, "User registered successfully", authService.register(request));
	}

	@PostMapping("/login")
	@Operation(summary = "Authenticate a user", description = "Validates credentials and returns access and refresh tokens.")
	public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {

		return new ApiResponse<>(true, "Login successful", authService.login(request));
	}

	@GetMapping("/me")
	@Operation(summary = "Get the current user", description = "Returns the profile of the authenticated user from the supplied JWT.")
	public ApiResponse<UserResponse> me(Authentication authentication) {

		UserResponse user = authService.getCurrentUser(authentication.getName());

		return new ApiResponse<>(true, "Current user retrieved successfully", user);
	}

	@PostMapping("/refresh")
	@Operation(summary = "Refresh access token", description = "Exchanges a valid refresh token for a new access token.")
	public ApiResponse<RefreshTokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {

		return new ApiResponse<>(true, "Token refreshed successfully", authService.refreshToken(request));
	}

	@PostMapping("/logout")
	@Operation(summary = "Logout the current session", description = "Revokes or invalidates the supplied refresh token.")
	public ApiResponse<String> logout(@Valid @RequestBody LogoutRequest request) {

		authService.logout(request);

		return new ApiResponse<>(true, "Logout successful", null);
	}
}