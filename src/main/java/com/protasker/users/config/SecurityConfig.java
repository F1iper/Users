package com.protasker.users.config;

import com.protasker.users.login.filter.AuthenticationFilter;
import com.protasker.users.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AppUserService usersService;
    private final PasswordEncoder passwordEncoder;
    private final Environment environment;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(usersService).passwordEncoder(passwordEncoder);

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http
                .csrf(AbstractHttpConfigurer::disable);

        http
                .authorizeHttpRequests(authorize -> {
                            authorize
                                    .requestMatchers(HttpMethod.POST, "/users").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/users").permitAll()
                                    //todo: replace deprecated method
                                    .anyRequest().authenticated()
                                    .and().addFilter(new AuthenticationFilter(authenticationManager, usersService, environment)
                                            ).authenticationManager(authenticationManager);
                        });

        http
                .sessionManagement((sessionManagement ->
                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                );

        return http.build();
    }
}