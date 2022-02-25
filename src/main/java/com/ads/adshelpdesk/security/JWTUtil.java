package com.ads.adshelpdesk.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${jwt.expiration}") // pega o tempo de expiration no propeties
	private Long expiration;
	
	@Value("${jwt.secret}") // pega o tempo de chave secreta no propeties
	private String secret;

	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}

	public boolean tokenValido(String token) {
		
		Claims claims = getclaims(token);
		if (claims != null) {
			
			String username = claims.getSubject();
			Date expeirationDate = claims.getExpiration(); // pega o tempo de ciracão do token
			Date now = new Date(System.currentTimeMillis()); // pega a hora atual			
			
			if (username != null && expeirationDate != null && now.before(expeirationDate)) { // verifacar se o token ainda é valido
				return true;
				
			}
			
		}
		return false;
	}

	private Claims getclaims(String token) {
		try {
			
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
			
			
		} catch (Exception e) {
			return null;
		}
		
	}

	public String getUsername(String token) {
		
		Claims claims = getclaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
}
