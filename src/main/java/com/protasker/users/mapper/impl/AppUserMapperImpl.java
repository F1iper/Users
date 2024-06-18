package com.protasker.users.mapper.impl;

import com.protasker.users.entity.AppUser;
import com.protasker.users.mapper.AppUserMapper;
import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;
import com.protasker.users.response.GetAppUserResponse;
import org.springframework.stereotype.Service;

@Service
public class AppUserMapperImpl implements AppUserMapper {

    @Override
    public AppUser toEntityFromRequest(CreateAppUserRequest request) {
        return AppUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                //todo encrypt password
                .encryptedPassword(request.getPassword())
                .email(request.getEmail())
                .build();
    }

    @Override
    public CreateAppUserResponse toCreateResponseFromEntity(AppUser savedAppUser) {
        return CreateAppUserResponse.builder()
                .firstName(savedAppUser.getFirstName())
                .lastName(savedAppUser.getLastName())
                .email(savedAppUser.getEmail())
                .userId(savedAppUser.getUserId())
                .build();
    }

    @Override
    public GetAppUserResponse toGetResponseFromEntity(AppUser appUser) {
        return GetAppUserResponse.builder()
                .id(appUser.getId())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .build();
    }
}
