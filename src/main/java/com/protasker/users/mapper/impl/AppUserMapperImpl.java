package com.protasker.users.mapper.impl;

import com.protasker.users.dto.AppUserDto;
import com.protasker.users.entity.AppUser;
import com.protasker.users.mapper.AppUserMapper;
import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;
import org.springframework.stereotype.Service;

@Service
public class AppUserMapperImpl implements AppUserMapper {

    @Override
    public AppUser toEntityFromRequest(CreateAppUserRequest request) {
        return AppUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }

    @Override
    public AppUserDto toDto(AppUser appUser) {
        return AppUserDto.builder()
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .password(appUser.getPassword())
                .email(appUser.getEmail())
                .build();
    }

    @Override
    public AppUser toEntity(AppUserDto dto) {
        return AppUser.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }

    @Override
    public CreateAppUserResponse toResponseFromEntity(AppUser savedAppUser) {
        return CreateAppUserResponse.builder()
                .firstName(savedAppUser.getFirstName())
                .lastName(savedAppUser.getLastName())
                .email(savedAppUser.getEmail())
                .build();
    }
}
