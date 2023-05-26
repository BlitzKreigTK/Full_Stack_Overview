package com.learning.rest.webservices.restfulwebservices.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// 1. All requests incoming should be authenticated
		// 1(a). Also permit pre-flight requests OPTIONS for all url /**
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated());
		// 2. Enable basic authorization instead of Sign In page
		http.httpBasic(withDefaults());
		// 3. Creating the RestAPI stateless
		// For becoming stateless, do not store even authentication/authorization
		// details of the client. Provide authentication credentials with each request.
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// 4. Disable CSRF for POST and PUT requests
		http.csrf().disable();
		return http.build();
	}
}
