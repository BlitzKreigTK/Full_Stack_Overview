package com.learning.security.controller;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;

//@RestController
public class AuthenticationController {

	@Autowired
	private JwtEncoder jwtEncoder;

	@PostMapping("/authenticate")
	public JwtResponse authenticate(Authentication authenticate) {
		// Will return all details like user details, role etc
		// return authenticate;
		// Authenticate object is passed to createToken method for adding details like
		// iat, sub etc
		return new JwtResponse(createToken(authenticate));
	}

	// Using authenticate we are going to create a token
	public String createToken(Authentication authentication) {
		// Using JwtClaimsSet we're setting issuer, issuedAt, expiresAt, subject and
		// scope (Access type) and then encoding the values and getting token value
		var claims = JwtClaimsSet.builder().issuer("self").issuedAt(Instant.now())
				.expiresAt(Instant.now().plusSeconds(60 * 15)).subject(authentication.getName())
				.claim("scope", createScope(authentication)).build();
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	// In case a user can have multiple roles, so create Scope from authentication,
	// getting it into a map and appending them with " "
	private String createScope(Authentication authentication) {
		return authentication.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(" "));
	}
}

// A record to return JwtToken in structured format
record JwtResponse(String token) {
}
