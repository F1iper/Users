package com.protasker.users.service;

import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;
import com.protasker.users.response.GetAppUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppUserService extends UserDetailsService {

    CreateAppUserResponse createUser(CreateAppUserRequest request);

    GetAppUserResponse getUserById(Long id);

    List<GetAppUserResponse> getAllUsers();

    void deleteUser(Long id);
    GetAppUserResponse getUserDetailsByEmail(String email);
}
