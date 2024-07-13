package com.protasker.users.login.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protasker.users.login.LoginRequest;
import com.protasker.users.response.GetAppUserResponse;
import com.protasker.users.service.AppUserService;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final AppUserService userService;
    private final Environment environment;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest credentials = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            credentials.getPassword(),
                            new ArrayList<>()));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Instant currentTime = Instant.now();
        String username = ((User) authResult.getPrincipal()).getUsername();
        String tokenSecret = environment.getProperty("token.secret");
        String expirationTime = environment.getProperty("token.expiration_time");
        byte[] secretKeyBytes = Base64.getEncoder().encode(tokenSecret.getBytes());

        //todo: inspect correctness of HS512 value
//        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());
        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, Jwts.SIG.HS512.toString());

        GetAppUserResponse userDetailsByEmail = userService.getUserDetailsByEmail(username);

        String token = Jwts.builder()
                .subject(userDetailsByEmail.getUserId())
                .expiration(Date.from(Instant.now().plusMillis(Long.parseLong(expirationTime))))
                .issuedAt(Date.from(currentTime))
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDetailsByEmail.getUserId());
    }
}
