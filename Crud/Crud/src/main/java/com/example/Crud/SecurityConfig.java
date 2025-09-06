package com.example.Crud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity  // Enables @PreAuthorize, @Secured, etc.
public class SecurityConfig {

    /**
     * Configure in-memory users with roles.
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails viewer = User.builder()
                .username("viewer")
                .password(passwordEncoder.encode("viewer123"))
                .roles("VIEWER")
                .build();

        return new InMemoryUserDetailsManager(admin, viewer);
    }

    /**
     * Define password encoder (BCrypt).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Define access rules using SecurityFilterChain.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (enable in prod for POST/PUT/DELETE)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/persons/healthcheck").permitAll()
                        .requestMatchers(HttpMethod.GET, "/persons/**").hasAnyRole("VIEWER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/persons/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/persons/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/persons/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Enable HTTP Basic Auth

        return http.build();
    }
}
