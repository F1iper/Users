package com.protasker.users;

import com.protasker.users.entity.AppUser;
import com.protasker.users.mapper.CreateAppUserRequestMapper;
import com.protasker.users.repository.AppUserRepository;
import com.protasker.users.request.CreateAppUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private Environment env;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private CreateAppUserRequestMapper mapper;


    @GetMapping("/status/check")
    public String status() {
        return "It is working on port: " + env.getProperty("local.server.port");
    }

    @GetMapping
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    @PostMapping
    public String createUser(@RequestBody CreateAppUserRequest request) {
        appUserRepository.save(mapper.mapToUser(request));
        return "Created user :)";
    }
}
