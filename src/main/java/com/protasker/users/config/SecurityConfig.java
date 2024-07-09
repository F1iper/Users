package com.protasker.users.config;

import com.protasker.users.login.filter.AuthenticationFilter;
import com.protasker.users.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final Environment environment;
    private AppUserService usersService;
    private BCryptPasswordEncoder passwordEncoder;


    public SecurityConfig(Environment environment, AppUserService usersService) {
        this.environment = environment;
        this.usersService = usersService;
    }

    @Autowired
    public SecurityConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String ipAddress = environment.getProperty("gateway.ip");

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
                                    .and().addFilter(new AuthenticationFilter(authenticationManager)
                                            ).authenticationManager(authenticationManager);
                        }
//                        .requestMatchers(HttpMethod.POST, "/users").access(new WebExpressionAuthorizationManager("hasIpAddress('" + ipAddress + "')"))
//                        .requestMatchers(HttpMethod.GET, "/users").access(new WebExpressionAuthorizationManager("hasIpAddress('" + ipAddress + "')"))
                );

        http
                .sessionManagement((sessionManagement ->
                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                );

        return http.build();
    }
}