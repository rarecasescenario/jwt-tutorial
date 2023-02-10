package com.jwt.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

	
		private final JwtEncoder encoder;

		public TokenService(JwtEncoder encoder) {
			super();
			this.encoder = encoder;
		}
		
		public String generateToken(Authentication authentication) {
			Instant now = Instant.now();
			
			String scope = authentication.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.joining(" "));
			
			System.out.println("SCOPE = " + scope);
			
	        JwtClaimsSet claims = JwtClaimsSet.builder()
	                .issuer("self")
	                .issuedAt(now)
	                .expiresAt(now.plus(1, ChronoUnit.HALF_DAYS))
	                .subject(authentication.getName())
	                .claim("scope", scope)
	                .build();
	        String token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	        System.out.println("TOKEN = " + token);
			return token;
		}
}