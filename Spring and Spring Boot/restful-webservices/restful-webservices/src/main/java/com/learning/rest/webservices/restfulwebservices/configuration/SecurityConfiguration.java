package com.learning.rest.webservices.restfulwebservices.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// 1. All requests incoming should be authenticated
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		// 2. Enable basic authorization instead of Sign In page
		http.httpBasic(withDefaults());
		// 3. Disable CSRF for POST and PUT requests
		http.csrf().disable();
		return http.build();
	}
}
