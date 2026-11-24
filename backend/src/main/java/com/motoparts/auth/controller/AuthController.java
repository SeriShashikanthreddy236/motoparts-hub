package com.motoparts.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {

		this.authService = authService;
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<UserResponse> register(@Valid @RequestBody RegisterRequest request) {

		return new ApiResponse<>(true, "User registered successfully", authService.register(request));
	}

	@PostMapping("/login")
	public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {

		return new ApiResponse<>(true, "Login successful", authService.login(request));
	}

	@GetMapping("/me")
	public ApiResponse<UserResponse> me(Authentication authentication) {

		UserResponse user = authService.getCurrentUser(authentication.getName());

		return new ApiResponse<>(true, "Current user retrieved successfully", user);
	}

	@PostMapping("/refresh")
	public ApiResponse<RefreshTokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {

		return new ApiResponse<>(true, "Token refreshed successfully", authService.refreshToken(request));
	}

	@PostMapping("/logout")
	public ApiResponse<String> logout(@Valid @RequestBody LogoutRequest request) {

		authService.logout(request);

		return new ApiResponse<>(true, "Logout successful", null);
	}
}