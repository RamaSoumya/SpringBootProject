//package com.example.security;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import com.example.exceptions.BlogApiException;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//
//@Component
//public class JwtTokenProvider {
//
//	@Value("${app.jwt-secret}")
//	private String jwtSecret;
//	
//	@Value("${app.jwt-expiration-milliseconds}")
//	private String jwtExpirationInMs;
//	
//	//generate token
//	public String generateToken(Authentication authentication) {
//		String username = authentication.getName();
//		Date currentDate = new Date();
//		Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);
//		
//		String token = Jwts.builder()
//				.setSubject(username)
//				.setIssuedAt(new Date())
//				.setExpiration(expireDate)
//				.signWith(SignatureAlgorithm.HS512, jwtSecret)
//				.compact();
//		
//		return token;
//	}
//	
//	//get username from token
//	public String getUsernameFromJWT(String token) {
//		Claims claims =	Jwts.parser()
//				.setSigningKey(jwtSecret)
//				.parseClaimsJws(token)
//				.getBody();
//		
//		return claims.getSubject();
//	}
//	
//	//validate jwt token
//	public boolean validateToken(String token) throws BlogApiException {
//		
//		try {
//			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//			return true;
//		} catch (SignatureException ex) {
//			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
//		} catch (MalformedJwtException ex) {
//			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
//		} catch (ExpiredJwtException ex) {
//			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
//		} catch (UnsupportedJwtException ex) {
//			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
//		} catch (IllegalArgumentException ex) {
//			throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT class String is empty");
//		}
//		
//	}
//}
