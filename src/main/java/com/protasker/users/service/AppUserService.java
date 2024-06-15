package com.protasker.users.service;

import com.protasker.users.dto.AppUserDto;
import com.protasker.users.entity.AppUser;
import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;

import java.util.List;

public interface AppUserService {

    CreateAppUserResponse createUser(CreateAppUserRequest request);
    AppUserDto getUserById(Long id);
    List<CreateAppUserResponse> getAllUsers();
    AppUserDto updateUser(AppUser appUser, Long id);
    void deleteUser(Long id);
}
