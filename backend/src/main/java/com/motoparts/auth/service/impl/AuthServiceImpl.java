package com.motoparts.auth.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
//import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.motoparts.auth.dto.request.LoginRequest;
import com.motoparts.auth.dto.request.LogoutRequest;
import com.motoparts.auth.dto.request.RefreshTokenRequest;
import com.motoparts.auth.dto.request.RegisterRequest;
import com.motoparts.auth.dto.response.AuthResponse;
import com.motoparts.auth.dto.response.RefreshTokenResponse;
import com.motoparts.auth.dto.response.UserResponse;
import com.motoparts.auth.service.AuthService;
import com.motoparts.refresh.entity.RefreshToken;
import com.motoparts.refresh.repository.RefreshTokenRepository;
import com.motoparts.role.entity.Role;
import com.motoparts.role.enums.RoleName;
import com.motoparts.role.repository.RoleRepository;
import com.motoparts.security.jwt.JwtTokenService;
import com.motoparts.user.entity.User;
import com.motoparts.user.enums.AccountStatus;
import com.motoparts.user.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtTokenService jwtTokenService;

	private final RefreshTokenRepository refreshTokenRepository;

	public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, JwtTokenService jwtTokenService,
			RefreshTokenRepository refreshTokenRepository) {

		this.userRepository = userRepository;

		this.roleRepository = roleRepository;

		this.passwordEncoder = passwordEncoder;

		this.jwtTokenService = jwtTokenService;

		this.refreshTokenRepository = refreshTokenRepository;
	}

	@Override
	public UserResponse register(RegisterRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {

			throw new RuntimeException("Email already exists");
		}

		Role customerRole = roleRepository.findByName(RoleName.CUSTOMER)
				.orElseThrow(() -> new RuntimeException("Customer role not found"));

		User user = new User();

		user.setFirstName(request.getFirstName());

		user.setLastName(request.getLastName());

		user.setEmail(request.getEmail());

		user.setPhoneNumber(request.getPhoneNumber());

		user.setPassword(passwordEncoder.encode(request.getPassword()));

		user.setEnabled(true);

		user.setAccountStatus(AccountStatus.ACTIVE);

		user.getRoles().add(customerRole);

		User savedUser = userRepository.save(user);

		UserResponse response = new UserResponse();

		response.setId(savedUser.getId());

		response.setFirstName(savedUser.getFirstName());

		response.setLastName(savedUser.getLastName());

		response.setEmail(savedUser.getEmail());

		response.setPhoneNumber(savedUser.getPhoneNumber());

		response.setAccountStatus(savedUser.getAccountStatus().name());

		response.setRoles(savedUser.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()));

		return response;
	}

	@Override
	public AuthResponse login(LoginRequest request) {

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Invalid email or password"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

			throw new RuntimeException("Invalid email or password");
		}

		if (user.getAccountStatus() != AccountStatus.ACTIVE) {

			throw new RuntimeException("Account is not active");
		}
		UserResponse userResponse = new UserResponse();

		userResponse.setId(user.getId());

		userResponse.setFirstName(user.getFirstName());

		userResponse.setLastName(user.getLastName());

		userResponse.setEmail(user.getEmail());

		userResponse.setPhoneNumber(user.getPhoneNumber());

		userResponse.setAccountStatus(user.getAccountStatus().name());

		userResponse.setRoles(user.getRoles().stream().map(role -> role.getName().name())
				.collect(java.util.stream.Collectors.toSet()));

		String accessToken = jwtTokenService.generateToken(user);

		String refreshToken = jwtTokenService.generateRefreshToken(user);

		RefreshToken refreshTokenEntity = new RefreshToken();

		refreshTokenEntity.setTokenHash(refreshToken);

		refreshTokenEntity.setUser(user);

		refreshTokenEntity.setExpiresAt(Instant.now().plus(7, ChronoUnit.DAYS));

		refreshTokenRepository.save(refreshTokenEntity);

		AuthResponse response = new AuthResponse();

		response.setAccessToken(accessToken);

		response.setRefreshToken(refreshToken);

		response.setTokenType("Bearer");

		response.setUser(userResponse);

		return response;
	}

	@Override
	public UserResponse getCurrentUser(String email) {

		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

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

	@Override
	public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {

		RefreshToken refreshToken = refreshTokenRepository.findByTokenHash(request.getRefreshToken())
				.orElseThrow(() -> new RuntimeException("Invalid refresh token"));

		if (refreshToken.getRevokedAt() != null) {

			throw new RuntimeException("Refresh token revoked");
		}

		if (refreshToken.getExpiresAt().isBefore(Instant.now())) {

			throw new RuntimeException("Refresh token expired");
		}

		User user = refreshToken.getUser();

		String newAccessToken = jwtTokenService.generateToken(user);

		RefreshTokenResponse response = new RefreshTokenResponse();

		response.setAccessToken(newAccessToken);

		response.setTokenType("Bearer");

		return response;
	}

	@Override
	public void logout(LogoutRequest request) {

		RefreshToken refreshToken = refreshTokenRepository.findByTokenHash(request.getRefreshToken())
				.orElseThrow(() -> new RuntimeException("Refresh token not found"));

		refreshToken.setRevokedAt(Instant.now());

		refreshTokenRepository.save(refreshToken);
	}
}