package com.learning.security.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@CrossOrigin(origins = "http://localhost:3000/")
public class BasicSecurityConfiguration {

	@SuppressWarnings("removal")
	@Bean
	// Override filterChain method of
	// SpringBootWebSecurityConfiguration(SecurityFilterChainConfiguration)
	SecurityFilterChain basicSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
		// Stateless API session Creation policy to not create httpSession
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		http.csrf().disable();
		// Allows frames for H2 Console on same origin
		http.headers().frameOptions().sameOrigin();
		return http.build();
	}

	@Bean
	public DataSource dataSource() {
		// We're now using H2 database tables Users and Authorities to store user
		// details, for doing that we need to set DatabaseBuilder H2, execute
		// DEFAULT_USER_SCHEMA_DDL_LOCATION (user.ddl) and build.
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION).build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		// Created user withUsername and password
		// {noop} in case of no encoding in password
		// return the users in InMemoryDetailsManager
		var user = User.withUsername("Tulsi").password("tulsi").passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("ADMIN", "USER").build();
		var admin = User.withUsername("Satya").password("tulsi").passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("USER").build();
		var jdbcUserDetails = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetails.createUser(user);
		jdbcUserDetails.createUser(admin);
		return jdbcUserDetails;
	}

	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurer() { public void addCorsMappings(CorsRegistry registry) { //
	 * For cross site requests allow all methods from allowedOrigin url
	 * registry.addMapping("/**").allowedMethods("*").allowedOrigins(
	 * "http://localhost:3000/");
	 * 
	 * } }; }
	 */
}
