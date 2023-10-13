package com.centella.backend.util;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.centella.backend.common.AccessDeniedException;
import com.centella.backend.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
//	private static String secret = Keys.secretKeyFor(SignatureAlgorithm.HS512).toString();
//	private String secret = generateSafeToken();
	private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); //or HS384 or HS512
	private String secret  = Encoders.BASE64.encode(key.getEncoded());
	private static long expiryDuration = 60+60;

	
	public String generateJWT(User user) {

		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDuration * 1000;
		
		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);
		
		List<String> authorities = Arrays.asList("ROLE_USER");
		
//		claims
		Claims claims = Jwts.claims()
				.setSubject(user.getUsername().toString())
				.setIssuedAt(issuedAt)
				.setExpiration(expiryAt);
		
//		claims.put("type", user.getUserType());
		claims.put("name", user.getName());
		claims.put("emailId", user.getEmailId());
		claims.put("authorities", authorities);
		
//		generate jwt using claims
		
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public void verify(String authorization) throws Exception{
		try {
//			System.out.print("Authorization " + authorization);
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization);
		} catch(Exception e) {
			throw new AccessDeniedException("Accessed Denied");
		}

	}
	
    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    private Claims extractAllClaims(String token) {
		return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .parseClaimsJws(token)
                .getBody();
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        System.out.println(claims);
        return claimsResolver.apply(claims);
    }
    
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public Claims validateToken(String token) {
        String username = extractUsername(token);
        Claims claims = extractAllClaims(token);
        
//        System.out.println(claims);

        if (claims == null || !claims.getSubject().equals(username) || claims.getExpiration().before(new Date())) {
            return null;
        }
        return claims;
    }
}
	