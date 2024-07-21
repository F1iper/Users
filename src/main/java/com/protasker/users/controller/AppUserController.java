package com.protasker.users.controller;

import com.protasker.users.login.LoginRequest;
import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;
import com.protasker.users.response.GetAppUserResponse;
import com.protasker.users.service.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AppUserController {

    @Autowired
    private Environment env;

    private final AppUserService appUserService;

    @GetMapping("/status/check")
    public String status() {
        return "This service is working on port: " + env.getProperty("local.server.port");
    }

    @PostMapping
    public ResponseEntity<CreateAppUserResponse> createUser(@Valid @RequestBody CreateAppUserRequest request) {
        CreateAppUserResponse response = appUserService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<GetAppUserResponse>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAppUserResponse> getAppUserById (@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(appUserService.getUserById(id));
    }

    @PostMapping("/login")
    public void fakeLogin(@RequestBody LoginRequest loginRequest) {
        throw new IllegalStateException("This should not be called bro");
    }
}
