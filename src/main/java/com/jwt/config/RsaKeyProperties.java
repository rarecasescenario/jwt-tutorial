package com.jwt.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="rsa")
public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
	public RsaKeyProperties {
		if(publicKey == null) 
			throw new IllegalArgumentException("Invalid or missing a public key"); 
		if(privateKey == null) 
			throw new IllegalArgumentException("Invalid or missing a private key");
	}
}
