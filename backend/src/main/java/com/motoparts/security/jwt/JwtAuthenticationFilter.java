package com.motoparts.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.motoparts.security.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenService jwtTokenService;

	private final CustomUserDetailsService userDetailsService;

	public JwtAuthenticationFilter(JwtTokenService jwtTokenService, CustomUserDetailsService userDetailsService) {

		this.jwtTokenService = jwtTokenService;

		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("JWT Filter Executed");

		final String authHeader = request.getHeader("Authorization");

		System.out.println("Authorization Header: " + authHeader);

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {

			filterChain.doFilter(request, response);

			return;
		}

		String jwt = authHeader.substring(7);

		if (!jwtTokenService.isTokenValid(jwt)) {

			filterChain.doFilter(request, response);

			return;
		}

		String email = jwtTokenService.extractEmail(jwt);

		System.out.println("JWT User: " + email);

		UserDetails userDetails = userDetailsService.loadUserByUsername(email);

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());

		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authToken);
		
		System.out.println("Authorities: " + userDetails.getAuthorities());

		System.out.println("Authentication Set: " + SecurityContextHolder.getContext().getAuthentication());

		filterChain.doFilter(request, response);
	}
}
