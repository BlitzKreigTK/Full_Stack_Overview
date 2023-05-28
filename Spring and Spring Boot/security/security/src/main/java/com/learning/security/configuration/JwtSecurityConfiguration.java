package com.learning.security.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

//@Configuration
@CrossOrigin(origins = "http://localhost:3000/")
public class JwtSecurityConfiguration {
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION).build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		var user = User.withUsername("Tulsi").password("tulsi").passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("ADMIN", "USER").build();
		var admin = User.withUsername("Satya").password("tulsi").passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("USER").build();
		var jdbcUserDetails = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetails.createUser(user);
		jdbcUserDetails.createUser(admin);
		return jdbcUserDetails;
	}

	@SuppressWarnings("removal")
	@Bean
	SecurityFilterChain basicSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.httpBasic(withDefaults());
		http.csrf().disable();
		http.headers().frameOptions().sameOrigin();

		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

		return http.build();
	}

	@Bean
	// First create a key Pair using KeyPair and pass to RSAKey
	// KeyPair: This class is a simple holder for a key pair(public & private key)
	public KeyPair keyPair() {
		try {
			// Creating RSA keyPair using KeyPairGenerator
			var keyPair = KeyPairGenerator.getInstance("RSA");
			// Setting size of Key generated - 2048 bit
			keyPair.initialize(2048);
			// Generating a keyPair
			return keyPair.generateKeyPair();

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Bean
	// Get the keyPair values
	public RSAKey rsaKey(KeyPair keyPair) {
		// Creating RSA Key from keyPair to get the public and private, generate a
		// random UUID to string
		return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic()).privateKey(keyPair.getPrivate())
				.keyID(UUID.randomUUID().toString()).build();
	}

	@Bean
	// JWK source, exposes a method for retrieving JWKs matching specified selector.
	public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
		// Creates a new JWK set with a RSA key.
		var jwkSet = new JWKSet(rsaKey);
		return (jwkSelector, context) -> jwkSelector.select(jwkSet);
	}

	@Bean
	// Using Nimbus JWT decoder
	// JWTDecoder: Decodes the JWT
	public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
		// Use the given public key to validate JWTs
		// Returns a standard representation of this RSA JWK.
		// RSA is not supported by provider or if the JWK parameters are invalid for a
		// public RSA key.
		return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
	}

	@Bean
	// JWTDecoder: Encoding the JWT
	public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
		return new NimbusJwtEncoder(jwkSource);
	}
}
