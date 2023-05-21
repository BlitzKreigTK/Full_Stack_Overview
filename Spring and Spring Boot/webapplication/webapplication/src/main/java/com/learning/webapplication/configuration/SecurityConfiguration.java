package com.learning.webapplication.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public InMemoryUserDetailsManager createDetailsManager() {
		UserDetails userDetails1 = createNewUser("Raunit", "raunit");
		UserDetails userDetails2 = createNewUser("Tulsi", "tulsi");
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	// InMemoryUserDetailsManager internally store and retrieve the user-related
	// information which is required for Authentication
	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password)
				.roles("ADMIN", "USER").build();
		return userDetails;
	}

	// Service interface for encoding passwords.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Configuring all urls should be protected and redirect to login.
	// Disable CSRF.
	// Enabled frames used by H2 console. Disable them.
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Configuring all urls should be protected.
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		// withDefaults --> Static import from Customizer and redirect to login.
		http.formLogin(withDefaults());
		// Disable CSRF.
		http.csrf().disable();
		// Enabled frames used by H2 console. Disable them.
		http.headers().frameOptions().disable();
		return http.build();
	}

}
