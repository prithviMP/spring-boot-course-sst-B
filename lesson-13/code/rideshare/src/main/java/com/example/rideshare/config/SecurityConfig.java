package com.example.rideshare.config;

import com.example.rideshare.config.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration c)
            throws Exception {
        return c.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain chain(HttpSecurity http, JwtFilter filter)
            throws Exception {

        http.csrf(cs -> cs.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/v1/**").permitAll()  // Allow v1 APIs for testing
                        .requestMatchers("/api/v2/**").permitAll()  // Allow v2 APIs for testing
                        .requestMatchers("/api/rides/**").permitAll()  // Allow core ride APIs for testing
                        .requestMatchers("/api/analytics/**").permitAll()  // Allow analytics APIs for testing
                        .anyRequest().authenticated())
                .sessionManagement(sess ->
                        sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
