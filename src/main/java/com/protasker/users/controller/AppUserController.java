package com.protasker.users.controller;

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
    public ResponseEntity createUser(@Valid @RequestBody CreateAppUserRequest request) {
        CreateAppUserResponse response = appUserService.createUser(request);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<GetAppUserResponse> getUsers() {
        return appUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public GetAppUserResponse getAppUserById (@PathVariable Long id){
        return appUserService.getUserById(id);
    }
}
