package com.protasker.users;

import com.protasker.users.dto.AppUserDto;
import com.protasker.users.entity.AppUser;
import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;
import com.protasker.users.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

    @GetMapping
    public List<AppUserDto> getUsers() {
        return appUserService.getAllUsers();
    }

    @PostMapping
    public CreateAppUserResponse createUser(@RequestBody CreateAppUserRequest request) {
        return appUserService.createUser(request);
    }
}
