package com.motoparts.security.jwt;

import com.motoparts.user.entity.User;

public interface JwtTokenService {

	String generateToken(User user);
	
	String generateRefreshToken(User user);
	
	String extractEmail(String token);

	boolean isTokenValid(String token);
}
