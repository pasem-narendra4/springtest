package com.centella.backend.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.centella.backend.common.AccessDeniedException;
import com.centella.backend.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	
	private static String secret = "This_is_secret";
	private static long expiryDuration = 60+60;
	
	public String generateJWT(User user) {

		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDuration * 1000;
		
		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);
		
//		claims
		Claims claims = Jwts.claims()
				.setIssuer(user.getUser_id().toString())
				.setIssuedAt(issuedAt)
				.setExpiration(expiryAt);
		
//		claims.put("type", user.getUserType());
		claims.put("name", user.getName());
		claims.put("emailId", user.getEmailId());
		
//		generate jwt using claims
		
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public void verify(String authorization) throws Exception{
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization);
		} catch(Exception e) {
			throw new AccessDeniedException("Accessed Denied");
		}

	}
}
	