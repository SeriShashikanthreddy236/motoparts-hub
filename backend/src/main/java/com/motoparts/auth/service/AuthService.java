package com.motoparts.auth.service;

import com.motoparts.auth.dto.request.LoginRequest;
import com.motoparts.auth.dto.request.LogoutRequest;
import com.motoparts.auth.dto.request.RefreshTokenRequest;
import com.motoparts.auth.dto.request.RegisterRequest;
import com.motoparts.auth.dto.response.AuthResponse;
import com.motoparts.auth.dto.response.RefreshTokenResponse;
import com.motoparts.auth.dto.response.UserResponse;

public interface AuthService {

	UserResponse register(RegisterRequest request);

	AuthResponse login(LoginRequest request);

	UserResponse getCurrentUser(String email);

	RefreshTokenResponse refreshToken(RefreshTokenRequest request);

	void logout(LogoutRequest request);
}