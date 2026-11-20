package com.motoparts.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.motoparts.user.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access-token-expiration}")
	private long accessTokenExpiration;

	@Override
	public String generateToken(User user) {

		String roles = user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.joining(","));

		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

		Date now = new Date();

		Date expiry = new Date(now.getTime() + accessTokenExpiration);

		return Jwts.builder().subject(user.getEmail()).issuer("motoparts-hub").claim("roles", roles).issuedAt(now)
				.expiration(expiry).signWith(key).compact();
	}

	@Override
	public String generateRefreshToken(User user) {

		return java.util.UUID.randomUUID().toString();
	}

	@Override
	public String extractEmail(String token) {

		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

		Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

		return claims.getSubject();
	}

	@Override
	public boolean isTokenValid(String token) {

		try {

			SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

			Jwts.parser().verifyWith(key).build().parseSignedClaims(token);

			return true;

		} catch (Exception e) {

			return false;
		}
	}
}