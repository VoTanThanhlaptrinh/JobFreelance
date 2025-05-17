package com.job_freelance_internal_db.service;

import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
	public String generateToken(UserDetails userDetails);

	public String extractUsername(String token);

	public <T> T extractClaims(String token, Function<Claims, T> claimResolver);

	public boolean isTokenValid(String token, UserDetails userDetails);
	
	public boolean isTokenExpired(String token);
}
